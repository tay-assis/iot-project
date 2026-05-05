import { Component,OnInit  } from '@angular/core';
import { UiBoxComponent } from '../../../components/ui-box/ui-box';
import { UiInputComponent } from '../../../components/ui-text-input/ui-text-input';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ClassService } from '../../../core/services/class';
import { ClassModel } from '../../../core/models/class.model';
import { ChangeDetectorRef } from '@angular/core';
import { SessionService } from '../../../core/services/session';

@Component({
  selector: 'app-dashboard',
  imports: [
    CommonModule,
    UiBoxComponent,
    UiInputComponent,
  ],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
})
export class Dashboard implements OnInit {

  classes: ClassModel[] = [];
  loading = false;

  constructor(
    private classService: ClassService,
    private cdr: ChangeDetectorRef,
    private sessionService: SessionService
  ) {}

  ngOnInit() {
    console.log('Dashboard iniciou');
    this.loadClasses();
  }

  loadClasses() {
    this.loading = true;

    this.classService.getClassesByProfessor()
      .subscribe({
        next: (data) => {
          console.log('Dados recebidos:', data);
          // adiciona estado local (frontend only)
          this.classes = data.map(c => ({
            ...c,
            active: false
          }));

          this.loading = false;
          this.cdr.detectChanges(); 
        },
        error: (err) => {
          console.error('Erro ao buscar classes', err);
          this.loading = false;
        }
      });

      console.log(this.classes)
  }

  toggleClass(classe: ClassModel) {

    if (!classe.active) {
      // 🔥 iniciar aula
      this.sessionService.startSession(classe.id)
        .subscribe({
          next: () => {
            classe.active = true;
          },
          error: (err) => {
            console.error(err);
            alert('Erro ao iniciar aula');
          }
        });

    } else {
      // 🔥 encerrar aula
      this.sessionService.endSession()
        .subscribe({
          next: () => {
            classe.active = false;
          },
          error: (err) => {
            console.error(err);
            alert('Erro ao encerrar aula');
          }
        });
    }
  }
}