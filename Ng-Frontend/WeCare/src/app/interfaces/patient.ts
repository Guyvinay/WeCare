import { Address, AddressDTO } from "./address";

export interface Patient {
    patient_id:string
    email:string;
    passWord:string; 
    profile_picture:string;
    role:string;
    name:string;
	gender:string;
	dateOfBirth:string;
	mobile:string;
    address:Address
}

export interface PatientDTO {
    email:string;
    passWord:string; 
    profile_picture:string;
    role:string;
    name:string;
	gender:string;
	dateOfBirth:string;
	mobile:string;
    address:AddressDTO;
}
