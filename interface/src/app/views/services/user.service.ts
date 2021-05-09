import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { promise } from "selenium-webdriver";

interface EmployerDTO { name: string; age: number; company: string }

@Injectable()
export class UserService {

    constructor(private http: HttpClient) { }
    saveUser = (employer:EmployerDTO): Promise<any> => {
        return this.http.post<any>(
                `http://localhost:8080/api/employer`, employer
            ).toPromise();
    };

    deleteUser = (idUser:number): Promise<any> => {
        return this.http.delete<any>(
            `http://localhost:8080/api/employer?id=${idUser}`
        ).toPromise();
        };

    editUser = (idUser:number, user:any): Promise<any> => {
        return this.http.put<any>(
            `http://localhost:8080/api/employer?id=${idUser}`, user
        ).toPromise();
    }
}