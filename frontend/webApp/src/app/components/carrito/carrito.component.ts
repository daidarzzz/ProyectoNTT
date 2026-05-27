import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CarritoService } from '../../services/carrito.service';
import { AuthService } from '../../services/auth.service';
import { PaymentService } from '../../services/payment.service';

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

  get subtotal(): number {
    return this.carrito.cartItems().reduce((s, i) => s + i.precio, 0);
  }

  get tax(): number {
    return this.subtotal * 0.08;
  }

  get total(): number {
    return this.subtotal + this.tax;
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
