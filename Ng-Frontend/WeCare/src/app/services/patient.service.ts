import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient, PatientDTO } from '../interfaces/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private basePatientUrl:string = "http://localhost:8888/patients";

  constructor(
    private httpClient:HttpClient
  ) { }

  registerPatient(patient:PatientDTO):Observable<Patient>{
    return this.httpClient.post<Patient>( this.basePatientUrl , patient);
  }

  getAllPatients():Observable<Patient[]>{
    return this.httpClient.get<Patient[]>(this.basePatientUrl);
  }

}
