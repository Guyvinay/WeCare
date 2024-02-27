import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PatientDTO } from '../../interfaces/patient';
import { DoctorDTO } from '../../interfaces/doctor';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {

  selectedRole:string='';
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

  onRoleChange(){

  }



}
