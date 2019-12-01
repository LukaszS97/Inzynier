import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from './main/main.component';
import { CompanyInfoComponent } from './company-info/company-info.component';
import { JobOffersComponent } from './job-offers/job-offers.component';
import { RegistrationComponent } from './registration/registration.component';
import { GraphicComponent } from './graphic/graphic.component';
import { ExcerciseComponent } from './excercise/excercise.component';
import { RaportComponent } from './raport/raport.component';
import { VacationComponent } from './vacation/vacation.component';
import { PasswordEditComponent } from './password-edit/password-edit.component';
import { PersonalDataComponent } from './personal-data/personal-data.component';
import { PaymentHistoryComponent } from './payment-history/payment-history.component';
import { LeaveComponent } from './leave/leave.component';
import { LoginComponent } from './login/login.component';
import { AuthGuardService } from './auth-guard.service';
import { MenuAfterLoginComponent } from './menu-after-login/menu-after-login.component';
import { AddJobOfferComponent } from './add-job-offer/add-job-offer.component';
import { CandidateComponent } from './candidate/candidate.component';
import { CvComponent } from './cv/cv.component';
import { PersonalDataShareComponent } from './personal-data-share/personal-data-share.component';
import { GraphicCreatorComponent } from './graphic-creator/graphic-creator.component';


const routes: Routes = [
  { path: 'main', component: MainComponent},
  { path: 'company-info', component: CompanyInfoComponent},
  { path: 'job-offers', component: JobOffersComponent},
  { path: 'registration', component: RegistrationComponent},
  { path: 'graphic', component: GraphicComponent},
  { path: 'excercise', component: ExcerciseComponent, canActivate: [AuthGuardService]},
  { path: 'raport', component: RaportComponent, canActivate: [AuthGuardService]},
  { path: 'vacation', component: VacationComponent, canActivate: [AuthGuardService]},
  { path: 'password-edit', component: PasswordEditComponent, canActivate: [AuthGuardService]},
  { path: 'personal-data', component: PersonalDataComponent, canActivate: [AuthGuardService]},
  { path: 'payment-history', component: PaymentHistoryComponent, canActivate: [AuthGuardService]},
  { path: 'leave', component: LeaveComponent, canActivate: [AuthGuardService]},
  { path: 'log', component: LoginComponent, canActivate: [AuthGuardService]},
  { path: 'menu', component: MenuAfterLoginComponent},
  { path: 'add-job-offer', component: AddJobOfferComponent, canActivate: [AuthGuardService]},
  { path: 'candidate', component: CandidateComponent},
  { path: 'cv', component: CvComponent, canActivate: [AuthGuardService]},
  { path: 'personal-data-share', component: PersonalDataShareComponent, canActivate: [AuthGuardService]},
  { path: 'graphic-creator', component: GraphicCreatorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
