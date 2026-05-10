import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { classCreateDto } from '../dto/class.create.dto';

export interface ClassModel {
  id: number;
  name: string;
  active?: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  private apiUrl = 'http://localhost:8080/classes';

  constructor(private http: HttpClient) {}

  getClassesByProfessor(): Observable<ClassModel[]> {
    return this.http.get<ClassModel[]>(
      `${this.apiUrl}/professor/classes`
    );
  }

  getAll(): Observable<ClassModel[]> {
      return this.http.get<ClassModel[]>(
        `${this.apiUrl}/getAll`
      );
    }

    createClass(newClass : classCreateDto) {
        return this.http.post(
          `${this.apiUrl}/create`,
          newClass
        );
      }
}