package com.weCare.servicesImpls;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
	public Appointment bookAppointment(Appointment appointment, String hospital_id, String patient_id) {
		
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

		/*
		
		LocalDate appointment_date = appointment.getAppointment_date();
//		System.out.println(appointment_date);
		
		int slotsForDoctor = slotRepository.countSlotsForDoctor(appointment_date, appointment.getSlotPeriod());
		System.out.println(slotsForDoctor);
		
		if(slotsForDoctor!=0)
			throw new NotFoundException(appointment.getSlotPeriod()+", is Booked on "+appointment_date);
		
		Slot slot = new Slot();
		
		slot.setSlotStatus(SlotStatus.BOOKED);
		slot.setSlotPeriod(appointment.getSlotPeriod());
		slot.setSlotDate(appointment_date);
//		slot.setAppointment(appointment);
		
		Slot saved_slot = slotRepository.save(slot);
		System.out.println(saved_slot);
		*/
		
		List<Appointment> appointmentOptional = appointmentRepository.findAppointmentByDateAndSlot(random_doctor.getProfile_id(), appointment.getSlot(), appointment.getAppointment_date());
		
//		System.out.println(appointmentOptional);
		if(!appointmentOptional.isEmpty())
			throw new AppointmentNotFoundException(appointment.getSlot()+", Already Booked on "+appointment.getAppointment_date()+", try different slot or date");
		 
		patient.getDoctors().add(random_doctor);
		patient.getAppointments().add(appointment);

		random_doctor.getPatients().add(patient);
		random_doctor.getAppointments().add(appointment);
//		random_doctor.getSlots().add(saved_slot);

		hospital.getAppointments().add(appointment);
		hospital.getPatients().add(patient);

		
//		appointment.setSlot(saved_slot);
		appointment.setPatient(patient);
		appointment.setDoctor(random_doctor);
		appointment.setBooking_time(LocalDateTime.now());
		appointment.setHospital(hospital);
		appointment.setStatus(AppointmentStatus.APPOINTMENT_BOOKED);

//        return appointment;
		return appointmentRepository.save(appointment);
	}

	@Override
	public Appointment bookAppointmentWithDoctor(Appointment appointment, String hospital_id, String patient_id,
			String doctor_id) {

		Hospital hospital = hospitalRepository.findById(hospital_id)
				.orElseThrow(() -> new NotFoundException("Hospital with id: " + hospital_id + ", not found!!!"));

		Patient patient = patientRepository.findById(patient_id)
				.orElseThrow(() -> new NotFoundException("Patient with id: " + patient_id + ", not found!!!"));
		Doctor doctor = doctorRepository.findById(doctor_id)
				.orElseThrow(() -> new NotFoundException("Doctor with id: " + doctor_id + ", not found!!!"));

		Department appointment_depart = appointment.getDepartment();
		Department doctor_depart = doctor.getDepartment();

		if (!doctor_depart.equals(appointment_depart) || doctor.getAvailability() != Availability.AVAILABLE) {

			List<Doctor> doctors_with_same_department = doctorRepository
					.findByDepartmentAvailabilityAndHospital(appointment_depart, Availability.AVAILABLE, hospital_id);

			if (doctors_with_same_department.isEmpty())
				throw new NotFoundException("Doctors with:" + appointment_depart + ", not found");

			String doctors_available = "";
			for (Doctor doc : doctors_with_same_department) {
				doctors_available += doc.getProfile_id() + ", ";
			}

			throw new NotFoundException("You chose " + appointment_depart + ", but Selected Doctor sees "
					+ doctor_depart + ". Perhaps, You can choose any of these, " + doctors_available);

		}
		
			List<Appointment> appointmentOptional = appointmentRepository.findAppointmentByDateAndSlot(doctor_id, appointment.getSlot(), appointment.getAppointment_date());
					
					System.out.println(appointmentOptional);
					if(!appointmentOptional.isEmpty())
						throw new AppointmentNotFoundException(appointment.getSlot()+", Already Booked on "+appointment.getAppointment_date()+", try different slot or date");

		// Persisting Patient details
		patient.getDoctors().add(doctor);
		patient.getAppointments().add(appointment);

		// Persisting doctor details
		doctor.getPatients().add(patient);
		doctor.getAppointments().add(appointment);

		// Persisting hospital details
		hospital.getAppointments().add(appointment);
		hospital.getPatients().add(patient);

		// Set appointment date to today if not provided
		if (appointment.getAppointment_date() == null)
			    appointment.setAppointment_date(LocalDate.now());
		
		// Persisting Appointment details
		appointment.setPatient(patient);
		appointment.setBooking_time(LocalDateTime.now());
//		appointment.setAppointment_date(LocalDate.now());
		appointment.setStatus(AppointmentStatus.APPOINTMENT_BOOKED);
		appointment.setDoctor(doctor);
		appointment.setHospital(hospital);
		
//		return appointment;
		return appointmentRepository.save(appointment);

	}

	@Override
	public Appointment getAppointmentById(String appointment_id) {

		return appointmentRepository.findById(appointment_id)
				.orElseThrow(() -> new NotFoundException("Appointment with id: " + appointment_id + ", not found!!!"));
	}

	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> appointments = appointmentRepository.findAll();
		if (appointments.isEmpty())
			throw new NotFoundException("Appointment Not Found!!!");
		return appointments;
	}

	@Override
	public Appointment updateAppointment(String appointment_id, Appointment appointment) {
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
			Appointment updatedAppointment = bookAppointment(retrieved_appointment, hospital_id, profile_id);			
			return updatedAppointment;
		}
		throw new AppointmentNotFoundException("Appointment Couldn't update!!!");
		
	}

	@Override
	public String deleteAppointmentById(String appointment_id) {
		return null;
	}

}
