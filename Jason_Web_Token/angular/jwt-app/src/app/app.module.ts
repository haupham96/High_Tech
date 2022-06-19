import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {JwtComponent} from './jwt/jwt.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ErrorComponent} from './error/error.component';
import {ReactiveFormsModule} from "@angular/forms";
import {TokenInterceptorService} from "./service/token-interceptor.service";
import { TestAuthenGuardComponent } from './test-authen-guard/test-authen-guard.component';

@NgModule({
  declarations: [
    AppComponent,
    JwtComponent,
    ErrorComponent,
    TestAuthenGuardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
