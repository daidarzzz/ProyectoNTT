import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class UsuarioService {
  private apiUrl = '/api/usuarios';

  constructor(private http: HttpClient) {}

  private mapBackendUser(u: any): User {
    return {
      id: u.id,
      nombre: u.nombre,
      apellidos: u.apellidos,
      email: u.email,
      rol: u.rol === 'ADMIN' ? 'admin' : 'cliente',
      estado: u.estado === 'ACTIVO' ? 'activo' : 'inactivo',
      fecha_alta: u.fechaAlta,
    };
  }

  getUsuarios(): Observable<User[]> {
    return this.http.get<any[]>(this.apiUrl).pipe(
      map(list => list.map(u => this.mapBackendUser(u)))
    );
  }

  updateUsuario(id: number, data: Partial<User>): Observable<User> {
    const body: any = {};
    if (data.nombre) body.nombre = data.nombre;
    if (data.apellidos) body.apellidos = data.apellidos;
    if (data.email) body.email = data.email;
    if (data.rol) body.rol = data.rol === 'admin' ? 'ADMIN' : 'CLIENTE';
    return this.http.put<any>(`${this.apiUrl}/${id}`, body).pipe(
      map(u => this.mapBackendUser(u))
    );
  }

  updateEstado(id: number, estado: string): Observable<User> {
    const body = { estado: estado === 'activo' ? 'ACTIVO' : 'INACTIVO' };
    return this.http.put<any>(`${this.apiUrl}/${id}/estado`, body).pipe(
      map(u => this.mapBackendUser(u))
    );
  }

  deleteUsuario(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
