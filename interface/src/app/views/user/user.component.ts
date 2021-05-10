import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { MatDialog } from '@angular/material/dialog';
import { SuccessAlertDialog } from '../../components/success-alert.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  constructor(private router: Router, private userService: UserService, public dialog: MatDialog) { }
  userForm = new FormGroup({ name: new FormControl("", [Validators.required]), age: new FormControl("", [Validators.required]), company: new FormControl("", [Validators.required]) })
  ngOnInit(): void {
  }

  navigate(route: string) {
    this.router.navigate([route])
  }

  async saveUser() {
    try {
      await this.userService.saveUser(this.userForm.getRawValue());
      this.dialog.open(SuccessAlertDialog);
    } catch (erro) {
      console.log(erro);
    }
  }
}
