package com.weCare.test;

/*

Medications
[
{
  "medication_name": "Ibuprofen",
  "medication_description": "Pain and fever reducer",
  "medication_manufacturer": "Johnson & Johnson",
  "medication_price": 8.99,
  "medication_quantity": 200,
  "expirationDate": "2025-11-15"
},
{
  "medication_name": "Ibuprofen",
  "medication_description": "Pain and fever reducer",
  "medication_manufacturer": "Johnson & Johnson",
  "medication_price": 8.99,
  "medication_quantity": 200,
  "expirationDate": "2025-11-15"
},
{
  "medication_name": "Lisinopril",
  "medication_description": "Blood pressure medication",
  "medication_manufacturer": "AstraZeneca",
  "medication_price": 15.50,
  "medication_quantity": 30,
  "expirationDate": "2024-05-22"
},
{
  "medication_name": "Omeprazole",
  "medication_description": "Acid reflux and heartburn relief",
  "medication_manufacturer": "GlaxoSmithKline",
  "medication_price": 12.75,
  "medication_quantity": 60,
  "expirationDate": "2025-12-01"
},
{
  "medication_name": "Simvastatin",
  "medication_description": "Cholesterol-lowering medication",
  "medication_manufacturer": "Merck",
  "medication_price": 18.99,
  "medication_quantity": 90,
  "expirationDate": "2024-04-10"
},
{
  "medication_name": "Cetirizine",
  "medication_description": "Antihistamine for allergies",
  "medication_manufacturer": "Novartis",
  "medication_price": 7.25,
  "medication_quantity": 120,
  "expirationDate": "2025-09-08"
},
{
  "medication_name": "Metformin",
  "medication_description": "Diabetes medication",
  "medication_manufacturer": "Pfizer",
  "medication_price": 11.99,
  "medication_quantity": 100,
  "expirationDate": "2024-02-28"
},
{
  "medication_name": "Atorvastatin",
  "medication_description": "Statin medication for heart health",
  "medication_manufacturer": "Sanofi",
  "medication_price": 22.50,
  "medication_quantity": 30,
  "expirationDate": "2025-10-15"
},
{
  "medication_name": "Diazepam",
  "medication_description": "Anxiety and muscle spasms medication",
  "medication_manufacturer": "Roche",
  "medication_price": 14.99,
  "medication_quantity": 50,
  "expirationDate": "2024-06-05"
},
{
  "medication_name": "Diazepam",
  "medication_description": "Anxiety and muscle spasms medication",
  "medication_manufacturer": "Roche",
  "medication_price": 14.99,
  "medication_quantity": 50,
  "expirationDate": "2024-06-05"
},
{
  "medication_name": "Acetaminophen",
  "medication_description": "Pain reliever and fever reducer",
  "medication_manufacturer": "Bayer",
  "medication_price": 9.25,
  "medication_quantity": 150,
  "expirationDate": "2025-11-30"
},
{
  "medication_name": "Levothyroxine",
  "medication_description": "Thyroid hormone replacement",
  "medication_manufacturer": "Abbott",
  "medication_price": 16.99,
  "medication_quantity": 90,
  "expirationDate": "2026-03-20"
}
]

Hospitals 
[
{
  "hospital_name": "Mount Sinai Hospital",
  "contact": "212-241-6500",
  "description": "Renowned medical center in New York City",
  "address": {
    "locality": "Madison Avenue",
    "city": "New York",
    "zip_code": 10029,
    "state": "New York",
    "country": "USA"
  }
},
{
  "hospital_name": "Johns Hopkins Hospital",
  "contact": "410-955-5000",
  "description": "Leading healthcare institution in Baltimore",
  "address": {
    "locality": "North Wolfe Street",
    "city": "Baltimore",
    "zip_code": 21287,
    "state": "Maryland",
    "country": "USA"
  }
},
{
  "hospital_name": "Singapore General Hospital",
  "contact": "+65 6222 3322",
  "description": "Flagship hospital in Singapore",
  "address": {
    "locality": "Outram Road",
    "city": "Singapore",
    "zip_code": 169608,
    "state": "Central Region",
    "country": "Singapore"
  }
},
{
  "hospital_name": "Charité – Universitätsmedizin Berlin",
  "contact": "+49 30 45050",
  "description": "Prominent hospital in Berlin, Germany",
  "address": {
    "locality": "Charitéplatz",
    "city": "Berlin",
    "zip_code": 10117,
    "state": "Berlin",
    "country": "Germany"
  }
},
{
  "hospital_name": "Apollo Hospitals",
  "contact": "1860-500-1066",
  "description": "Largest healthcare group in India",
  "address": {
    "locality": "Greams Road",
    "city": "Chennai",
    "zip_code": 600006,
    "state": "Tamil Nadu",
    "country": "India"
  }
}
]

Doctors
[
{
  "email": "grace.wong@gmail.com",
  "userName": "gracewong",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Dr-Grace-Wong.jpg",
  "role": "DOCTOR",
  "doctor_name": "Dr. Grace Wong",
  "department": "PSYCHIATRY",
  "qualification": "MD in Cardiology",
  "mobile": "9876543210",
  "address": {
    "locality": "Cardio Plaza",
    "city": "San Francisco",
    "zip_code": 94105,
    "state": "California",
    "country": "USA"
  }
},
{
  "email": "alex.jones@gmail.com",
  "userName": "alexjones",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Dr-Alex-Jones.jpg",
  "role": "DOCTOR",
  "doctor_name": "Dr. Alex Jones",
  "department": "HOMEOPATHY",
  "qualification": "MS in Orthopedic Surgery",
  "mobile": "8765432109",
  "address": {
    "locality": "Bone Avenue",
    "city": "Chicago",
    "zip_code": 60611,
    "state": "Illinois",
    "country": "USA"
  }
},
{
  "email": "lucia.smith@gmail.com",
  "userName": "luciasmith",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Dr-Lucia-Smith.jpg",
  "role": "DOCTOR",
  "doctor_name": "Dr. Lucia Smith",
  "department": "DERMATOLOGY",
  "qualification": "Dermatology Specialist",
  "mobile": "7654321098",
  "address": {
    "locality": "SkinCare Lane",
    "city": "Miami",
    "zip_code": 33101,
    "state": "Florida",
    "country": "USA"
  }
},
{
  "email": "robert.anderson@gmail.com",
  "userName": "robertanderson",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Dr-Robert-Anderson.jpg",
  "role": "DOCTOR",
  "doctor_name": "Dr. Robert Anderson",
  "department": "NEUROLOGY",
  "qualification": "Neurologist",
  "mobile": "9876123450",
  "address": {
    "locality": "Brain Street",
    "city": "Boston",
    "zip_code": 02101,
    "state": "Massachusetts",
    "country": "USA"
  }
},
{
  "email": "emma.johnson@gmail.com",
  "userName": "emmajohnson",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Dr-Emma-Johnson.jpg",
  "role": "DOCTOR",
  "doctor_name": "Dr. Emma Johnson",
  "department": "PAEDIATRICS",
  "qualification": "Pediatrician",
  "mobile": "8765123456",
  "address": {
    "locality": "Childhood Lane",
    "city": "Denver",
    "zip_code": 80202,
    "state": "Colorado",
    "country": "USA"
  }
},
{
  "email": "sara.ent@gmail.com",
  "userName": "saraent",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Dr-Sara-ENT.jpg",
  "role": "DOCTOR",
  "doctor_name": "Dr. Sara Johnson",
  "department": "ENT",
  "qualification": "ENT Specialist",
  "mobile": "9876123456",
  "address": {
    "locality": "Ear & Throat Lane",
    "city": "Seattle",
    "zip_code": 98101,
    "state": "Washington",
    "country": "USA"
  }
},
{
  "email": "mike.dentist@gmail.com",
  "userName": "mikedentist",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Dr-Mike-Dentist.jpg",
  "role": "DOCTOR",
  "doctor_name": "Dr. Mike Davis",
  "department": "DENTISTRY",
  "qualification": "Dentist",
  "mobile": "8765432109",
  "address": {
    "locality": "Smile Street",
    "city": "Orlando",
    "zip_code": 32801,
    "state": "Florida",
    "country": "USA"
  }
},
{
  "email": "lisa.derm@gmail.com",
  "userName": "lisaderm",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Dr-Lisa-Dermatologist.jpg",
  "role": "DOCTOR",
  "doctor_name": "Dr. Lisa Taylor",
  "department": "DERMATOLOGY",
  "qualification": "Dermatologist",
  "mobile": "7654321098",
  "address": {
    "locality": "SkinCare Boulevard",
    "city": "Los Angeles",
    "zip_code": 90001,
    "state": "California",
    "country": "USA"
  }
},
{
  "email": "lisa.derm@gmail.com",
  "userName": "lisaderm",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Dr-Lisa-Dermatologist.jpg",
  "role": "DOCTOR",
  "doctor_name": "Dr. Lisa Taylor",
  "department": "DERMATOLOGY",
  "qualification": "Dermatologist",
  "mobile": "7654321098",
  "address": {
    "locality": "SkinCare Boulevard",
    "city": "Los Angeles",
    "zip_code": 90001,
    "state": "California",
    "country": "USA"
  }
},
{
  "email": "olivia.neuro@gmail.com",
  "userName": "olivianeuro",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Dr-Olivia-Neurologist.jpg",
  "role": "DOCTOR",
  "doctor_name": "Dr. Olivia Brown",
  "department": "NEUROLOGY",
  "qualification": "Neurologist",
  "mobile": "8765123456",
  "address": {
    "locality": "Brain Avenue",
    "city": "San Diego",
    "zip_code": 92101,
    "state": "California",
    "country": "USA"
  }
}
]

Patients
[
{
  "email": "olivia@gmail.com",
  "userName": "olivia",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Olivia.jpg",
  "role": "PATIENT",
  "patient_name": "Olivia Johnson",
  "patient_gender": "FEMALE",
  "date_of_birth": "1990-05-15",
  "mobile": "8765432109",
  "address": {
    "locality": "Rose Lane",
    "city": "Los Angeles",
    "zip_code": 90001,
    "state": "California",
    "country": "USA"
  }
},
{
  "email": "samuel@gmail.com",
  "userName": "samuel",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Samuel.jpg",
  "role": "PATIENT",
  "patient_name": "Samuel Taylor",
  "patient_gender": "MALE",
  "date_of_birth": "1985-08-20",
  "mobile": "7654321098",
  "address": {
    "locality": "Evergreen Street",
    "city": "New York",
    "zip_code": 10001,
    "state": "New York",
    "country": "USA"
  }
},
{
  "email": "emma@gmail.com",
  "userName": "emma",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Emma.jpg",
  "role": "PATIENT",
  "patient_name": "Emma Davis",
  "patient_gender": "FEMALE",
  "date_of_birth": "1995-03-25",
  "mobile": "9876123450",
  "address": {
    "locality": "Sunflower Lane",
    "city": "Chicago",
    "zip_code": 60601,
    "state": "Illinois",
    "country": "USA"
  }
},
{
  "email": "nathan@gmail.com",
  "userName": "nathan",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Nathan.jpg",
  "role": "PATIENT",
  "patient_name": "Nathan Smith",
  "patient_gender": "MALE",
  "date_of_birth": "1998-11-12",
  "mobile": "8765123456",
  "address": {
    "locality": "Maple Avenue",
    "city": "San Francisco",
    "zip_code": 94105,
    "state": "California",
    "country": "USA"
  }
},
{
  "email": "isabella@gmail.com",
  "userName": "isabella",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Isabella.jpg",
  "role": "PATIENT",
  "patient_name": "Isabella White",
  "patient_gender": "FEMALE",
  "date_of_birth": "1992-07-08",
  "mobile": "7654321234",
  "address": {
    "locality": "Tulip Street",
    "city": "Seattle",
    "zip_code": 98101,
    "state": "Washington",
    "country": "USA"
  }
},
{
  "email": "daniel@gmail.com",
  "userName": "daniel",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Daniel.jpg",
  "role": "PATIENT",
  "patient_name": "Daniel Brown",
  "patient_gender": "MALE",
  "date_of_birth": "1987-04-18",
  "mobile": "9876543210",
  "address": {
    "locality": "Oak Street",
    "city": "Denver",
    "zip_code": 80202,
    "state": "Colorado",
    "country": "USA"
  }
},
{
  "email": "ava@gmail.com",
  "userName": "ava",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Ava.jpg",
  "role": "PATIENT",
  "patient_name": "Ava Miller",
  "patient_gender": "FEMALE",
  "date_of_birth": "1994-12-30",
  "mobile": "8765432109",
  "address": {
    "locality": "Rosewood Lane",
    "city": "Boston",
    "zip_code": 02101,
    "state": "Massachusetts",
    "country": "USA"
  }
},
{
  "email": "leo@gmail.com",
  "userName": "leo",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Leo.jpg",
  "role": "PATIENT",
  "patient_name": "Leo Anderson",
  "patient_gender": "MALE",
  "date_of_birth": "1999-02-05",
  "mobile": "8765123456",
  "address": {
    "locality": "Leo Lane",
    "city": "San Diego",
    "zip_code": 92101,
    "state": "California",
    "country": "USA"
  }
},
{
  "email": "mia@gmail.com",
  "userName": "mia",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Mia.jpg",
  "role": "PATIENT",
  "patient_name": "Mia Roberts",
  "patient_gender": "FEMALE",
  "date_of_birth": "1993-06-14",
  "mobile": "9876123450",
  "address": {
    "locality": "Magnolia Lane",
    "city": "Orlando",
    "zip_code": 32801,
    "state": "Florida",
    "country": "USA"
  }
},
{
  "email": "sophie@gmail.com",
  "userName": "sophie",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Sophie.jpg",
  "role": "PATIENT",
  "patient_name": "Sophie Williams",
  "patient_gender": "FEMALE",
  "date_of_birth": "1992-04-15",
  "mobile": "7654321234",
  "address": {
    "locality": "Lavender Lane",
    "city": "Seattle",
    "zip_code": 98101,
    "state": "Washington",
    "country": "USA"
  }
},
{
  "email": "ethan@gmail.com",
  "userName": "ethan",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Ethan.jpg",
  "role": "PATIENT",
  "patient_name": "Ethan Davis",
  "patient_gender": "MALE",
  "date_of_birth": "1990-10-08",
  "mobile": "9876543210",
  "address": {
    "locality": "Oakwood Street",
    "city": "New York",
    "zip_code": 10001,
    "state": "New York",
    "country": "USA"
  }
},
{
  "email": "mia.string@gmail.com",
  "userName": "miastring",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Mia-String.jpg",
  "role": "PATIENT",
  "patient_name": "Mia String",
  "patient_gender": "FEMALE",
  "date_of_birth": "1995-08-22",
  "mobile": "8765432109",
  "address": {
    "locality": "Melody Lane",
    "city": "Chicago",
    "zip_code": 60601,
    "state": "Illinois",
    "country": "USA"
  }
},
{
  "email": "noah.string@gmail.com",
  "userName": "noahstring",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Noah-String.jpg",
  "role": "PATIENT",
  "patient_name": "Noah String",
  "patient_gender": "MALE",
  "date_of_birth": "1998-01-20",
  "mobile": "7654321098",
  "address": {
    "locality": "Harmony Avenue",
    "city": "San Francisco",
    "zip_code": 94105,
    "state": "California",
    "country": "USA"
  }
},
{
  "email": "olivia.string@gmail.com",
  "userName": "oliviastring",
  "passWord": "string",
  "profile_picture": "https://dummyurl.com/Olivia-String.jpg",
  "role": "PATIENT",
  "patient_name": "Olivia String",
  "patient_gender": "FEMALE",
  "date_of_birth": "1993-05-14",
  "mobile": "9876123450",
  "address": {
    "locality": "Serenade Lane",
    "city": "Los Angeles",
    "zip_code": 90001,
    "state": "California",
    "country": "USA"
  }
}
]

 */