import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, FormsModule, MatIconModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  private auth = inject(AuthService);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);

  email = '';
  password = '';
  hidePassword = true;

  onSubmit(): void {
    if (!this.email || !this.password) return;

    this.auth.login(this.email, this.password).subscribe(user => {
      if (user) {
        this.snackBar.open(`Bienvenido, ${user.nombre}!`, 'OK', { duration: 2000 });
        this.router.navigate(['/']);
      } else {
        this.snackBar.open('Credenciales incorrectas. Intenta de nuevo.', 'Cerrar', { duration: 3000 });
      }
    });
  }
}
