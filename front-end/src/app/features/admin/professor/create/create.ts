import { Component } from '@angular/core';
import { UiBoxComponent } from '../../../../components/ui-box/ui-box';
import { UiInputComponent } from '../../../../components/ui-text-input/ui-text-input';
import { ProfessorModel } from '../../../../core/models/professor.model';
import { email } from '@angular/forms/signals';
import { StudentService } from '../../../../core/services/student';
import { Router } from '@angular/router';
import { ProfessorService } from '../../../../core/services/professor';

@Component({
  selector: 'app-create',
  imports: [UiBoxComponent, UiInputComponent],
  templateUrl: './create.html',
  styleUrl: './create.scss',
})
export class Create {

  professor : ProfessorModel = {
    name : "",
    email : "",
    password: ""
  }

   constructor(private router: Router, private professorService:ProfessorService) {}


  submit() {
    this.professorService.createProfessor(this.professor).subscribe(response => {
    console.log(response);
  });
    this.router.navigate(['/admin']);
  }
}
