import {Injectable} from '@angular/core';
const TOKEN_KEY = 'TOKEN';
const ACCOUNT_KEY = 'ACCOUNT';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() {
  }

  signOut() {
    window.localStorage.clear();
    window.sessionStorage.clear();
  }

  saveTokenLocalStorage(token: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  saveTokenSessionStorage(token: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  getToken() {
    if (window.localStorage.getItem(TOKEN_KEY) != null) {
      return window.localStorage.getItem(TOKEN_KEY);
    } else {
      return window.sessionStorage.getItem(TOKEN_KEY);
    }
  }

  saveAccountLocalStorage(account) {
    window.localStorage.removeItem(ACCOUNT_KEY);
    window.localStorage.setItem(ACCOUNT_KEY, JSON.stringify(account));
  }

  saveAccountSessionStorage(account) {
    window.sessionStorage.removeItem(ACCOUNT_KEY);
    window.sessionStorage.setItem(ACCOUNT_KEY, JSON.stringify(account));
  }

  getAccount() {
    if (window.localStorage.getItem(ACCOUNT_KEY) != null) {
      return JSON.parse(window.localStorage.getItem(ACCOUNT_KEY));
    } else {
      return JSON.parse(window.sessionStorage.getItem(ACCOUNT_KEY));
    }
  }

}
