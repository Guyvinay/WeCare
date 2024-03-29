import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Patient, PatientDTO } from '../../interfaces/patient';
import { DoctorDTO, SignUpDTO } from '../../interfaces/doctor';
import { PatientService } from '../../services/patient.service';
import { DoctorService } from '../../services/doctor.service';
import { AppComponent } from '../../app.component';
import { partition } from 'rxjs';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent implements OnInit {

  ngOnInit(): void {

  }
  constructor(
    private patientService : PatientService,
    private doctorService : DoctorService,
    private appComponent : AppComponent
  ){}

  selectedRole:string='';

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
    
    this.appComponent.bubbleLoadsTrue();

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
      console.log(doctorDTO);

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
      
      // this.patientService.registerPatient(patientDTO)
      //                     .subscribe({
      //                       next:(patient:Patient)=>{
      //                         console.log(patient);
      //                           this.signUpDTO={
      //                             email: '',
      //                             passWord: '',
      //                             profile_picture: '',
      //                             role: '',
      //                             firstName: '',
      //                             lastName: '',
      //                             department: '',
      //                             gender: '',
      //                             mobile: '',
      //                             qualification: '',
      //                             availability: '',
      //                             dateOfBirth: '',
      //                             address: {
      //                               locality: '',
      //                               city: '',
      //                               zip_code: 0,
      //                               state: '',
      //                               country: ''
      //                             },
      //                           }
      //                       },
      //                       error:(error:any)=>{
      //                         console.log(error);
      //                         this.appComponent.bubbleLoadsFalse()
      //                       },
      //                       complete:()=>{
      //                         this.appComponent.bubbleLoadsFalse()
      //                       }
      //                     })

      // this.patientService.getAllPatients()
      //                     .subscribe({
      //                       next : (patient)=>{
      //                         console.log(patient)
      //                       },
      //                       error :(err:any)=> {
      //                           console.log(err)
      //                       },
      //                       complete:()=>{
      //                         console.log("Completed")
      //                       }
      //                     })
    }
  }
}