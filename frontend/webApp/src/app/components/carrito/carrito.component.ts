import { Component, inject, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CarritoService } from '../../services/carrito.service';
import { CompraService } from '../../services/compra.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-carrito',
  standalone: true,
  imports: [RouterLink, MatIconModule],
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css'],
})
export class CarritoComponent {
  protected carrito = inject(CarritoService);
  private compraService = inject(CompraService);
  private auth = inject(AuthService);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);

  protected paymentMethod = signal<'card' | 'paypal' | 'transfer'>('card');
  protected processing = signal(false);
  protected step: 'cart' | 'payment' | 'confirmation' = 'cart';

  get subtotal(): number {
    return this.carrito.items().reduce((s, i) => s + i.precio, 0);
  }

  get tax(): number {
    return this.subtotal * 0.08;
  }

  get total(): number {
    return this.subtotal + this.tax;
  }

  goToPayment(): void {
    if (!this.auth.isLoggedIn()) {
      this.snackBar.open('Debes iniciar sesión para continuar', 'OK', { duration: 2000 });
      this.router.navigate(['/login']);
      return;
    }
    this.step = 'payment';
  }

  goToCart(): void {
    this.step = 'cart';
  }

  confirmarCompra(): void {
    const user = this.auth.currentUser();
    if (!user) return;

    const items = this.carrito.items();
    if (items.length === 0) return;

    this.processing.set(true);

    let completed = 0;
    items.forEach(curso => {
      this.compraService.crearCompra({
        id_usuario: user.id,
        id_curso: curso.id_curso,
        precio_pagado: curso.precio,
      }).subscribe(() => {
        completed++;
        if (completed === items.length) {
          this.processing.set(false);
          this.step = 'confirmation';
          this.carrito.clearCart();
        }
      });
    });
  }
}
