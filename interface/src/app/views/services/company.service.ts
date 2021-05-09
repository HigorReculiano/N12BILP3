import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class CompanyService {

    constructor(private http: HttpClient) { }
    getCompanies = (): Promise<any> => {
        return this.http.get<any>(
                `http://localhost:8080/api/employer`
            ).toPromise();
    };
}