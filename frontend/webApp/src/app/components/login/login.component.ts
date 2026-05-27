import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, MatIconModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  private auth = inject(AuthService);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);
  private fb = inject(FormBuilder);

  protected hidePassword = true;

  protected loginForm = this.fb.nonNullable.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
  });

  onSubmit(): void {
    if (this.loginForm.invalid) return;

    const { email, password } = this.loginForm.getRawValue();

    this.auth.login(email, password).subscribe({
      next: (res) => {
        this.snackBar.open(`Bienvenido, ${res.user.nombre}!`, 'OK', { duration: 2000 });
        this.router.navigate(['/']);
      },
      error: (err: HttpErrorResponse) => {
        const msg = err.status === 401
          ? 'Credenciales incorrectas. Intenta de nuevo.'
          : 'Error del servidor. Intenta más tarde.';
        this.snackBar.open(msg, 'Cerrar', { duration: 3000 });
      }
    });
  }
}
