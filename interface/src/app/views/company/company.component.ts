import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { CompanyService } from '../services/company.service';
import { UserService } from '../services/user.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { SuccessAlertDialog } from '../../components/success-alert.component';


export interface userEdit { id: number, name: string, age: number, role: string; company: string }
@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss'],
  providers: [CompanyService]
})

export class CompanyComponent implements OnInit {

  dataSource: any[] = []
  displayedColumns = ["name", "age", "role", "company", "actions"]
  constructor(private router: Router, private companyService: CompanyService, private cdr: ChangeDetectorRef, private userService: UserService, public dialog: MatDialog) { }

  async ngOnInit() {
    this.loadPage();
  }

  navigate(route: string) {
    this.router.navigate([route])
  }

  async deleteUser(idUser: number) {
    try {
      await this.userService.deleteUser(idUser);
      this.loadPage();
      this.dialog.open(SuccessAlertDialog);
    } catch (erro) {
      console.log(erro);
    }
  }

  async openDialog(element: userEdit) {
    const dialogRef = this.dialog.open(EditUser, {
      width: '250px',
      data: { name: element.name, age: element.age, role: element.role, company: element.company }
    });

    dialogRef.afterClosed().subscribe(async (result) => {
      await this.userService.editUser(element.id, result)
      await this.loadPage();
      console.log('The dialog was closed');
    });
  }

  async loadPage() {
    this.dataSource = await this.companyService.getCompanies();
    this.cdr.markForCheck();
  }
}

@Component({
  selector: 'editUser',
  templateUrl: 'editUser.html',
  styleUrls: ['./userEdit.component.scss']
})
export class EditUser {

  constructor(
    public dialogRef: MatDialogRef<EditUser>,
    @Inject(MAT_DIALOG_DATA) public data: userEdit) { }

  onNoClick(): void {
    this.dialogRef.close();
  }


}
