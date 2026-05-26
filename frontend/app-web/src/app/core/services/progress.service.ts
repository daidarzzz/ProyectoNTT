import { Injectable, signal, computed } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ProgressService {
  readonly #duration = signal(0);
  readonly #watched = signal(0);
  readonly #totalDuration = signal(0);

  readonly duration = this.#duration.asReadonly();
  readonly watched = this.#watched.asReadonly();
  readonly percentage = computed(() => {
    const total = this.#totalDuration();
    return total > 0 ? Math.round((this.#watched() / total) * 100) : 0;
  });

  setDuration(seconds: number): void {
    this.#duration.set(seconds);
    this.#totalDuration.set(seconds);
  }

  setWatched(seconds: number): void {
    this.#watched.set(seconds);
  }

  tick(seconds: number): void {
    this.#watched.update(s => s + seconds);
  }

  reset(): void {
    this.#duration.set(0);
    this.#watched.set(0);
    this.#totalDuration.set(0);
  }
}