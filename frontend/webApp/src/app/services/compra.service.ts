import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Compra, CompraRequest } from '../models/compra.model';

@Injectable({ providedIn: 'root' })
export class CompraService {
  private apiUrl = '/api/compras';

  constructor(private http: HttpClient) {}

  crearCompra(request: CompraRequest): Observable<Compra> {
    return this.http.post<Compra>(this.apiUrl, request);
  }
}
