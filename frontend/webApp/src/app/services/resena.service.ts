import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Resena } from '../models/resena.model';

@Injectable({ providedIn: 'root' })
export class ResenaService {
  private apiUrl = '/api';

  constructor(private http: HttpClient) {}

  private mapResena(r: any): Resena {
    return {
      id_resena: r.id,
      id_curso: r.idCurso,
      id_usuario: r.idUsuario,
      usuario_nombre: r.nombreUsuario || '',
      puntuacion: r.puntuacion,
      comentario: r.comentario || '',
      fecha: r.fecha,
    };
  }

  getResenasByCurso(id_curso: number): Observable<Resena[]> {
    return this.http.get<any[]>(`${this.apiUrl}/cursos/${id_curso}/resenas`).pipe(
      map(list => list.map(r => this.mapResena(r)))
    );
  }

  createResena(data: Omit<Resena, 'id_resena' | 'fecha' | 'usuario_nombre'>): Observable<Resena> {
    const body = {
      cursoId: data.id_curso,
      puntuacion: data.puntuacion,
      comentario: data.comentario,
    };
    return this.http.post<any>(`${this.apiUrl}/resenas`, body).pipe(
      map(r => this.mapResena(r))
    );
  }
}
