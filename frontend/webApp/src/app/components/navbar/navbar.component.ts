import { Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../services/auth.service';
import { CarritoService } from '../../services/carrito.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, MatIconModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  protected auth = inject(AuthService);
  protected carrito = inject(CarritoService);
  protected menuOpen = signal(false);

  toggleMenu(): void {
    this.menuOpen.update(v => !v);
  }

  getInitials(): string {
    const user = this.auth.currentUser();
    if (!user) return '';
    return (user.nombre.charAt(0) + user.apellidos.charAt(0)).toUpperCase();
  }
}
