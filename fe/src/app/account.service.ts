import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from './user_model';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  public userSubject: BehaviorSubject<User > ;
  constructor() { 
    this.userSubject = new BehaviorSubject(new User("",""));
  }
}
