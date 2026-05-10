import {
  ChangeDetectorRef,
  Component,
  OnInit
} from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-session',
  imports: [
      CommonModule,
    ],
  templateUrl: './session.html',
  styleUrls: ['./session.scss']
})
export class Session implements OnInit {

  className = 'Engenharia de Software';

  sessionActive = false;
  showAttendanceModal = false;

  students = [
    {
      id: 1,
      name: 'Pedro',
      present: true,
      manualPresence: false
    },
    {
      id: 2,
      name: 'Stephane',
      present: false,
      manualPresence: false
    },
    {
      id: 3,
      name: 'Carlos',
      present: true,
      manualPresence: false
    },
    {
      id: 4,
      name: 'Amanda',
      present: false,
      manualPresence: false
    }
  ];

  constructor(
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {}

  startSession(): void {
    this.sessionActive = true;
    this.cdr.detectChanges();
  }

  endSession(): void {
    this.sessionActive = false;
    this.showAttendanceModal = true;
    this.cdr.detectChanges();
  }

  toggleManualPresence(student: any): void {
    student.manualPresence = !student.manualPresence;
  }

  isPresent(student: any): boolean {
    return student.present || student.manualPresence;
  }

  concludeAttendance(): void {

    const finalAttendance = this.students.map(student => ({
      id: student.id,
      name: student.name,
      present: this.isPresent(student)
    }));

    console.log('Chamada final:', finalAttendance);

    this.showAttendanceModal = false;

    alert('Chamada concluída');

    this.cdr.detectChanges();
  }
}
