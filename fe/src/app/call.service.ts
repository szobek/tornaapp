import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CallService {
  
  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  next(data:any) {
    console.log(data)
  }
end(){}

t2(){
  const httpBody = new HttpParams()
      .set('email', 'kunszt.norbert@gmail.com')
      .set('password', 'rrrrrrt');
  const url = "http://127.0.0.1:8000/tornaapp/login";
  return this.http.post(url,httpBody)
}

  test() {

    
    this.t2().subscribe({
      next: (response) => {
        console.log(response)
      },
      error: (error) => {
          alert('There was an error in retrieving data from the server');
      }
  });


  }

  
  
}
