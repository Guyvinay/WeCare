import { Routes } from '@angular/router';
import { SignInComponent } from './utility/sign-in/sign-in.component';
import { SignUpComponent } from './utility/sign-up/sign-up.component';
import { AdminComponent } from './admin/admin/admin.component';
import { HospitalComponent } from './hospital/hospital/hospital.component';
import { PatientComponent } from './patient/patient/patient.component';
import { DashboardComponent } from './homepage/dashboard/dashboard.component';

export const routes: Routes = [
    {path:"dashboard", component:DashboardComponent},
    {path:"", redirectTo:"dashboard", pathMatch:'full'},
    {path:"sign-in", component:SignInComponent},
    {path:"sign-up", component:SignUpComponent},
    {path:"admin", component:AdminComponent},
    {path:"hospital", component:HospitalComponent},
    {path:"patient", component:PatientComponent},
];