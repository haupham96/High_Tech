import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {SecurityService} from "../service/security.service";
import {Request} from "../model/request";
import {Response} from "../model/response";
import {Router} from "@angular/router";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-jwt',
  templateUrl: './jwt.component.html',
  styleUrls: ['./jwt.component.css']
})
export class JwtComponent implements OnInit {

  response: Response = {};

  formLogin = new FormGroup({
    username: new FormControl(),
    password: new FormControl()
  });

  constructor(private service: SecurityService, private router: Router,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    if (this.tokenStorageService.getToken()) {
      this.response = this.tokenStorageService.getAccount();
    }
  }

  submit() {
    let request: Request = Object.assign({}, this.formLogin.value);
    this.service.login(request).subscribe(data => {
      this.response = data;
      this.tokenStorageService.saveAccountLocalStorage(this.response);
      this.tokenStorageService.saveTokenLocalStorage(this.response.token);
      alert('success');
    }, error => {
      console.log(error);
      alert('error');
    })
  }

  get username() {
    return this.formLogin.get('username');
  }

  get password() {
    return this.formLogin.get('password');
  }

  hello() {
    this.service.hello(this.response.token).subscribe(data => {
      console.log(data);
    }, err => {
      console.log(err);
    })
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
