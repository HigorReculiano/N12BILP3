import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class DashboardService {

    constructor(private http: HttpClient) { }
    getDashboardData = (): Promise<any> => {
        return this.http.get<any>(
                `http://localhost:8080/api/dashboard`
            ).toPromise();
    };
}
