import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterLink, FormsModule, MatIconModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  private auth = inject(AuthService);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);

  nombre = '';
  apellidos = '';
  email = '';
  password = '';
  confirmPassword = '';
  hidePassword = true;
  hideConfirm = true;

  get passwordsMatch(): boolean {
    return this.password === this.confirmPassword;
  }

  get formValid(): boolean {
    return !!(
      this.nombre && this.apellidos && this.email &&
      this.password && this.password.length >= 6 && this.passwordsMatch
    );
  }

  onSubmit(): void {
    if (!this.formValid) return;
    this.auth.register({
      nombre: this.nombre,
      apellidos: this.apellidos,
      email: this.email,
      password: this.password,
    }).subscribe({
      next: (res) => {
        this.snackBar.open(`¡Cuenta creada! Bienvenido, ${res.user.nombre}`, 'OK', { duration: 3000 });
        this.router.navigate(['/']);
      },
      error: (err: HttpErrorResponse) => {
        const msg = err.status === 409
          ? 'Este email ya está registrado.'
          : 'Error del servidor. Intenta más tarde.';
        this.snackBar.open(msg, 'Cerrar', { duration: 3000 });
      }
    });
  }
}
