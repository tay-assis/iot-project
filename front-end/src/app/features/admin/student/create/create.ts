import { Component } from '@angular/core';
import { UiBoxComponent } from '../../../../components/ui-box/ui-box';
import { UiInputComponent } from '../../../../components/ui-text-input/ui-text-input';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { StudentModel } from '../../../../core/models/student.model';
import { StudentService } from '../../../../core/services/student';

@Component({
  selector: 'app-create',
  standalone: true,
  imports: [UiBoxComponent, UiInputComponent],
  templateUrl: './create.html',
  styleUrl: './create.scss',
})
export class Create {

  constructor(private router: Router, private studentService:StudentService) {}

  student: StudentModel = {
    name : '',
    registrationNumber : '',
    fingerprintId : ''
  }


  submit() {
    console.log({
      name: this.student.name,
      registrationNumber: this.student.registrationNumber,
      fingerprintId: this.student.fingerprintId
    });
    this.studentService.createStudent(this.student).subscribe(response => {
    console.log(response);
  });
    this.router.navigate(['/admin']);
  }
}
