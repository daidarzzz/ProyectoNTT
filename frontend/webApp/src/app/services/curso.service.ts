import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CursoDetalle } from '../models/curso.model';

@Injectable({ providedIn: 'root' })
export class CursoService {
  private apiUrl = '/api/cursos';

  constructor(private http: HttpClient) {}

  getCursos(): Observable<CursoDetalle[]> {
    return this.http.get<CursoDetalle[]>(this.apiUrl);
  }

  getCursoById(id: number): Observable<CursoDetalle> {
    return this.http.get<CursoDetalle>(`${this.apiUrl}/${id}`);
  }

  createCurso(data: Omit<CursoDetalle, 'id_curso'>): Observable<CursoDetalle> {
    return this.http.post<CursoDetalle>(this.apiUrl, data);
  }

  updateCurso(id: number, data: Partial<CursoDetalle>): Observable<CursoDetalle> {
    return this.http.put<CursoDetalle>(`${this.apiUrl}/${id}`, data);
  }

  deleteCurso(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
