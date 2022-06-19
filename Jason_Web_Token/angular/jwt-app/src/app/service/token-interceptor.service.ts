import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  token: any;

  constructor(private tokenStorageService: TokenStorageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authenticate = req;
    if (this.tokenStorageService.getAccount() != null) {
      this.token = this.tokenStorageService.getAccount().token;
    } else {
      this.token = ''
    }
    if (this.token != null) {
      authenticate = req.clone({
        setHeaders: {
          Authorization: `Bearer ${this.token}`,
          'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
        }
      })
    }
    return next.handle(authenticate);
  }
}
