import { Injectable, signal, computed } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, delay, tap, map, catchError } from 'rxjs';
import { User, LoginRequest, RegisterRequest } from '../models/user.model';

interface LoginResponse {
  token: string;
  user: {
    id: number;
    nombre: string;
    apellidos: string;
    email: string;
    rol: string;
    estado: string;
    fechaAlta?: string;
    password?: string;
  };
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth'; // TEMPORARY CHANGE FOR DIAGNOSTICS
  private readonly STORAGE_KEY = 'learnhub_user';
  private readonly TOKEN_KEY = 'learnhub_token';

  private currentUserSignal = signal<User | null>(null);
  readonly currentUser = this.currentUserSignal.asReadonly();

  readonly isLoggedIn = computed(() => this.currentUserSignal() !== null);
  readonly isAdmin = computed(() => this.currentUserSignal()?.rol === 'admin');

  constructor(private http: HttpClient) {
    const stored = localStorage.getItem(this.STORAGE_KEY);
    if (stored) {
      this.currentUserSignal.set(JSON.parse(stored));
    }
  }

  private mapBackendUser(u: { id: number; nombre: string; apellidos: string; email: string; rol: string; estado: string; fechaAlta?: string }): User {
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

  private persistSession(user: User, token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(user));
    this.currentUserSignal.set(user);
  }

  login(email: string, password: string): Observable<User> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, { email, password }).pipe(
      map(res => {
        const user = this.mapBackendUser(res.user);
        this.persistSession(user, res.token);
        return user;
      })
    );
  }

  register(data: RegisterRequest): Observable<User> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/register`, data).pipe(
      map(res => {
        const user = this.mapBackendUser(res.user);
        this.persistSession(user, res.token);
        return user;
      })
    );
  }

  refreshCurrentUser(): Observable<User | null> {
    const current = this.currentUserSignal();
    if (!current) return of(null);
    return this.http.get<{ id: number; nombre: string; apellidos: string; email: string; rol: string; estado: string; fechaAlta?: string }>(
      `${this.apiUrl}/profile/${current.id}`
    ).pipe(
      map(res => {
        const user = this.mapBackendUser(res);
        localStorage.setItem(this.STORAGE_KEY, JSON.stringify(user));
        this.currentUserSignal.set(user);
        return user;
      }),
      catchError(() => of(null))
    );
  }

  logout(): void {
    localStorage.removeItem(this.STORAGE_KEY);
    localStorage.removeItem(this.TOKEN_KEY);
    this.currentUserSignal.set(null);
  }
}
