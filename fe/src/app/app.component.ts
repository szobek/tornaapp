import { Component } from '@angular/core';
import { CallService } from './call.service';
import { HttpClientModule, HttpHeaders } from '@angular/common/http';
import { first } from 'rxjs';
import { User } from './user_model';
import { AccountService } from './account.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  name:string ="";
  user: User |null;
  
  constructor(call: CallService, ac:AccountService){
    this.user = ac.userSubject.getValue()
    call.loginFromFrontend().subscribe({
      next: (res: any) => {
        this.name = res[0].first_name + res[0].last_name
        console.log(res[0])
        ac.userSubject.next(new User(res[0].first_name,res[0].last_name))
        this.user = ac.userSubject.getValue()
    console.log(this.user)
        
      },
      error: (err: any) => {
        console.error(err);
      },
      complete: () => {
        console.log('complete http');
      },
    });
  }
}

