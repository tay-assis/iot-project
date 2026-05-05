import { Routes } from '@angular/router';

export const routes: Routes = [
     {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./features/auth/login/login')
        .then(m => m.Login)
  },
   {
    path: 'admin',
    loadComponent: () =>
      import('./features/admin/admin-deshboard/admin-deshboard')
        .then(m => m.AdminDeshboard)
  },
  {
    path: 'admin/student/create',
    loadComponent: () =>
      import('./features/admin/student/create/create')
        .then(m => m.Create)
  },
  {
    path: 'admin/professor/create',
    loadComponent: () =>
      import('./features/admin/professor/create/create')
        .then(m => m.Create)
  },
  {
    path: 'admin/class/create',
    loadComponent: () =>
      import('./features/admin/class/create/create')
        .then(m => m.Create)
  },
  {
    path: 'admin/enrollment/create',
    loadComponent: () =>
      import('./features/admin/enrollment/create/create')
        .then(m => m.Create)
  },
  {
  path: 'professor',
    loadComponent: () =>
      import('./features/professor/dashboard/dashboard')
        .then(m => m.Dashboard)
  },
  
];
