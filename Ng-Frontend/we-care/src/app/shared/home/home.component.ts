import { Component, OnInit } from '@angular/core';
import { Department } from '../../core/interface/department';

@Component({
  selector: 'wc-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  departments: Department[] = [];

  ngOnInit(): void {
    this.departments = [
      { iconUrl: '..//..//../assets/department-icons/cardiology_icon.svg', name: 'Cardiology', doctorsUrl:"" },
      { iconUrl: '..//..//../assets/department-icons/neurology.svg', name: 'Neurology', doctorsUrl:"" },
      { iconUrl: '..//..//../assets/department-icons/dentistry.svg', name: 'Dentistry', doctorsUrl:"" },
      { iconUrl: '..//..//../assets/department-icons/dermatology.svg', name: 'Dermatology', doctorsUrl:"" },
      { iconUrl: '..//..//../assets/department-icons/ent.svg', name: 'ENT', doctorsUrl:"" },
      { iconUrl: '..//..//../assets/department-icons/psychiatry.svg', name: 'Psychiatry', doctorsUrl:"" },
      { iconUrl: '..//..//../assets/department-icons/paediatricurology.svg', name: 'Paediatrics', doctorsUrl:"" },
      { iconUrl: '..//..//../assets/department-icons/cardiology_icon.svg', name: 'Cardiology', doctorsUrl:"" },
      { iconUrl: '..//..//../assets/department-icons/neurology.svg', name: 'Neurology', doctorsUrl:"" },
      { iconUrl: '..//..//../assets/department-icons/dentistry.svg', name: 'Dentistry', doctorsUrl:"" },
      { iconUrl: '..//..//../assets/department-icons/dermatology.svg', name: 'Dermatology', doctorsUrl:"" },
      { iconUrl: '..//..//../assets/department-icons/ent.svg', name: 'ENT', doctorsUrl:"" }
    ];
  }

  



}
