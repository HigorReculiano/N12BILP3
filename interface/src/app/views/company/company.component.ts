import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CompanyService } from '../services/company.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss'],
  providers:[CompanyService]
})


export class CompanyComponent implements OnInit {

  dataSource = []
  displayedColumns = ["name", "age", "role", "company"]
  constructor(private companyService:CompanyService, private cdr:ChangeDetectorRef) { }

  async ngOnInit(){
    this.dataSource = await this.companyService.getCompanies();
    this.cdr.markForCheck();
  }

}
