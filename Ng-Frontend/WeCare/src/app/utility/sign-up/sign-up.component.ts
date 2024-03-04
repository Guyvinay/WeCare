import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PatientDTO } from '../../interfaces/patient';
import { DoctorDTO, SignUpDTO } from '../../interfaces/doctor';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent implements OnInit {

  ngOnInit(): void {
  }
  
  selectedRole:string='';

/*
  patientDTO:PatientDTO={
    email: '',
    passWord: '',
    profile_picture: '',
    role: '',
    name: '',
    gender: '',
    dateOfBirth: '',
    mobile: '',
    address:{
          locality: '',
          city: '',
          zip_code: 0,
          state: '',
          country: ''
    }
  }

  doctorDTO:DoctorDTO={
    email: '',
    passWord: '',
    profile_picture: '',
    role: '',
    name: '',
    department: '',
    gender: '',
    mobile: '',
    qualification: '',
    availability: '',
    address: {
      locality: '',
      city: '',
      zip_code: 0,
      state: '',
      country: ''
    },
  }

  */

  signUpDTO:SignUpDTO={
    email: '',
    passWord: '',
    profile_picture: '',
    role: '',
    firstName: '',
    lastName: '',
    department: '',
    gender: '',
    mobile: '',
    qualification: '',
    availability: '',
    dateOfBirth: '',
    address: {
      locality: '',
      city: '',
      zip_code: 0,
      state: '',
      country: ''
    },
  }

  onRoleChange(){
    this.signUpDTO.role = this.selectedRole;
  }
  onSubmit() :void {
    if(this.selectedRole=='DOCTOR'){
      
      let doctorDTO:DoctorDTO={
        email: this.signUpDTO.email,
        passWord: this.signUpDTO.passWord,
        profile_picture: this.signUpDTO.profile_picture,
        role: this.signUpDTO.role,
        name: this.signUpDTO.firstName+" "+this.signUpDTO.lastName,
        department: this.signUpDTO.department,
        gender: this.signUpDTO.gender,
        mobile: this.signUpDTO.mobile,
        qualification: this.signUpDTO.qualification,
        availability: "AVAILABLE",
        address: {
          locality: this.signUpDTO.address.locality,
              city: this.signUpDTO.address.city,
              zip_code: this.signUpDTO.address.zip_code,
              state: this.signUpDTO.address.state,
              country: this.signUpDTO.address.country
        },
      }
      console.log(doctorDTO)

    }
    else if(this.selectedRole=='PATIENT'){

      let patientDTO:PatientDTO={
        email: this.signUpDTO.email,
        passWord: this.signUpDTO.passWord,
        profile_picture: this.signUpDTO.profile_picture,
        role: this.signUpDTO.role,
        name: this.signUpDTO.firstName+" "+this.signUpDTO.lastName,
        gender: this.signUpDTO.gender,
        dateOfBirth: this.signUpDTO.dateOfBirth,
        mobile: this.signUpDTO.mobile,
        address:{
              locality: this.signUpDTO.address.locality,
              city: this.signUpDTO.address.city,
              zip_code: this.signUpDTO.address.zip_code,
              state: this.signUpDTO.address.state,
              country: this.signUpDTO.address.country
        }
      }

      console.log(patientDTO);
    }
  }
}