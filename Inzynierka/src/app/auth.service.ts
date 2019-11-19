import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { LoginResult } from './models/login-result';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router) { }

  public get isSignedIn() {
    const token = localStorage.getItem('token');

    return (!!token);
  }

  public getAuthorizationHeaders(): HttpHeaders {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.getApiToken()}`
    });

    return headers;
  }

  public getUsername(): string {
    try {
      const decodedToken = jwt_decode(this.getApiToken());
      return decodedToken.sub;
    }
    catch (Error) {
      return '';
    }

  }

  public signOut(): void {
    localStorage.removeItem('token');
  }

  public getApiToken(): string {
    return localStorage.getItem('token');
  }

  public setApiToken(loginResult: LoginResult): void {
    localStorage.setItem('token', loginResult.token);
  }
}
