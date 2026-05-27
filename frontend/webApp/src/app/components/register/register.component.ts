import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule, FormBuilder, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';

function passwordsMatchValidator(group: AbstractControl): ValidationErrors | null {
  const password = group.get('password')?.value;
  const confirm = group.get('confirmPassword')?.value;
  return password === confirm ? null : { passwordsMismatch: true };
}

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, MatIconModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  private auth = inject(AuthService);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);
  private fb = inject(FormBuilder);

  protected hidePassword = true;
  protected hideConfirm = true;

  protected registerForm = this.fb.nonNullable.group({
    nombre: ['', [Validators.required, Validators.minLength(2)]],
    apellidos: ['', [Validators.required, Validators.minLength(2)]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    confirmPassword: ['', [Validators.required]],
  }, { validators: passwordsMatchValidator });

  onSubmit(): void {
    if (this.registerForm.invalid) return;

    const { nombre, apellidos, email, password } = this.registerForm.getRawValue();

    this.auth.register({ nombre, apellidos, email, password }).subscribe({
      next: (user) => {
        this.snackBar.open(`¡Cuenta creada! Bienvenido, ${user.nombre}`, 'OK', { duration: 3000 });
        this.router.navigate(['/']);
      },
      error: (err: HttpErrorResponse) => {
        const msg = err.status === 409
          ? 'Este email ya está registrado. Prueba con otro o inicia sesión.'
          : err.status === 400
          ? 'Revisa los datos ingresados.'
          : err.status === 0
          ? 'El servidor no está disponible. ¿Has iniciado el backend?'
          : 'Error del servidor. Intenta más tarde.';
        this.snackBar.open(msg, 'Cerrar', { duration: 4000 });
      }
    });
  }
}
