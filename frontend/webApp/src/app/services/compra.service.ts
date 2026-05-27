import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Compra, CompraRequest } from '../models/compra.model';

@Injectable({ providedIn: 'root' })
export class CompraService {
  private apiUrl = '/api/compras';

  constructor(private http: HttpClient) {}

  getComprasByUser(id_usuario: number): Observable<Compra[]> {
    return this.http.get<Compra[]>(`${this.apiUrl}/usuario/${id_usuario}`);
  }

  getAllCompras(): Observable<Compra[]> {
    return this.http.get<Compra[]>(this.apiUrl);
  }

  crearCompra(request: CompraRequest): Observable<Compra> {
    return this.http.post<Compra>(this.apiUrl, request);
  }
}
