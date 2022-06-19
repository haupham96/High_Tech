import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {JwtComponent} from "./jwt/jwt.component";
import {ErrorComponent} from "./error/error.component";
import {TestAuthenGuardComponent} from "./test-authen-guard/test-authen-guard.component";
import {AuthenGuard} from "./authen.guard";


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: "login"},
  {path: "login", component: JwtComponent},
  {path:'error',component:ErrorComponent},
  {path:'guard',component:TestAuthenGuardComponent,canActivate:[AuthenGuard],
    data:{
    roles:['ROLE_ADMIN']
    }}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
