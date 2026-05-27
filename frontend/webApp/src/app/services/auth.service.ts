import { Injectable, signal, computed } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, delay, tap } from 'rxjs';
import { User, LoginRequest, RegisterRequest } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = '/api/auth';
  private readonly STORAGE_KEY = 'learnhub_user';

  private currentUserSignal = signal<User | null>(null);
  readonly currentUser = this.currentUserSignal.asReadonly();

  readonly isLoggedIn = computed(() => this.currentUserSignal() !== null);
  readonly isAdmin = computed(() => this.currentUserSignal()?.rol === 'admin');

  private mockUsers: User[] = [
    { id: 1, nombre: 'Admin', apellidos: 'Sistema', email: 'admin@learnhub.com', rol: 'admin', estado: 'activo', password: 'admin123' },
    { id: 2, nombre: 'Daria', apellidos: 'Koba', email: 'daria@gmail.com', rol: 'cliente', estado: 'activo', password: '123456' },
  ];

  constructor(private http: HttpClient) {
    const stored = localStorage.getItem(this.STORAGE_KEY);
    if (stored) {
      this.currentUserSignal.set(JSON.parse(stored));
    }
  }

  login(email: string, password: string): Observable<User | null> {
    const user = this.mockUsers.find(u => u.email === email && u.password === password) || null;
    if (user) {
      const { password: _, ...safeUser } = user;
      return of(safeUser as User).pipe(
        delay(500),
        tap(u => {
          localStorage.setItem(this.STORAGE_KEY, JSON.stringify(u));
          this.currentUserSignal.set(u);
        })
      );
    }
    return of(null).pipe(delay(500));
  }

  register(data: RegisterRequest): Observable<User> {
    const newUser: User = {
      id: this.mockUsers.length + 1,
      nombre: data.nombre,
      apellidos: data.apellidos,
      email: data.email,
      rol: 'cliente',
      estado: 'activo',
      fecha_alta: new Date().toISOString(),
    };
    this.mockUsers.push({ ...newUser, password: data.password });
    return of(newUser).pipe(
      delay(500),
      tap(u => {
        localStorage.setItem(this.STORAGE_KEY, JSON.stringify(u));
        this.currentUserSignal.set(u);
      })
    );
  }

  logout(): void {
    localStorage.removeItem(this.STORAGE_KEY);
    this.currentUserSignal.set(null);
  }
}
