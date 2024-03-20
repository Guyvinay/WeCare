import { Component, OnInit } from '@angular/core';
import { Department } from '../../interfaces/department';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  departments: Department[] = [];

  constructor() { }

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
