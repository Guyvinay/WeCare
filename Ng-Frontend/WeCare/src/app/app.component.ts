import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { initFlowbite } from 'flowbite';
import { LoaderService } from './services/loader.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HttpClientModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'web-app';
  isLoading: boolean = false;

  isBubbleLoads:boolean=false;

  bubbleLoadsTrue(){
    this.isBubbleLoads=true;
  }
  bubbleLoadsFalse(){
    this.isBubbleLoads=false;
  }
  
  ngOnInit(): void {
    initFlowbite();
  }

  constructor(
    private loaderService:LoaderService
  ){}
}
