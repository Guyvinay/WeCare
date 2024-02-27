export interface Address {
    address_id:string; 
    locality:string;
    city:string;
    zip_code:number;
    state:string;
    country:string;
}
export interface AddressDTO {
    locality:string;
    city:string;
    zip_code:number;
    state:string;
    country:string;
}