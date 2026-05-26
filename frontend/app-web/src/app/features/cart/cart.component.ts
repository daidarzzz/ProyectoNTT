import { Component, inject } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { CartService } from '../../core/services/cart.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CurrencyPipe, RouterLink],
  template: `
    <div class="cart-page">
      <h1>Tu Carrito</h1>

      @if (cart.items().length === 0) {
        <p class="empty">No hay cursos en tu carrito</p>
        <a routerLink="/cursos" class="back-link">Ver catálogo de cursos</a>
      } @else {
        <div class="cart-list">
          @for (item of cart.items(); track item.course.id) {
            <div class="cart-item">
              <img [src]="item.course.thumbnailUrl" alt="" width="80" />
              <div>
                <h3>{{ item.course.title }}</h3>
                <p>{{ item.course.price | currency:'USD' }} x {{ item.quantity }}</p>
              </div>
              <button (click)="cart.remove(item.course.id)">Eliminar</button>
            </div>
          }
        </div>
        <div class="total-section">
          <strong>Total: {{ cart.totalPrice() | currency:'USD' }}</strong>
          <a routerLink="/cursos" class="back-link">Seguir comprando</a>
        </div>
      }
    </div>
  `,
  styles: [`
    .cart-page { max-width: 800px; margin: 2rem auto; padding: 0 2rem; }
    h1 { margin-bottom: 2rem; }
    .empty { text-align: center; color: #888; padding: 2rem; }
    .cart-item { display: flex; align-items: center; gap: 1rem; padding: 1rem; border: 1px solid #e0e0e0; border-radius: 8px; margin-bottom: 0.5rem; }
    .cart-item button { margin-left: auto; background: #e94560; color: white; border: none; padding: 0.5rem 1rem; border-radius: 6px; cursor: pointer; }
    .total-section { text-align: right; margin-top: 2rem; font-size: 1.5rem; }
  `]
})
export class CartComponent {
  readonly cart = inject(CartService);
}