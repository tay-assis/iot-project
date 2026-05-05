import { Component } from '@angular/core';
import { UiBoxComponent } from '../../../../components/ui-box/ui-box';
import { UiInputComponent } from '../../../../components/ui-text-input/ui-text-input';
import { UiSelectComponent } from '../../../../components/ui-select/ui-select';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create',
  imports: [UiBoxComponent,UiInputComponent,UiSelectComponent],
  templateUrl: './create.html',
  styleUrl: './create.scss',
})
export class Create {
   nome: string = '';
  professorId: number | null = null;

  classes = [
  { id: 1, name: 'Matemática' },
  { id: 2, name: 'Física' }
];

  // mock (depois vem do backend)
  professores = [
    { id: 1, name: 'Dr. João' },
    { id: 2, name: 'Dra. Maria' },
    { id: 3, name: 'Dr. Carlos' }
  ];

  submit() {
    const payload = {
      nome: this.nome,
      professorId: this.professorId
    };

    console.log('Classe criada:', payload);
  }
  
}
