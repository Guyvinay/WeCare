import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoaderService {

  constructor(

  ) { }

  isBubbleLoads:boolean=false;

  bubbleLoadsTrue(){
    this.isBubbleLoads=true;
  }
  bubbleLoadsFalse(){
    this.isBubbleLoads=false;
  }


}
