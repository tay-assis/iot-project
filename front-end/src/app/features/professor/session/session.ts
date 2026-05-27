import {ChangeDetectorRef,Component,OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ClassModel } from '../../../core/models/class.model';
import { ClassService } from '../../../core/services/class';
import { AttendanceResponseDTO } from '../../../core/dto/attendance.response.dto';
import { StudentService } from '../../../core/services/student';
import { AttendanceService } from '../../../core/services/attendance';
import { SessionService } from '../../../core/services/session';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-session',
  imports: [
      CommonModule,
    ],
  templateUrl: './session.html',
  styleUrls: ['./session.scss']
})
export class Session implements OnInit {

  constructor(
    private cdr: ChangeDetectorRef,
    private route: ActivatedRoute,
    private classService: ClassService,
    private attendanceService: AttendanceService,
    private sessionService: SessionService
  ) {}

  pollingSubscription?: Subscription;

  ngOnInit(): void {
    this.classId = Number(
      this.route.snapshot.paramMap.get('classId'));
    console.log(this.classId);

      this.loadClass();
  this.loadStudents();


  }


  classData: ClassModel= {
    id: 0,
    name: '',
    active: false
  };

  classId!: number;

  sessionActive = false;
  showAttendanceModal = false;

  students: AttendanceResponseDTO[] = [];


  toggleManualPresence(student: AttendanceResponseDTO): void {

  const updatedStudent = {
    ...student,
    status: 'PRESENT'
  };

  this.attendanceService
    .updateAttendance(updatedStudent)
    .subscribe({

      next: () => {

        student.status = 'PRESENT';

        this.cdr.detectChanges();
      },

      error: (err) => {
        console.error(err);
        alert('Erro ao atualizar presença');
      }

    });

}


  loadClass(): void {

    this.classService
    .getClass(this.classId)
    .subscribe({
      next: (data) => {

        this.classData = data;

        console.log(data);

         if (this.classData.active) {
          this.startPolling();
        }
        this.cdr.detectChanges();
      },

      error: (err) => {
        console.error(err);
      }

    });

  }

  loadStudents(): void {

  this.attendanceService
     .getSessionAttendance(this.classId)
  .subscribe({
    next: (data) => {
      this.students = data;
      this.cdr.detectChanges();
    },

      error: (err) => {
        console.error(err);
      }

    });

}

startSession(): void {

  this.sessionService
    .startSession(this.classId)
    .subscribe({

      next: () => {

        this.classData.active = true;

        this.loadStudents();

        this.startPolling();

        this.cdr.detectChanges();
      },

      error: (err) => {

        console.error(err);

        if (err.status === 400) {
          alert('Já existe outra sessão aberta');
        } else {
          alert('Erro ao iniciar sessão');
        }
      }

    });

}



endSession(): void {

  this.showAttendanceModal = true;

}

concludeAttendance(): void {

  this.sessionService
    .endSession()
    .subscribe({

      next: () => {

        this.classData.active = false;

        this.showAttendanceModal = false;

        this.stopPolling();

        this.cdr.detectChanges();
      },

      error: (err) => {

        console.error(err);

        alert('Erro ao encerrar sessão');
      }

    });

}

startPolling(): void {

  this.pollingSubscription = interval(3000)
    .subscribe(() => {

      this.loadStudents();

    });

}

stopPolling(): void {

  this.pollingSubscription?.unsubscribe();

}

generatePdf(): void {

  const pdfUrl = 'pdfs/chamada.pdf';

  const link = document.createElement('a');

  link.href = pdfUrl;

  link.download = 'chamada.pdf';

  link.click();

}

}
