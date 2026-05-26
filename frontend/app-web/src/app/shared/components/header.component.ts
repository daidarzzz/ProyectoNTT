import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CartService } from '../../core/services/cart.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink],
  template: `
    <header class="app-header">
      <div class="header-container">
        <a routerLink="/" class="logo">LearnHub</a>
        <nav>
          <a routerLink="/cursos">Cursos</a>
          <a routerLink="/carrito" class="cart-link">
            Carrito
            @if (cart.totalItems() > 0) {
              <span class="badge">{{ cart.totalItems() }}</span>
            }
          </a>
        </nav>
        <div class="auth-section">
          @if (auth.isAuthenticated()) {
            <a routerLink="/dashboard">Mi Dashboard</a>
          } @else {
            <a routerLink="/login">Iniciar Sesión</a>
          }
        </div>
      </div>
    </header>
  `,
  styles: [`
    .app-header { background: #1a1a2e; color: white; padding: 1rem 2rem; position: sticky; top: 0; z-index: 1000; }
    .header-container { max-width: 1200px; margin: 0 auto; display: flex; justify-content: space-between; align-items: center; }
    .logo { font-size: 1.5rem; font-weight: 700; color: #e94560; text-decoration: none; }
    nav { display: flex; gap: 1.5rem; }
    nav a { color: white; text-decoration: none; }
    .cart-link { position: relative; }
    .badge { background: #e94560; color: white; border-radius: 50%; padding: 0.2rem 0.5rem; font-size: 0.75rem; position: absolute; top: -8px; right: -12px; }
    .auth-section a { color: #e94560; text-decoration: none; }
  `]
})
export class HeaderComponent {
  readonly cart = inject(CartService);
  readonly auth = inject(AuthService);
}