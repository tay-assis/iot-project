import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StudentModel } from '../models/student.model';
@Injectable({
  providedIn: 'root',
})
export class StudentService {
   private apiUrl = 'http://localhost:8080/students';

  constructor(private http: HttpClient) {}

  createStudent(student: StudentModel) {
    return this.http.post(
      `${this.apiUrl}`,
      student
    );
  }
}
