import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  constructor(private userService:UserService) { }
  userForm = new FormGroup({name:new FormControl("",[Validators.required]), age:new FormControl("",[Validators.required]), company:new FormControl("",[Validators.required])})
  ngOnInit(): void {
  }

  async saveUser(){
    try{
      await this.userService.saveUser(this.userForm.getRawValue());
    }catch(erro){
      console.log(erro);
    }
  }
}
