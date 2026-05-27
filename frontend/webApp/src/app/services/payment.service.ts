import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Curso } from '../models/curso.model';

interface CheckoutSessionRequest {
  items: {
    name: string;
    description: string;
    unitAmount: number;
    quantity: number;
  }[];
  successUrl: string;
  cancelUrl: string;
}

@Injectable({ providedIn: 'root' })
export class PaymentService {
  private apiUrl = '/api/pagos';

  constructor(private http: HttpClient) {}

  createCheckoutSession(items: Curso[]): Observable<{ url: string }> {
    const request: CheckoutSessionRequest = {
      items: items.map(item => ({
        name: item.titulo,
        description: item.descripcion,
        unitAmount: Math.round(item.precio * 100), // Stripe usa centavos
        quantity: 1,
      })),
      successUrl: `${window.location.origin}/pago-exitoso`,
      cancelUrl: `${window.location.origin}/pago-cancelado`,
    };
    return this.http.post<{ url: string }>(`${this.apiUrl}/crear-sesion-checkout`, request);
  }
}
