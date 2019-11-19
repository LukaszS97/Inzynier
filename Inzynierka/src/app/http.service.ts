import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './models/User';
import { Employee } from './models/employee';
import { LoginResult } from './models/login-result';
import { AuthService } from './auth.service';




@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient, private authService: AuthService) { }
  addUser(user: User): Observable<string> {
    return this.http.post<string>('http://localhost:8080/register', user, { responseType: 'text' as 'json' });
  }

  login(user: User): Observable<LoginResult> {
    return this.http.post<LoginResult>('http://localhost:8080/authenticate', user);
  }

  createEmployee(employee: Employee): Observable<Employee> {
    const authHeaders = this.authService.getAuthorizationHeaders();
    return this.http.post<Employee>(`http://localhost:8080/api/employee`, employee, { headers: authHeaders });
  }
}
