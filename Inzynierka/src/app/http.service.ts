import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './models/User';
import { Employee } from './models/employee';
import { LoginResult } from './models/login-result';
import { AuthService } from './auth.service';
import { Joboffer } from './models/joboffer';




@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient, private authService: AuthService) { }
  addUser(user: User): Observable<string> {
    return this.http.post<string>('http://localhost:8080/register', user, { responseType: 'text' as 'json' });
  }
  //tą uzywac jesli mi nic nie zwraca

  public httpActionRequest(actionName: string, requestData: any = {}): Promise<any> {
    return new Promise((resolve) => {
      this.http.post<any>(`http://localhost:8080/${actionName}`, requestData, { observe: 'response' })
        .subscribe((response: HttpResponse<any>) => {

          if (response.status >= 200 && response.status < 300) {
            resolve(true);
          } else {
            resolve(false);
          }
        });
    });
  }
//get z lub bez parametru
  public httpGetRequest(actionName: string, params: string = ''): Promise<any> {
    return new Promise((resolve) => {
      this.http.get<any>(`http://localhost:8080/${actionName}/${params}`, { observe: 'response' })
        .subscribe((response: HttpResponse<any>) => {
          if (response.status >= 200 && response.status < 300) {
            resolve(response.body);
          } else {
            resolve(false);
          }
        });
    });
  }
// post, który coś zwraca
  public httpPostRequest(actionName: string, requestData: any = {}): Promise<any> {
    return new Promise((resolve) => {
      this.http.post<any>(`http://localhost:8080/${actionName}`, requestData, { observe: 'response' })
        .subscribe((response: HttpResponse<any>) => {
          if (response.status >= 200 && response.status < 300) {
            resolve(response.body);
          } else {
            resolve(false);
          }
        });
    });
  }


  async login(user: User): Promise<LoginResult> {
    return await this.httpPostRequest('authenticate', user);
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(`http://localhost:8080/api/employee`, employee);
  }

  addJoboffer(joboffer: Joboffer): Observable<Joboffer> {
    return this.http.post<Joboffer>('http://localhost:8080/api/joboffer', joboffer, { responseType: 'text' as 'json' });
  }

  async getJoboffers(): Promise<Array<Joboffer>> {
    return await this.httpGetRequest('api/joboffer');
  }
}
