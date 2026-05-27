import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { CursoDetalle } from '../models/curso.model';

const GRADIENTES: Record<number, string> = {
  1: 'linear-gradient(135deg, #667eea, #764ba2)',
  2: 'linear-gradient(135deg, #f093fb, #f5576c)',
  3: 'linear-gradient(135deg, #4facfe, #00f2fe)',
  4: 'linear-gradient(135deg, #43e97b, #38f9d7)',
  5: 'linear-gradient(135deg, #fa709a, #fee140)',
};

const ICONOS: Record<number, string> = {
  1: 'storage',
  2: 'code',
  3: 'web',
  4: 'security',
  5: 'python',
};

@Injectable({ providedIn: 'root' })
export class CursoService {
  private apiUrl = '/api/cursos';

  constructor(private http: HttpClient) {}

  private mapCurso(u: any): CursoDetalle {
    return {
      id_curso: u.id,
      titulo: u.titulo,
      descripcion: u.descripcion,
      descripcion_larga: u.descripcionLarga || '',
      precio: u.precio,
      imagen_url: u.imagenUrl || '',
      id_categoria: u.idCategoria,
      horas: u.horas,
      autor: u.autor,
      gradiente_card: GRADIENTES[u.idCategoria] || 'linear-gradient(135deg, #667eea, #764ba2)',
      icono_abstracto: ICONOS[u.idCategoria] || 'school',
    };
  }

  private unmapCurso(c: Partial<CursoDetalle>): any {
    const body: any = {};
    body.nombre = c.titulo || '';
    body.descripcion = c.descripcion || '';
    body.descripcionLarga = c.descripcion_larga || '';
    body.precio = c.precio ?? 0;
    body.idCategoria = c.id_categoria ?? 1;
    body.horas = c.horas ?? 0;
    body.autor = c.autor || '';
    if (c.imagen_url) body.imagenes = [c.imagen_url];
    return body;
  }

  getCursos(): Observable<CursoDetalle[]> {
    return this.http.get<any[]>(this.apiUrl).pipe(
      map(list => list.map(u => this.mapCurso(u)))
    );
  }

  getCursoById(id: number): Observable<CursoDetalle> {
    return this.http.get<any>(`${this.apiUrl}/${id}`).pipe(
      map(u => this.mapCurso(u))
    );
  }

  createCurso(data: Omit<CursoDetalle, 'id_curso'>): Observable<CursoDetalle> {
    const body = this.unmapCurso(data);
    return this.http.post<any>(this.apiUrl, body).pipe(
      map(u => this.mapCurso(u))
    );
  }

  updateCurso(id: number, data: Partial<CursoDetalle>): Observable<CursoDetalle> {
    const body = this.unmapCurso(data);
    return this.http.put<any>(`${this.apiUrl}/${id}`, body).pipe(
      map(u => this.mapCurso(u))
    );
  }

  deleteCurso(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
