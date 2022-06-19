import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Request} from "../model/request";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {
  URL = "http://localhost:8080"

  constructor(private http: HttpClient) {
  }

  login(request: Request): Observable<any> {
    return this.http.post(`${this.URL}/login`, request);
  }

  hello(token: string): Observable<any> {
    return this.http.get(`${this.URL}/hello`, {responseType: "text"});
  }
}
