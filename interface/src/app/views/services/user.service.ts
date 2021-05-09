import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

interface EmployerDTO { name: string; age: number; company: string }

@Injectable()
export class UserService {

    constructor(private http: HttpClient) { }
    saveUser = (employer:EmployerDTO): Promise<any> => {
        return this.http.post<any>(
                `http://localhost:8080/api/employer`, employer
            ).toPromise();
    };
}