import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyComponent } from './views/company/company.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { HomeComponent } from './views/home/home.component';
import { UserComponent } from './views/user/user.component';

const routes: Routes = [{path:"",component:HomeComponent},{path:"company",component:CompanyComponent},{path:"dashboard",component:DashboardComponent},{path:"user",component:UserComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
