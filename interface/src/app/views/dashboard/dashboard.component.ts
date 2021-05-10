import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ChartType, ChartOptions } from 'chart.js';
import { SingleDataSet, Label } from 'ng2-charts';
import { DashboardService } from '../services/dashboard.service';

interface DashboardData {
  company: string;
  count: number;
}
@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

    isDataAvailable:boolean = false;
    dashboardData: DashboardData[] = []
    // Pie
    public pieChartOptions: ChartOptions = {
      responsive: true,

    };
    public pieChartLabels: Label[] = [];
    public pieChartData: SingleDataSet = [];
    public pieChartType: ChartType = 'pie';
    public pieChartLegend = true;
    public pieChartPlugins = [];

    constructor(private router: Router, private companyService: DashboardService, private cdr: ChangeDetectorRef) {}

    async ngOnInit() {
      this.dashboardData = await this.companyService.getDashboardData();

      for(const data of this.dashboardData) {
        this.pieChartLabels.push(data.company);
        this.pieChartData.push(data.count);
      }

      this.isDataAvailable = true;
      this.cdr.markForCheck();
    }

    navigate(route: string) {
      this.router.navigate([route])
    }
}
