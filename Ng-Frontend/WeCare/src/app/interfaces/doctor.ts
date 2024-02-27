import { Address, AddressDTO } from "./address";

export interface Doctor {
    doctor_id:string
    email:string;
    passWord:string; 
    profile_picture:string;
    role:string;
    name:string;
	department:string;
	gender:string;
	mobile:string;
    availability:string;
    qualification:string;
    address:Address
}
export interface DoctorDTO {
    email:string;
    passWord:string; 
    profile_picture:string;
    role:string;
    name:string;
	department:string;
	gender:string;
	mobile:string;
    availability:string
    qualification:string
    address:AddressDTO
}