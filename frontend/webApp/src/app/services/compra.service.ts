import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Compra, CompraRequest } from '../models/compra.model';

@Injectable({ providedIn: 'root' })
export class CompraService {
  private apiUrl = '/api/compras';

  constructor(private http: HttpClient) {}

  private mapCompra(c: any): Compra {
    return {
      id_compra: c.id_compra,
      id_usuario: c.id_usuario,
      id_curso: c.id_curso,
      precio_pagado: c.precio_pagado,
      fecha_compra: c.fecha_compra,
      estado_pago: (c.estado_pago || '').toLowerCase() as Compra['estado_pago'],
    };
  }

  getComprasByUser(id_usuario: number): Observable<Compra[]> {
    return this.http.get<any[]>(`${this.apiUrl}/usuario/${id_usuario}`).pipe(
      map(list => list.map(c => this.mapCompra(c)))
    );
  }

  getAllCompras(): Observable<Compra[]> {
    return this.http.get<any[]>(this.apiUrl).pipe(
      map(list => list.map(c => this.mapCompra(c)))
    );
  }

  crearCompra(request: CompraRequest): Observable<Compra> {
    return this.http.post<any>(this.apiUrl, request).pipe(
      map(c => this.mapCompra(c))
    );
  }
}
