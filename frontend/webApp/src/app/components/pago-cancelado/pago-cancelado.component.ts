import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-pago-cancelado',
  standalone: true,
  imports: [RouterLink, MatIconModule],
  template: `
    <div class="status-page">
      <div class="status-icon cancel">
        <mat-icon>cancel</mat-icon>
      </div>
      <h2>Pago cancelado</h2>
      <p>El proceso de pago ha sido cancelado. Tu carrito ha sido guardado por si quieres intentarlo de nuevo.</p>
      <div class="status-actions">
        <a routerLink="/carrito" class="btn-glass-primary">
          <mat-icon>shopping_cart</mat-icon>
          Volver al carrito
        </a>
        <a routerLink="/cursos" class="btn-glass">
          <mat-icon>explore</mat-icon>
          Explorar cursos
        </a>
      </div>
    </div>
  `,
  styleUrls: ['../pago-exitoso/pago-status.css']
})
export class PagoCanceladoComponent {}
