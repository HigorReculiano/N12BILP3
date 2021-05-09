import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CompanyService } from '../services/company.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss'],
  providers:[CompanyService]
})


export class CompanyComponent implements OnInit {

  dataSource:any[] = []
  displayedColumns = ["name", "age", "role", "company", "actions"]
  constructor(private companyService:CompanyService, private cdr:ChangeDetectorRef, private userService:UserService) { }

  async ngOnInit(){
    //this.dataSource = await this.companyService.getCompanies();
    this.dataSource = [{name:"Renato", age:"23", role:"Chefe", company:"FTT"}]
    this.cdr.markForCheck();
  }

  async deleteUser(idUser:number){
    try{
      await this.userService.deleteUser(idUser);
    }catch(erro)
    {
      console.log(erro);
    }
  }
}
