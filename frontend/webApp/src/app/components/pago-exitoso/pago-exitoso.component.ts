import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-pago-exitoso',
  standalone: true,
  imports: [RouterLink, MatIconModule],
  template: `
    <div class="status-page">
      <div class="status-icon success">
        <mat-icon>check_circle</mat-icon>
      </div>
      <h2>¡Pago realizado con éxito!</h2>
      <p>Gracias por tu compra. Recibirás un email con los detalles de acceso a tus cursos.</p>
      <div class="status-actions">
        <a routerLink="/cursos" class="btn-glass-primary">
          <mat-icon>explore</mat-icon>
          Seguir aprendiendo
        </a>
        <a routerLink="/" class="btn-glass">
          <mat-icon>home</mat-icon>
          Ir al inicio
        </a>
      </div>
    </div>
  `,
  styleUrls: ['./pago-status.css']
})
export class PagoExitosoComponent {}
