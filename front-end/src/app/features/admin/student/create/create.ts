import { Component } from '@angular/core';
import { UiBoxComponent } from '../../../../components/ui-box/ui-box';
import { UiInputComponent } from '../../../../components/ui-text-input/ui-text-input';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create',
  standalone: true,
  imports: [UiBoxComponent, UiInputComponent],
  templateUrl: './create.html',
  styleUrl: './create.scss',
})
export class Create {

  constructor(private router: Router) {}

  nome = '';
  email = '';

  submit() {
    console.log({
      nome: this.nome,
      email: this.email
    });
    this.router.navigate(['/admin']);
  }
}
