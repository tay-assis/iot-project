import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { UiBoxComponent } from '../../../../components/ui-box/ui-box';
import { UiInputComponent } from '../../../../components/ui-text-input/ui-text-input';
import { UiSelectComponent } from '../../../../components/ui-select/ui-select';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ProfessorService } from '../../../../core/services/professor';
import { ProfessorModel } from '../../../../core/models/professor.model';
import { Observable } from 'rxjs';
import { ClassService } from '../../../../core/services/class';
import { classCreateDto } from '../../../../core/dto/class.create.dto';

@Component({
  selector: 'app-create',
  imports: [UiBoxComponent,UiInputComponent,UiSelectComponent],
  templateUrl: './create.html',
  styleUrl: './create.scss',
})
export class Create implements OnInit {


  professors : ProfessorModel[] = [];
  
  newClass: classCreateDto = {
    name: "",
    professorId : 0
  }

  constructor(
    private router: Router,
    private professorService:ProfessorService,
    private cdr: ChangeDetectorRef, 
    private classService: ClassService) {}
   
   ngOnInit(): void {
    this.professorService.getAll().subscribe({
      next: (professors) => {
        this.professors = professors;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
      }
    });

  }

  submit() {
    this.classService.createClass(this.newClass).subscribe(response => {
    console.log(response);
  });
    this.router.navigate(['/admin']);
  }
  
}
