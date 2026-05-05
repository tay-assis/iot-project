import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UiBoxComponent } from '../../../../components/ui-box/ui-box';
import { UiInputComponent } from '../../../../components/ui-text-input/ui-text-input';
import { UiSelectComponent } from '../../../../components/ui-select/ui-select';

@Component({
  selector: 'app-create',
  imports: [UiBoxComponent,UiInputComponent,UiSelectComponent],
  templateUrl: './create.html',
  styleUrl: './create.scss',
})
export class Create {
  constructor(private router: Router) {}

  classes = [
  { id: 1, name: 'Matemática' },
  { id: 2, name: 'Física' }
];
  

    alunoId: number | null = null;
    classeId: number | null = null;

   alunos = [
    { id: 1, name: 'Dr. João' },
    { id: 2, name: 'Dra. Maria' },
    { id: 3, name: 'Dr. Carlos' }
  ];

  submit() {
    this.router.navigate(['/admin']);
  }
}
