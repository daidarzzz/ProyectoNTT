import { Routes } from '@angular/router';
import { adminGuard } from './guards/admin.guard';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./components/catalogo/catalogo.component').then(m => m.CatalogoComponent),
  },
  {
    path: 'cursos',
    loadComponent: () => import('./components/cursos/cursos.component').then(m => m.CursosComponent),
  },
  {
    path: 'curso/:id',
    loadComponent: () => import('./components/curso-detalle/curso-detalle.component').then(m => m.CursoDetalleComponent),
  },
  {
    path: 'login',
    loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent),
  },
  {
    path: 'register',
    loadComponent: () => import('./components/register/register.component').then(m => m.RegisterComponent),
  },
  {
    path: 'carrito',
    loadComponent: () => import('./components/carrito/carrito.component').then(m => m.CarritoComponent),
  },
  {
    path: 'pago-exitoso',
    loadComponent: () => import('./components/pago-exitoso/pago-exitoso.component').then(m => m.PagoExitosoComponent),
  },
  {
    path: 'pago-cancelado',
    loadComponent: () => import('./components/pago-cancelado/pago-cancelado.component').then(m => m.PagoCanceladoComponent),
  },
  {
    path: 'admin',
    loadComponent: () => import('./components/admin-panel/admin-panel.component').then(m => m.AdminPanelComponent),
    canActivate: [adminGuard],
  },
  {
    path: '**',
    redirectTo: '',
  },
];
