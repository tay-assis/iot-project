import { Routes } from '@angular/router';
import { Home } from './views/home/home';
import { Cadastro1 } from './views/cadastro-1/cadastro-1';
import { Dashboard } from './views/dashboard/dashboard';
import { Cadastro2 } from './views/cadastro-2/cadastro-2';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: Home },
  { path: 'cadastro_1', component: Cadastro1 },
  { path: 'dashboard', component: Dashboard },
  { path: 'cadastro_2', component: Cadastro2 }
];

