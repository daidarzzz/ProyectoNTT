import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Resena } from '../models/resena.model';

@Injectable({ providedIn: 'root' })
export class ResenaService {
  private apiUrl = '/api';

  constructor(private http: HttpClient) {}

  getResenasByCurso(id_curso: number): Observable<Resena[]> {
    return this.http.get<Resena[]>(`${this.apiUrl}/cursos/${id_curso}/resenas`);
  }

  createResena(data: Omit<Resena, 'id_resena' | 'fecha' | 'usuario_nombre'>): Observable<Resena> {
    return this.http.post<Resena>(`${this.apiUrl}/resenas`, data);
  }
}
