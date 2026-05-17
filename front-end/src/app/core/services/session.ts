import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ClassModel } from '../models/class.model';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private apiUrl = 'http://localhost:8080/sessions';

  constructor(private http: HttpClient) {}

 startSession(classId: number) {
  return this.http.post(
    `${this.apiUrl}/start`,
    classId
  );
}

endSession() {
  return this.http.post(
    `${this.apiUrl}/end`,
    {}
  );
}
}