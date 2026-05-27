import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, delay } from 'rxjs';
import { Compra, CompraRequest } from '../models/compra.model';

@Injectable({ providedIn: 'root' })
export class CompraService {
  private apiUrl = '/api/compras';

  constructor(private http: HttpClient) {}

  crearCompra(request: CompraRequest): Observable<Compra> {
    const compra: Compra = {
      id_compra: Math.floor(Math.random() * 10000),
      id_usuario: request.id_usuario,
      id_curso: request.id_curso,
      precio_pagado: request.precio_pagado,
      fecha_compra: new Date().toISOString(),
      estado_pago: 'completado',
    };
    return of(compra).pipe(delay(600));
  }
}
