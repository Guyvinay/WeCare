# WeCare
(In Development)
# Hospital Management System

The Hospital Management System is a comprehensive application designed to automate and streamline various aspects of hospital operations. It provides functionalities for patient management, appointment scheduling, billing, prescription handling, and more. This system aims to enhance the efficiency of healthcare services by offering a centralized platform for both healthcare providers and patients.

## Table of Contents

- [Features](#features)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [Acknowledgements](#Acknowledgements)
- [Contact](#Contact)
- [License](#license)

## Features

1. **User Management:**
   - Create, update, and delete user accounts.
   - Assign roles to users (e.g., administrators, doctors, nurses).

2. **Patient Management:**
   - Add, update, and retrieve patient information.
   - Maintain detailed medical history for each patient.

3. **Doctor Management:**
   - Register new doctors with specialization and contact details.
   - Retrieve details of individual doctors.

4. **Appointment Scheduling:**
   - Schedule, update, and cancel appointments between doctors and patients.
   - Manage appointment status and timing.

5. **Billing and Invoicing:**
   - Generate invoices for services provided to patients.
   - Track payment status and services included in each invoice.

6. **Hospital Information:**
   - Add new hospitals with name, address, and associated doctors.
   - Retrieve details of individual hospitals.

7. **Prescription Handling:**
   - Create prescriptions with details of medications and additional instructions.
   - Retrieve prescription details for a patient.

8. **Security and Authentication:**
   - Implement secure user authentication using Spring Security.
   - Role-based access control to restrict access to specific features.

## Database Schema

The database schema includes the following entities:

![Screenshot 2024-01-07 052913](https://github.com/Guyvinay/WeCare/assets/119345842/cbd2b4dc-fd6a-457e-a09f-bc453483219b)


1. **User:** Represents users of the system, including doctors, nurses, and administrators.
2. **Role:** Defines roles assigned to users (e.g., ROLE_ADMIN, ROLE_DOCTOR).
3. **Patient:** Stores information about patients, including personal details and medical history.
4. **Doctor:** Represents doctors with details such as specialization and contact information.
5. **Appointment:** Manages appointments scheduled between doctors and patients.
6. **Invoice:** Handles billing information for services provided to patients.
7. **Address:** Stores address details for entities like hospitals, users, etc.
8. **Hospital:** Represents hospitals with information like name, address, and associated doctors.
9. **Prescription:** Manages prescriptions issued by doctors to patients, including medications and instructions.

## API Endpoints

The application exposes RESTful endpoints for various functionalities:

1. **User Management:**
   - `POST /api/users`: Create a new user.
   - `GET /api/users/{userId}`: Get user details.
   - ...

2. **Patient Management:**
   - `POST /api/patients`: Add a new patient.
   - `GET /api/patients/{patientId}`: Retrieve patient information.
   - ...

3. **Doctor Management:**
   - `POST /api/doctors`: Add a new doctor.
   - `GET /api/doctors/{doctorId}`: Retrieve doctor information.
   - ...

4. **Appointment Management:**
   - `POST /api/appointments`: Schedule a new appointment.
   - `GET /api/appointments/{appointmentId}`: Retrieve appointment details.
   - ...

5. **Invoice Management:**
   - `POST /api/invoices`: Generate a new invoice.
   - `GET /api/invoices/{invoiceId}`: Retrieve invoice details.
   - ...

6. **Hospital Information:**
   - `POST /api/hospitals`: Add a new hospital.
   - `GET /api/hospitals/{hospitalId}`: Retrieve hospital details.
   - ...

7. **Prescription Handling:**
   - `POST /api/prescriptions`: Create a new prescription.
   - `GET /api/prescriptions/{prescriptionId}`: Retrieve prescription details.
   - ...

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/hospital-management-system.git

   cd hospital-management-system

   ./mvnw spring-boot:run

   ## Contributing

Contributions are welcome! If you'd like to contribute to the project, please follow the [Contributing Guidelines](CONTRIBUTING.md).

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

- The project is built using [Spring Boot](https://spring.io/projects/spring-boot) for the backend.
- [Angular](https://angular.io/) is used for the frontend user interface.
- The database is managed with [Hibernate](https://hibernate.org/) as the ORM tool.
- [Thymeleaf](https://www.thymeleaf.org/) is used for server-side templating.

## Contact

For any inquiries or feedback, feel free to contact us at [your.email@example.com](mailto:your.email@example.com).

---

**Note:** Customize the contact email and other details as needed.


