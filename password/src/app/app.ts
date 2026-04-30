import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Home } from './views/home/home';
import { Cadastro1 } from './views/cadastro-1/cadastro-1';
import { Cadastro2 } from './views/cadastro-2/cadastro-2';
import { Dashboard } from './views/dashboard/dashboard';

@Component({
  selector: 'app-root',
  imports: [Home],
  templateUrl: './app.html',
  styleUrl: './app.scss'

  // selector: 'app-root',
  // imports: [Home],
  // templateUrl: './app.html',
  // styleUrl: './app.scss'

  // selector: 'app-root',
  // imports: [Cadastro1],
  // templateUrl: './app.html',
  // styleUrl: './app.scss'

  // selector: 'app-root',
  // imports: [Cadastro2],
  // templateUrl: './app.html',
  // styleUrl: './app.scss'

})
export class App {
  protected readonly title = signal('password');
}
