import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, delay } from 'rxjs';
import { Compra, CompraRequest } from '../models/compra.model';

@Injectable({ providedIn: 'root' })
export class CompraService {
  private apiUrl = '/api/compras';
  private readonly STORAGE_KEY = 'learnhub_compras';

  private comprasSignal = signal<Compra[]>([]);

  constructor(private http: HttpClient) {
    this.load();
  }

  private load(): void {
    const stored = localStorage.getItem(this.STORAGE_KEY);
    if (stored) {
      this.comprasSignal.set(JSON.parse(stored));
    } else {
      const seed: Compra[] = [
        { id_compra: 1, id_usuario: 2, id_curso: 1, precio_pagado: 49.99, fecha_compra: '2025-12-01T10:00:00.000Z', estado_pago: 'completado' },
        { id_compra: 2, id_usuario: 2, id_curso: 3, precio_pagado: 59.99, fecha_compra: '2026-01-15T14:30:00.000Z', estado_pago: 'completado' },
        { id_compra: 3, id_usuario: 1, id_curso: 2, precio_pagado: 39.99, fecha_compra: '2026-02-20T09:00:00.000Z', estado_pago: 'completado' },
        { id_compra: 4, id_usuario: 2, id_curso: 5, precio_pagado: 44.99, fecha_compra: '2026-03-10T16:45:00.000Z', estado_pago: 'completado' },
        { id_compra: 5, id_usuario: 3, id_curso: 1, precio_pagado: 49.99, fecha_compra: '2026-04-05T11:20:00.000Z', estado_pago: 'completado' },
        { id_compra: 6, id_usuario: 3, id_curso: 4, precio_pagado: 34.99, fecha_compra: '2026-04-05T11:21:00.000Z', estado_pago: 'completado' },
      ];
      this.comprasSignal.set(seed);
      this.persist();
    }
  }

  private persist(): void {
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(this.comprasSignal()));
  }

  getComprasByUser(id_usuario: number): Observable<Compra[]> {
    const compras = this.comprasSignal().filter(c => c.id_usuario === id_usuario);
    return of(compras).pipe(delay(200));
  }

  getAllCompras(): Observable<Compra[]> {
    return of(this.comprasSignal()).pipe(delay(200));
  }

  crearCompra(request: CompraRequest): Observable<Compra> {
    const compra: Compra = {
      id_compra: Date.now() + Math.floor(Math.random() * 1000),
      id_usuario: request.id_usuario,
      id_curso: request.id_curso,
      precio_pagado: request.precio_pagado,
      fecha_compra: new Date().toISOString(),
      estado_pago: 'completado',
    };
    this.comprasSignal.set([...this.comprasSignal(), compra]);
    this.persist();
    return of(compra).pipe(delay(600));
  }
}
