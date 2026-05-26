import { Injectable } from '@angular/core';
import { EnrollCreateDto } from '../dto/enroll.create.dto';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class EnrollService {
  private apiUrl = 'http://localhost:8080/enrollments'

  constructor(private http: HttpClient) {}

  createEnroll(enroll : EnrollCreateDto) {
          return this.http.post(
            `${this.apiUrl}/create`,
            enroll
          );
        }
}
