import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, delay } from 'rxjs';
import { Resena } from '../models/resena.model';

@Injectable({ providedIn: 'root' })
export class ResenaService {
  private apiUrl = '/api/resenas';
  private readonly STORAGE_KEY = 'learnhub_resenas';

  private resenasSignal = signal<Resena[]>([]);

  private defaultResenas: Resena[] = [
    { id_resena: 1, id_curso: 1, id_usuario: 2, usuario_nombre: 'Daria Koba', puntuacion: 5, comentario: 'Excelente curso, muy completo y bien explicado. Lo recomiendo totalmente.', fecha: '2025-12-10T08:00:00.000Z' },
    { id_resena: 2, id_curso: 1, id_usuario: 3, usuario_nombre: 'Carlos López', puntuacion: 4, comentario: 'Muy buen contenido, aunque algunos temas avanzados podrían tener más ejemplos prácticos.', fecha: '2026-01-05T12:30:00.000Z' },
    { id_resena: 3, id_curso: 2, id_usuario: 1, usuario_nombre: 'Admin Sistema', puntuacion: 5, comentario: 'Diseño impecable, los proyectos prácticos son increíbles.', fecha: '2026-02-25T15:00:00.000Z' },
    { id_resena: 4, id_curso: 3, id_usuario: 2, usuario_nombre: 'Daria Koba', puntuacion: 5, comentario: 'Python nunca fue tan fácil de aprender. Los ejercicios con datos reales son muy útiles.', fecha: '2026-02-01T09:15:00.000Z' },
    { id_resena: 5, id_curso: 5, id_usuario: 2, usuario_nombre: 'Daria Koba', puntuacion: 4, comentario: 'Buen curso de React Native, aunque le faltan temas de testing.', fecha: '2026-03-20T11:45:00.000Z' },
    { id_resena: 6, id_curso: 4, id_usuario: 3, usuario_nombre: 'Carlos López', puntuacion: 5, comentario: 'Estrategias de marketing muy actualizadas y aplicables. El módulo de growth hacking es excelente.', fecha: '2026-04-10T16:30:00.000Z' },
  ];

  constructor(private http: HttpClient) {
    this.load();
  }

  private load(): void {
    const stored = localStorage.getItem(this.STORAGE_KEY);
    if (stored) {
      this.resenasSignal.set(JSON.parse(stored));
    } else {
      this.resenasSignal.set(this.defaultResenas);
      this.persist();
    }
  }

  private persist(): void {
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(this.resenasSignal()));
  }

  getResenasByCurso(id_curso: number): Observable<Resena[]> {
    const resenas = this.resenasSignal().filter(r => r.id_curso === id_curso);
    return of(resenas).pipe(delay(200));
  }

  createResena(data: Omit<Resena, 'id_resena' | 'fecha'>): Observable<Resena> {
    const resena: Resena = {
      ...data,
      id_resena: Date.now() + Math.floor(Math.random() * 1000),
      fecha: new Date().toISOString(),
    };
    this.resenasSignal.set([...this.resenasSignal(), resena]);
    this.persist();
    return of(resena).pipe(delay(300));
  }
}
