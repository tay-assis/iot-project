import { Component } from '@angular/core';
import { UiBoxComponent } from '../../../components/ui-box/ui-box';
import { UiInputComponent } from '../../../components/ui-text-input/ui-text-input';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-admin-deshboard',
    imports: [
    CommonModule,
    UiBoxComponent,
    UiInputComponent,
  ],
  templateUrl: './admin-deshboard.html',
  styleUrl: './admin-deshboard.scss',
})
export class AdminDeshboard {

   constructor(private router: Router) {}

  submit(route: string) {
  this.router.navigate([route]);
}
}
