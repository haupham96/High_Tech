import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product";
import firebase from "firebase";
import Blob = firebase.firestore.Blob;

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  URL = "http://localhost:8080/product";

  constructor(private http: HttpClient) {
  }

  getAllProducts(): Observable<any> {
    return this.http.get<any>(this.URL);
  }

  findById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.URL}/${id}`);
  }

  getLatestProduct(): Observable<Product> {
    return this.http.get<Product>(`${this.URL}/latest`);
  }

  createProduct(product: Product): Observable<any> {
    // @ts-ignore
    return this.http.post<any>(`${this.URL}`, product, {responseType: "blob"});
  }

  editProduct(product: Product): Observable<any> {
    // @ts-ignore
    return this.http.put<any>(`${this.URL}/${product.id}`, product, {responseType: "blob"});
  }

  deleteProduct(id: number): Observable<Product> {
    return this.http.delete<Product>(`${this.URL}/${id}`);
  }

}
