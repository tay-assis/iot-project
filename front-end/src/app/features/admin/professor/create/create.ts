import { Component } from '@angular/core';
import { UiBoxComponent } from '../../../../components/ui-box/ui-box';
import { UiInputComponent } from '../../../../components/ui-text-input/ui-text-input';

@Component({
  selector: 'app-create',
  imports: [UiBoxComponent, UiInputComponent],
  templateUrl: './create.html',
  styleUrl: './create.scss',
})
export class Create {
   nome = '';
  email = '';

  submit() {
    console.log({
      nome: this.nome,
      email: this.email
    });
  }
}
