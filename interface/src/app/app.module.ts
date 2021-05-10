import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './views/user/user.component';
import { CompanyComponent, EditUser } from './views/company/company.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatSelectModule} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import { HomeComponent } from './views/home/home.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserService } from './views/services/user.service';
import { HttpClientModule } from '@angular/common/http';
import { CompanyService } from './views/services/company.service';
import { MatDialogModule } from '@angular/material/dialog';
import { SuccessAlertDialog } from './components/success-alert.component';
import { ChartsModule } from 'ng2-charts';
import { DashboardService } from './views/services/dashboard.service';



@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    CompanyComponent,
    HomeComponent,
    DashboardComponent,
    EditUser,
    SuccessAlertDialog
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatTableModule,
    MatSelectModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatIconModule,
    FormsModule,
    MatDialogModule,
    ChartsModule
  ],
  providers: [UserService, CompanyService, DashboardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
