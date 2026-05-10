import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UiBoxComponent } from '../../../../components/ui-box/ui-box';
import { UiSelectComponent } from '../../../../components/ui-select/ui-select';
import { StudentService } from '../../../../core/services/student';
import { StudentModel } from '../../../../core/models/student.model';
import { ClassModel } from '../../../../core/models/class.model';
import { ClassService } from '../../../../core/services/class';
import { EnrollCreateDto } from '../../../../core/dto/enroll.create.dto';
import { EnrollService } from '../../../../core/services/enroll';

@Component({
  selector: 'app-create',
  imports: [UiBoxComponent,UiSelectComponent],
  templateUrl: './create.html',
  styleUrl: './create.scss',
})
export class Create implements OnInit{

  students: StudentModel[] = [];
  classes: ClassModel[] = [];

  enrollment: EnrollCreateDto = {
    studentId : 0,
    classId : 0
  }

   ngOnInit(): void {

    this.loadStudents();
    this.loadClasses();

  }
   constructor(
    private studentService: StudentService,
    private router : Router,
    private classService: ClassService,
    private enrollService: EnrollService,
    private cdr: ChangeDetectorRef
  ) {}


  loadStudents(): void {

    this.studentService.getAll().subscribe({
      next: (students) => {
        this.students = students;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
      }
    });

  }

  loadClasses(): void {

    this.classService.getAll().subscribe({
      next: (classes) => {
        this.classes = classes;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
      }
    });

  }

  submit() {
    this.enrollService.createEnroll(this.enrollment).subscribe(response => {
    console.log(response);
  });
    this.router.navigate(['/admin']);
  }
}
  
