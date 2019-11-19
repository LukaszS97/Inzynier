import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const isSignedIn = this.authService.isSignedIn;

    if (isSignedIn && state.url === '/log') {
      this.router.navigate(['personal-data']);
      return true;
    } else if (!isSignedIn && state.url === '/log') {
      return true;
    } else if (isSignedIn) {
      return true;
    } else {
      return false;
    }
  }
}
