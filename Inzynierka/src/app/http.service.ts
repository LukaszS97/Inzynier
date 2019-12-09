import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { User } from './models/User';
import { Employee } from './models/employee';
import { LoginResult } from './models/login-result';
import { AuthService } from './auth.service';
import { Joboffer } from './models/joboffer';
import { CandidateEmployee } from './models/candidate-employee';
import { UserRole } from './models/user-role';
import { JobName } from './models/job-name';
import { Email } from './models/email';
import { WorkSchedule } from './models/work-schedule';
import { Task } from './models/task';
import { Raport } from './models/raport';




@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  // get z lub bez parametru *************************************************************************************************8
  public httpGetRequest(actionName: string, params: string = ''): Promise<any> {
    return new Promise((resolve) => {
      this.http.get<any>(`http://localhost:8080/${actionName}/${params}`, {observe: 'response'})
        .subscribe((response: HttpResponse<any>) => {
          if (response.status >= 200 && response.status < 300) {
            resolve(response.body);
          } else {
            resolve(false);
          }
        });
    });
  }
  async getRaportsOneEmployee(): Promise<Array<Raport>> {
    return await this.httpGetRequest('api/taskReport/task');
  }

  async getAllRaport(): Promise<Array<Raport>> {
    return await this.httpGetRequest('api/taskReport');
  }

  async getTask(): Promise<Array<Task>> {
    return await this.httpGetRequest('api/user/task');
  }

  async getTasks(): Promise<Array<Task>> {
    return await this.httpGetRequest('api/task');
  }

  async getGraphicList(data): Promise<Array<Employee>> {
    return await this.httpGetRequest('api/employee/employees', data);
  }

  async getGraphic(): Promise<Array<WorkSchedule>> {
    return await this.httpGetRequest('api/workSchedule');
  }

  async getUsers(): Promise<Array<User>> {
    return await this.httpGetRequest('api/user/users');
  }

  async getJoboffers(): Promise<Array<Joboffer>> {
    return await this.httpGetRequest('api/joboffer');
  }

  async getCV(): Promise<Array<CandidateEmployee>> {
    return await this.httpGetRequest('api/applicationForm');
  }

  async getId(): Promise<any> {
    return await this.httpGetRequest('api/user');
  }

  async getUserRole(): Promise<any> {
    return await this.httpGetRequest('api/user/userRole');
  }

  async getEmployee(idUser): Promise<any> {
    return await this.httpGetRequest('api/employee', idUser);
  }

  // post, który coś zwraca ************************************************************************************************
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
  async addRaport(raport: Raport): Promise<any> {
    return await this.httpPostRequest('api/taskReport', raport);
  }

  async addTasks(task: Task): Promise<any> {
    return await this.httpPostRequest('api/task', task);
  }

  async sendEmail(email: Email): Promise<any> {
    return await this.httpPostRequest('sendMail', email);
  }

  async login(user: User): Promise<LoginResult> {
    return await this.httpPostRequest('authenticate', user);
  }

  async addJoboffer(joboffer: Joboffer): Promise<string> {
    return await this.httpPostRequest('api/joboffer', joboffer);
  }

  // put, który cos zwraca z parametrem ************************************************************************************************
  public httpPutRequest(actionName: string, params: string = '', requestData: any = {}): Promise<any> {
    return new Promise((resolve) => {
      this.http.put<any>(`http://localhost:8080/${actionName}/${params}`, requestData, { observe: 'response' })
        .subscribe((response: HttpResponse<any>) => {
          if (response.status >= 200 && response.status < 300) {
            resolve(response.body);
          } else {
            resolve(false);
          }
        });
    });
  }

  async putEmployee( idUser, employee: Employee): Promise<string> {
    return await this.httpPutRequest(`api/employee`, idUser, employee);
  }

  // post z parametrem , który coś zwraca ************************************************************************************************
  public httpPostRequestParm(actionName: string, params: string = '', requestData: any = {}): Promise<any> {
    return new Promise((resolve) => {
      this.http.post<any>(`http://localhost:8080/${actionName}/${params}`, requestData, { observe: 'response' })
        .subscribe((response: HttpResponse<any>) => {
          if (response.status >= 200 && response.status < 300) {
            resolve(response.body);
          } else {
            resolve(false);
          }
        });
    });
  }

  async addGraphic(grafik: WorkSchedule, email): Promise<string> {
    console.log(grafik);
    return await this.httpPostRequestParm('api/workSchedule', email, grafik);
  }

  async addUser(user: User, role: UserRole): Promise<string> {
    return await this.httpPostRequestParm('register', role.userRole,  user);
  }

  async addCandidate(candidate: CandidateEmployee, job: JobName): Promise<string> {
    return await this.httpPostRequestParm('api/applicationForm', job.position, candidate);
  }


  // usuwanie danych  ************************************************************************************************
  public httpDeleteRequest(actionName: string, params: string = ''): Promise<any> {
    return new Promise((resolve) => {
      this.http.delete<any>(`http://localhost:8080/${actionName}/${params}`, {observe: 'response'})
        .subscribe((response: HttpResponse<any>) => {
          if (response.status >= 200 && response.status < 300) {
            resolve(response.body);
          } else {
            resolve(false);
          }
        });
    });
  }

  async deleteEmployee(idUser): Promise<any> {
    return await this.httpDeleteRequest('api/employee', idUser);
  }
}
