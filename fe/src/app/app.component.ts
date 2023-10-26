import { Component } from '@angular/core';
import { CallService } from './call.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(call: CallService){
    call.test();
  }
}
