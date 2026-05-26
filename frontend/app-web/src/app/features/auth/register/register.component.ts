import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterLink],
  template: `
    <div class="auth-page">
      <h1>Crear Cuenta</h1>
      <form (ngSubmit)="onSubmit()" class="auth-form">
        <input [(ngModel)]="name" name="name" placeholder="Nombre completo" required />
        <input [(ngModel)]="email" name="email" type="email" placeholder="Correo" required />
        <input [(ngModel)]="password" name="password" type="password" placeholder="Contraseña" required />
        <button type="submit">Registrarse</button>
        <p>¿Ya tienes cuenta? <a routerLink="/login">Inicia sesión</a></p>
      </form>
    </div>
  `,
  styles: [`
    .auth-page { max-width: 400px; margin: 4rem auto; padding: 0 2rem; }
    h1 { text-align: center; margin-bottom: 2rem; }
    .auth-form { display: flex; flex-direction: column; gap: 1rem; }
    .auth-form input { padding: 0.75rem; border: 1px solid #ccc; border-radius: 6px; font-size: 1rem; }
    .auth-form button { background: #e94560; color: white; border: none; padding: 0.75rem; border-radius: 6px; cursor: pointer; font-size: 1rem; }
    .auth-form a { color: #e94560; }
  `]
})
export class RegisterComponent {
  readonly #auth = inject(AuthService);
  readonly #router = inject(Router);
  name = '';
  email = '';
  password = '';

  onSubmit() {
    this.#auth.setUser({ id: 1, name: this.name, email: this.email } as any);
    this.#router.navigate(['/dashboard']);
  }
}