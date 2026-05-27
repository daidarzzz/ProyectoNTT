import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, delay } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class UsuarioService {
  private apiUrl = '/api/usuarios';
  private readonly STORAGE_KEY = 'learnhub_usuarios';

  private usuariosSignal = signal<User[]>([]);
  readonly usuarios = this.usuariosSignal.asReadonly();

  private defaultUsers: User[] = [
    { id: 1, nombre: 'Admin', apellidos: 'Sistema', email: 'admin@learnhub.com', password: 'admin123', rol: 'admin', estado: 'activo' },
    { id: 2, nombre: 'Daria', apellidos: 'Koba', email: 'daria@gmail.com', password: '123456', rol: 'cliente', estado: 'activo' },
    { id: 3, nombre: 'Carlos', apellidos: 'López', email: 'carlos@mail.com', password: '123456', rol: 'cliente', estado: 'activo' },
    { id: 4, nombre: 'María', apellidos: 'García', email: 'maria@mail.com', password: '123456', rol: 'cliente', estado: 'inactivo' },
  ];

  constructor(private http: HttpClient) {
    this.load();
  }

  private load(): void {
    const stored = localStorage.getItem(this.STORAGE_KEY);
    if (stored) {
      this.usuariosSignal.set(JSON.parse(stored));
    } else {
      this.usuariosSignal.set(this.defaultUsers);
      this.persist();
    }
  }

  private persist(): void {
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(this.usuariosSignal()));
  }

  getUsuarios(): Observable<User[]> {
    return of(this.usuariosSignal()).pipe(delay(300));
  }

  updateUsuario(id: number, data: Partial<User>): Observable<User> {
    const list = this.usuariosSignal();
    const idx = list.findIndex(u => u.id === id);
    if (idx === -1) throw new Error('Usuario no encontrado');
    const updated = { ...list[idx], ...data };
    list[idx] = updated;
    this.usuariosSignal.set([...list]);
    this.persist();
    return of(updated).pipe(delay(200));
  }

  deleteUsuario(id: number): Observable<void> {
    const list = this.usuariosSignal().filter(u => u.id !== id);
    this.usuariosSignal.set(list);
    this.persist();
    return of(void 0).pipe(delay(200));
  }

  addUserFromAuth(user: User): void {
    const list = this.usuariosSignal();
    if (list.some(u => u.id === user.id)) return;
    this.usuariosSignal.set([...list, user]);
    this.persist();
  }
}
