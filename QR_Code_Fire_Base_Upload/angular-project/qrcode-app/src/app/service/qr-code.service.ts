import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product";

@Injectable({
  providedIn: 'root'
})
export class QrCodeService {
  URL = "http://localhost:8080/qr"

  constructor(private http: HttpClient) {
  }

  encode(product: Product): Observable<any> {
    return this.http.get<any>(`${this.URL}/encode`);
  }

  decode(type: string, formData: FormData, link: string): Observable<Product> {
    return this.http.post<Product>(`${this.URL}/decode?type=${type}&link=${link}`, formData);
  }

  check2QRCodes(formData: FormData): Observable<any> {
    return this.http.post<any>(`${this.URL}/check`, formData);
  }

}
