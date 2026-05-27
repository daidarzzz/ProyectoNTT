import { Injectable, signal, computed } from '@angular/core';
import { Curso } from '../models/curso.model';

@Injectable({ providedIn: 'root' })
export class CarritoService {
  private readonly STORAGE_KEY = 'learnhub_cart';
  private cartItemsSignal = signal<Curso[]>([]);
  readonly cartItems = this.cartItemsSignal.asReadonly();

  readonly cartCount = computed(() => this.cartItemsSignal().length);
  readonly cartTotal = computed(() => this.cartItemsSignal().reduce((sum, item) => sum + item.precio, 0));

  constructor() {
    const stored = localStorage.getItem(this.STORAGE_KEY);
    if (stored) {
      this.cartItemsSignal.set(JSON.parse(stored));
    }
  }

  private persist(): void {
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(this.cartItemsSignal()));
  }

  addItem(curso: Curso): void {
    if (this.cartItemsSignal().some(i => i.id_curso === curso.id_curso)) return;
    this.cartItemsSignal.update(list => [...list, curso]);
    this.persist();
  }

  removeItem(id_curso: number): void {
    this.cartItemsSignal.update(list => list.filter(i => i.id_curso !== id_curso));
    this.persist();
  }

  clearCart(): void {
    this.cartItemsSignal.set([]);
    localStorage.removeItem(this.STORAGE_KEY);
  }
}
