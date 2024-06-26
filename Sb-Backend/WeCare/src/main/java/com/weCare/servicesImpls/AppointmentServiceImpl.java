package com.weCare.servicesImpls;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import com.weCare.dto.AppointmentResponseDTO;
import com.weCare.dto.DoctorDTO;
import com.weCare.dto.HospitalDTO;
import com.weCare.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.AppointmentNotFoundException;
import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.Appointment;
import com.weCare.modals.AppointmentStatus;
import com.weCare.modals.Availability;
import com.weCare.modals.Department;
import com.weCare.modals.Doctor;
import com.weCare.modals.Hospital;
import com.weCare.modals.Patient;
import com.weCare.modals.SlotPeriod;
import com.weCare.repository.AppointmentRepository;
import com.weCare.repository.DoctorRepository;
import com.weCare.repository.HospitalRepository;
import com.weCare.repository.PatientRepository;
import com.weCare.services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;
	
//	@Autowired
//	private SlotRepository slotRepository;

	@Override
	public AppointmentResponseDTO bookAppointment(Appointment appointment, String hospital_id, String patient_id) {
		
		Hospital hospital = hospitalRepository.findById(hospital_id)
				.orElseThrow(() -> new NotFoundException("Hospital with id: " + hospital_id + ", not found!!!"));

		Patient patient = patientRepository.findById(patient_id)
				.orElseThrow(() -> new NotFoundException("Patient with id: " + patient_id + ", not found!!!"));

		// Set appointment date to today if not provided
		if (appointment.getAppointment_date() == null)
			appointment.setAppointment_date(LocalDate.now());

		Department department = appointment.getDepartment();

		List<Doctor> doctors_with_same_department = doctorRepository.findByDepartmentAvailabilityAndHospital(department,
				Availability.AVAILABLE, hospital_id);
		
		if (doctors_with_same_department.isEmpty())
			throw new NotFoundException("Doctors with:" + department + ", not found");

		Doctor random_doctor = doctors_with_same_department
				.get(new Random().nextInt(doctors_with_same_department.size()));
		
		List<Appointment> appointmentOptional = appointmentRepository.findAppointmentByDateAndSlot(random_doctor.getProfile_id(), appointment.getSlot(), appointment.getAppointment_date());
		
//		System.out.println(appointmentOptional);
		if(!appointmentOptional.isEmpty())
			throw new AppointmentNotFoundException(appointment.getSlot()+", Already Booked on "+appointment.getAppointment_date()+", try different slot or date");

		String[] slotPeriod = appointment.getSlot().getSlotRange().split("-");
		
		patient.getDoctors().add(random_doctor);
		patient.getAppointments().add(appointment);

		random_doctor.getPatients().add(patient);
		random_doctor.getAppointments().add(appointment);
//		random_doctor.getSlots().add(saved_slot);

		hospital.getAppointments().add(appointment);
		hospital.getPatients().add(patient);

		appointment.setAppointmentStarts(slotPeriod[0]);
		appointment.setAppointmentEnds(slotPeriod[1]);
		appointment.setPatient(patient);
		appointment.setDoctor(random_doctor);
		appointment.setBooking_time(LocalDateTime.now());
		appointment.setHospital(hospital);
		appointment.setStatus(AppointmentStatus.APPOINTMENT_BOOKED);

//        return appointment;
		Appointment bookedAppointment = appointmentRepository.save(appointment);

		return makeAppointmentResponseDTO(bookedAppointment);
	}

	private AppointmentResponseDTO makeAppointmentResponseDTO(Appointment bookedAppointment) {
		Doctor doc = bookedAppointment.getDoctor();
		Patient patient = bookedAppointment.getPatient();
		Hospital hospital = bookedAppointment.getHospital();
		DoctorDTO doctorDTO = new DoctorDTO(
				doc.getProfile_id(),
				doc.getName(),
				doc.getEmail(),
				doc.getMobile()
		);
		PatientDTO patientDTO = new PatientDTO(
				patient.getProfile_id(),
				patient.getName(),
				patient.getEmail(),
				patient.getMobile()
		);
		HospitalDTO hospitalDTO = new HospitalDTO(
				hospital.getHospital_id(),
				hospital.getHospital_name(),
				hospital.getContact()
		);
		return new AppointmentResponseDTO(
				bookedAppointment.getAppointment_id(),
				bookedAppointment.getBooking_time(),
				bookedAppointment.getAppointment_date(),
				bookedAppointment.getAppointmentStarts(),
				bookedAppointment.getAppointmentEnds(),
				bookedAppointment.getDepartment(),
				bookedAppointment.getAilment_description(),
				bookedAppointment.getStatus(),
				bookedAppointment.getSlot(),
				doctorDTO,
				patientDTO,
				hospitalDTO
		);
	}

	@Override
	public AppointmentResponseDTO bookAppointmentWithDoctor(Appointment appointment, String hospital_id, String patient_id,
			String doctor_id) {

		Hospital hospital = hospitalRepository.findById(hospital_id)
				.orElseThrow(() -> new NotFoundException("Hospital with id: " + hospital_id + ", not found!!!"));

		Patient patient = patientRepository.findById(patient_id)
				.orElseThrow(() -> new NotFoundException("Patient with id: " + patient_id + ", not found!!!"));

		Doctor doctor = doctorRepository.findById(doctor_id)
				.orElseThrow(() -> new NotFoundException("Doctor with id: " + doctor_id + ", not found!!!"));

		Department appointment_depart = appointment.getDepartment();
		Department doctor_depart = doctor.getDepartment();

		// Set appointment date to today if not provided
		if (appointment.getAppointment_date() == null)
			appointment.setAppointment_date(LocalDate.now());

		/*
		   If appointment department differs or doctor not available the
		   to show available doctors
		 */
		if (!doctor_depart.equals(appointment_depart) || doctor.getAvailability() != Availability.AVAILABLE) {

			List<Doctor> doctors_with_same_department = doctorRepository
					.findByDepartmentAvailabilityAndHospital(appointment_depart, Availability.AVAILABLE, hospital_id);

			if (doctors_with_same_department.isEmpty())
				throw new NotFoundException("Doctors with:" + appointment_depart + ", not found");

			StringBuilder doctors_available = new StringBuilder();
			for (Doctor doc : doctors_with_same_department) {
				doctors_available.append(doc.getProfile_id()).append(", ");
			}

			throw new NotFoundException("You chose " + appointment_depart + ", but Selected Doctor sees "
					+ doctor_depart + ". Perhaps, You can choose any of these, " + doctors_available);

		}

		List<Appointment> appointmentOptional = appointmentRepository.findAppointmentByDateAndSlot(doctor_id, appointment.getSlot(), appointment.getAppointment_date());

				// System.out.println(appointmentOptional);
		if(!appointmentOptional.isEmpty())
			throw new AppointmentNotFoundException(appointment.getSlot()+", Already Booked on "+appointment.getAppointment_date()+", try different slot or date");

		String[] slotPeriod = appointment.getSlot().getSlotRange().split("-");

		// Persisting Patient details
		patient.getDoctors().add(doctor);
		patient.getAppointments().add(appointment);

		// Persisting doctor details
		doctor.getPatients().add(patient);
		doctor.getAppointments().add(appointment);

		// Persisting hospital details
		hospital.getAppointments().add(appointment);
		hospital.getPatients().add(patient);


		
		// Persisting Appointment details
		appointment.setAppointmentStarts(slotPeriod[0]);
		appointment.setAppointmentEnds(slotPeriod[1]);
		appointment.setPatient(patient);
		appointment.setBooking_time(LocalDateTime.now());
//		appointment.setAppointment_date(LocalDate.now());
		appointment.setStatus(AppointmentStatus.APPOINTMENT_BOOKED);
		appointment.setDoctor(doctor);
		appointment.setHospital(hospital);

		Appointment bookedAppointment =  appointmentRepository.save(appointment);

//		return appointment;
// 		return appointmentRepository.save(appointment);
// 		return new AppointmentResponseDTO();
		return makeAppointmentResponseDTO(bookedAppointment);
	}


	@Override
	public AppointmentResponseDTO getAppointmentById(String appointment_id) {

		Appointment bookedAppointment = appointmentRepository.findById(appointment_id)
				.orElseThrow(() -> new NotFoundException("Appointment with id: " + appointment_id + ", not found!!!"));
		return makeAppointmentResponseDTO(bookedAppointment);
	}

	@Override
	public List<AppointmentResponseDTO> getAllAppointments() {
		List<Appointment> appointments = appointmentRepository.findAll();
		if (appointments.isEmpty())
			throw new NotFoundException("Appointment Not Found!!!");
		// List<AppointmentResponseDTO> appointmentResponseDTOS = new ArrayList<>();
		// for(Appointment appointment:appointments){
		// 	appointmentResponseDTOS.add(
		// 			makeAppointmentResponseDTO(appointment)
		// 	);
		// }
		// return appointmentResponseDTOS;
		// return	appointments.stream().map(
		// 				p->this.makeAppointmentResponseDTO(p)
		// 		).collect(Collectors.toList());
		return appointments.stream().map(this::makeAppointmentResponseDTO).collect(Collectors.toList());
	}

	@Override
	public AppointmentResponseDTO updateAppointment(String appointment_id, Appointment appointment) {
		Appointment retrieved_appointment = appointmentRepository.findById(appointment_id)
		.orElseThrow(() -> new NotFoundException("Appointment with id: " + appointment_id + ", not found!!!"));
		String hospital_id = retrieved_appointment.getHospital().getHospital_id();
		String profile_id = retrieved_appointment.getPatient().getProfile_id();
		List<Appointment> appointments = retrieved_appointment.getDoctor().getAppointments();
		
		if(appointment.getAppointment_date()!=null)
			retrieved_appointment.setAppointment_date(appointment.getAppointment_date());
		if(
			appointment.getDepartment()!=null&&
			!appointment.getDepartment().equals(retrieved_appointment.getDepartment())
				) {
			retrieved_appointment.setDepartment(appointment.getDepartment());
            return bookAppointment(retrieved_appointment, hospital_id, profile_id);
		}
		throw new AppointmentNotFoundException("Appointment Couldn't update!!!");
		
	}

	@Override
	public String deleteAppointmentById(String appointment_id) {
		return null;
	}

}
