import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';


@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
    constructor(private authService: AuthService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add authorization header with basic auth credentials if available
        if (this.authService.isSignedIn) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${this.authService.getApiToken()}`
                }
            });
        }
        return next.handle(request);
    }
}
