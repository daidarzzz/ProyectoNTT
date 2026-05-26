import { Injectable, signal, computed } from '@angular/core';
import { Course } from '../models/course.model';

export interface CartItem {
  course: Course;
  quantity: number;
}

@Injectable({ providedIn: 'root' })
export class CartService {
  readonly #items = signal<CartItem[]>([]);

  readonly items = this.#items.asReadonly();
  readonly totalItems = computed(() => this.#items().reduce((acc, i) => acc + i.quantity, 0));
  readonly totalPrice = computed(() => this.#items().reduce((acc, i) => acc + i.course.price * i.quantity, 0));

  add(course: Course): void {
    this.#items.update(items => {
      const existing = items.find(i => i.course.id === course.id);
      return existing
        ? items.map(i => i.course.id === course.id ? { ...i, quantity: i.quantity + 1 } : i)
        : [...items, { course, quantity: 1 }];
    });
  }

  remove(courseId: number): void {
    this.#items.update(items => items.filter(i => i.course.id !== courseId));
  }

  clear(): void {
    this.#items.set([]);
  }
}