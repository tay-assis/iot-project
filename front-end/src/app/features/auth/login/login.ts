import { Component } from '@angular/core';
import { UiBoxComponent } from '../../../components/ui-box/ui-box';
import { UiInputComponent } from '../../../components/ui-text-input/ui-text-input';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth';

@Component({
  selector: 'app-login',
   imports: [
    CommonModule,
    UiBoxComponent,
    UiInputComponent,
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}


  email: string = '';
  password: string = '';
  error: string = '';

  submit() {
    this.authService.login(this.email, this.password).subscribe({
      next: (res) => {
        console.log('Login OK', res);

        if (res.role === 'ADMIN') {
          this.router.navigate(['/admin']);

        } else if (res.role === 'PROFESSOR') {
          this.router.navigate(['/professor']);

        } else {
          this.router.navigate(['/']);
        }
      },
      error: () => {
        this.error = 'Email ou senha inválidos';
        console.log(this.error)
      }
    });
  }
}
