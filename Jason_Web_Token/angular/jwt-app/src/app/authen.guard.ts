import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenStorageService} from "./service/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenGuard implements CanActivate {

  constructor(private tokenStorageService: TokenStorageService, private router: Router) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let currentAccount = this.tokenStorageService.getAccount();
    if (currentAccount != null) {
      let roles = currentAccount.roles[0];

      if (next.data.roles.indexOf(roles) == -1) {
        this.router.navigate(['/error']);
        return false;
      }
      //success
      return true;
    }
    this.router.navigate(['/login'])
    return null;
  }

}
