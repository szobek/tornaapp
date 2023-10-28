import { Component, OnInit } from '@angular/core';
import { CallService } from './call.service';
import { User } from './user_model';
import { AccountService } from './account.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  name:string ="";
  user: User |null;
  
  
  constructor(private call: CallService,private ac:AccountService){
    this.user = ac.userSubject.getValue()
    
  }
  ngOnInit(): void {
    this.call.loginFromFrontend().subscribe({
      next: (res: any) => {
        this.name = res[0].first_name + res[0].last_name
        console.log(res[0])
        const user_data = {
          firstName:res[0].first_name,
          lastName:res[0].last_name,
          phone:res[0].phone
        }
        this.ac.userSubject.next(new User(user_data))
        this.user = this.ac.userSubject.getValue()
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

