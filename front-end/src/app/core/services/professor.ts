import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ProfessorModel } from '../models/professor.model';


@Injectable({
  providedIn: 'root',
})
export class ProfessorService {
  private apiUrl = 'http://localhost:8080/professors';

  constructor(private http: HttpClient) {}

  createProfessor(professor: ProfessorModel) {
    return this.http.post(
      `${this.apiUrl}/create`,
      professor
    );
  }

  getAll(): Observable<ProfessorModel[]> {
  return this.http.get<ProfessorModel[]>(
    `${this.apiUrl}/getAll`
  );
}
}
