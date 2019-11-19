import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StartMenuComponent } from './start-menu/start-menu.component';
import { CompanyInfoComponent } from './company-info/company-info.component';
import { JobOffersComponent } from './job-offers/job-offers.component';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';
import { MenuAfterLoginComponent } from './menu-after-login/menu-after-login.component';
import { ExcerciseComponent } from './excercise/excercise.component';
import { GraphicComponent } from './graphic/graphic.component';
import { LeaveComponent } from './leave/leave.component';
import { PasswordEditComponent } from './password-edit/password-edit.component';
import { PaymentHistoryComponent } from './payment-history/payment-history.component';
import { PersonalDataComponent } from './personal-data/personal-data.component';
import { RaportComponent } from './raport/raport.component';
import { RegistrationComponent } from './registration/registration.component';
import { VacationComponent } from './vacation/vacation.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpService } from './http.service';
import { AuthService } from './auth.service';
import { AuthGuardService } from './auth-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    StartMenuComponent,
    CompanyInfoComponent,
    JobOffersComponent,
    LoginComponent,
    MainComponent,
    MenuAfterLoginComponent,
    ExcerciseComponent,
    GraphicComponent,
    LeaveComponent,
    PasswordEditComponent,
    PaymentHistoryComponent,
    PersonalDataComponent,
    RaportComponent,
    RegistrationComponent,
    VacationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    AuthGuardService,
    AuthService,
    HttpService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
