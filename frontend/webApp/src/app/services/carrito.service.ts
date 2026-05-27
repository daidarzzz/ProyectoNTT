import { Injectable, signal, computed } from '@angular/core';
import { Curso } from '../models/curso.model';

@Injectable({ providedIn: 'root' })
export class CarritoService {
  private readonly STORAGE_KEY = 'learnhub_cart';
  items = signal<Curso[]>([]);

  total = computed(() => this.items().reduce((sum, item) => sum + item.precio, 0));
  count = computed(() => this.items().length);

  constructor() {
    const stored = localStorage.getItem(this.STORAGE_KEY);
    if (stored) {
      this.items.set(JSON.parse(stored));
    }
  }

  private persist(): void {
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(this.items()));
  }

  addItem(curso: Curso): void {
    if (this.items().some(i => i.id_curso === curso.id_curso)) return;
    this.items.update(list => [...list, curso]);
    this.persist();
  }

  removeItem(id_curso: number): void {
    this.items.update(list => list.filter(i => i.id_curso !== id_curso));
    this.persist();
  }

  clearCart(): void {
    this.items.set([]);
    localStorage.removeItem(this.STORAGE_KEY);
  }
}
