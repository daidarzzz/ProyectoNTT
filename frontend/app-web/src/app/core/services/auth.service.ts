import { Injectable, signal, computed, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthService {
  readonly #http = inject(HttpClient);
  readonly #user = signal<User | null>(null);

  readonly user = this.#user.asReadonly();
  readonly isAuthenticated = computed(() => this.#user() !== null);

  login(credentials: { email: string; password: string }) {
    return this.#http.post<{ user: User }>('/api/auth/login', credentials).pipe(
      tap(res => this.#user.set(res.user)),
    );
  }

  logout(): void {
    this.#user.set(null);
  }

  setUser(user: User): void {
    this.#user.set(user);
  }
}