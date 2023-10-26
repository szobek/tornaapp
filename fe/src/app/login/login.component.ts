import { Component } from '@angular/core';
import { CallService } from '../call.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
constructor(call: CallService){
  call.test();
}
}
