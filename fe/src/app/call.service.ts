import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CallService {
  
  constructor(private http: HttpClient) { }



loginFromFrontend(){
  const httpBody = new HttpParams()
      .set('email', 'kunszt.norbert@gmail.com')
      .set('password', 'rrrrrr');
  const url = "http://127.0.0.1:8081/tornaapp/login";
  return this.http.post(url,httpBody)
}
  
  
}
