import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './models/User';
import { Employee } from './models/employee';
import { LoginResult } from './models/login-result';
import { AuthService } from './auth.service';
import { Joboffer } from './models/joboffer';
import { CandidateEmployee } from './models/candidate-employee';




@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient, private authService: AuthService) { }

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

  async addUser(user: User): Promise<string> {
    return await this.httpPostRequest('register', user);
  }

  async login(user: User): Promise<LoginResult> {
    return await this.httpPostRequest('authenticate', user);
  }

  async createEmployee(employee: Employee): Promise<string> {
    return await this.httpPostRequest(`api/employee`, employee);
  }

  async addJoboffer(joboffer: Joboffer): Promise<string> {
    return await this.httpPostRequest('api/joboffer', joboffer);
  }

  async addCandidate(candidate: CandidateEmployee): Promise<string> {
    return await this.httpPostRequest('api/applicationForm', candidate);
  }

  async getJoboffers(): Promise<Array<Joboffer>> {
    return await this.httpGetRequest('api/joboffer');
  }

  async getId(): Promise<any> {
    return await this.httpGetRequest('api/user');
<<<<<<< HEAD
=======
  }

  async getEmployee(idUser): Promise<any> {
    return await this.httpGetRequest('api/employee', idUser);
>>>>>>> a645240c737169c939b207ce0f0f8ad7da463faa
  }
}
