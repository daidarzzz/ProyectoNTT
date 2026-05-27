import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { User, LoginRequest, RegisterRequest } from '../models/user.model';

export interface AuthResponse {
  token: string;
  user: User;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = '/api/auth';

  currentUser = signal<User | null>(null);

  constructor(private http: HttpClient) {
    const stored = localStorage.getItem('learnhub_user');
    if (stored) {
      this.currentUser.set(JSON.parse(stored));
    }
  }

  login(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, { email, password } as LoginRequest).pipe(
      tap(res => {
        localStorage.setItem('learnhub_token', res.token);
        localStorage.setItem('learnhub_user', JSON.stringify(res.user));
        this.currentUser.set(res.user);
      })
    );
  }

  register(data: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, data).pipe(
      tap(res => {
        localStorage.setItem('learnhub_token', res.token);
        localStorage.setItem('learnhub_user', JSON.stringify(res.user));
        this.currentUser.set(res.user);
      })
    );
  }

  logout(): void {
    localStorage.removeItem('learnhub_token');
    localStorage.removeItem('learnhub_user');
    this.currentUser.set(null);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('learnhub_token');
  }

  getToken(): string | null {
    return localStorage.getItem('learnhub_token');
  }
}
