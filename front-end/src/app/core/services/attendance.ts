import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AttendanceResponseDTO } from '../dto/attendance.response.dto';

@Injectable({
  providedIn: 'root',
})
export class AttendanceService {

   private apiUrl = 'http://localhost:8080/attendance';

  constructor(private http: HttpClient) {}

getSessionAttendance(classId: number) {

  return this.http.get<AttendanceResponseDTO[]>(
    `${this.apiUrl}/session/${classId}`
  );

}

  updateAttendance(attendance: AttendanceResponseDTO) {

  return this.http.post(
    `${this.apiUrl}`,
    attendance
  );
}
}
