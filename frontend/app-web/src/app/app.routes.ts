import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { path: '', loadComponent: () => import('./features/home/home.component').then(c => c.HomeComponent) },

  { path: 'cursos', loadComponent: () => import('./features/catalog/catalog.component').then(c => c.CatalogComponent) },
  { path: 'curso/:slug', loadComponent: () => import('./features/course-detail/course-detail.component').then(c => c.CourseDetailComponent) },

  { path: 'carrito', loadComponent: () => import('./features/cart/cart.component').then(c => c.CartComponent) },

  { path: 'login', loadComponent: () => import('./features/auth/login/login.component').then(c => c.LoginComponent) },
  { path: 'registro', loadComponent: () => import('./features/auth/register/register.component').then(c => c.RegisterComponent) },

  {
    path: 'dashboard',
    loadComponent: () => import('./features/dashboard/dashboard.component').then(c => c.DashboardComponent),
    canActivate: [authGuard]
  },
  {
    path: 'player/:enrollmentId',
    loadComponent: () => import('./features/player/player.component').then(c => c.PlayerComponent),
    canActivate: [authGuard]
  },

  { path: '**', redirectTo: '' }
];
