import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { forkJoin } from 'rxjs';
import { CarritoService } from '../../services/carrito.service';
import { AuthService } from '../../services/auth.service';
import { PaymentService } from '../../services/payment.service';
import { CompraService } from '../../services/compra.service';

@Component({
  selector: 'app-carrito',
  standalone: true,
  imports: [RouterLink, MatIconModule],
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css'],
})
export class CarritoComponent {
  protected carrito = inject(CarritoService);
  private auth = inject(AuthService);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);
  private paymentService = inject(PaymentService);
  private compraService = inject(CompraService);

  get subtotal(): number {
    return this.carrito.cartItems().reduce((s, i) => s + i.precio, 0);
  }

  get tax(): number {
    return this.subtotal * 0.08;
  }

  get total(): number {
    return this.subtotal + this.tax;
  }

  pagarProvisional(): void {
    const user = this.auth.currentUser();
    if (!user) {
      this.snackBar.open('Debes iniciar sesión para continuar', 'OK', { duration: 2000 });
      this.router.navigate(['/login']);
      return;
    }

    const items = this.carrito.cartItems();
    if (items.length === 0) return;

    const purchases = items.map(item =>
      this.compraService.crearCompra({
        id_usuario: user.id,
        id_curso: item.id_curso,
        precio_pagado: item.precio,
      })
    );

    forkJoin(purchases).subscribe({
      next: () => {
        this.carrito.clearCart();
        this.snackBar.open('¡Compra realizada con éxito!', 'OK', { duration: 3000 });
        this.router.navigate(['/pago-exitoso']);
      },
      error: (err) => {
        const msg = err.error?.error || err.statusText || err.message || 'Error desconocido';
        this.snackBar.open(`Error: ${msg}`, 'OK', { duration: 5000 });
        console.error('Error al comprar:', err);
      }
    });
  }

  pagarConStripe(): void {
    if (!this.auth.isLoggedIn()) {
      this.snackBar.open('Debes iniciar sesión para continuar', 'OK', { duration: 2000 });
      this.router.navigate(['/login']);
      return;
    }

    const items = this.carrito.cartItems();
    if (items.length === 0) return;

    this.paymentService.createCheckoutSession(items).subscribe({
      next: (res) => {
        window.location.href = res.url;
      },
      error: (err) => {
        this.snackBar.open('Error al crear la sesión de pago. Inténtalo de nuevo.', 'OK', { duration: 3000 });
        console.error(err);
      }
    });
  }
}
