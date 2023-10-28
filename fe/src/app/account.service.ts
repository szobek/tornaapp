import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from './user_model';
import { UserData } from "./user-data";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  public userSubject: BehaviorSubject<User > ;
  constructor() { 
    const data:UserData  = {firstName:"",lastName:"",phone:""}
    this.userSubject = new BehaviorSubject(new User(data));
  }
}
