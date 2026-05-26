import { Component, output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Subject, debounceTime, distinctUntilChanged } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-search-bar',
  standalone: true,
  imports: [FormsModule],
  template: `
    <div class="search-wrapper">
      <input
        [ngModel]="query"
        (ngModelChange)="searchSubject.next($event)"
        placeholder="Buscar cursos..."
        class="search-input"
      />
    </div>
  `,
  styles: [`
    .search-wrapper { width: 100%; }
    .search-input { width: 100%; padding: 0.75rem 1rem; border: 2px solid #e0e0e0; border-radius: 8px; font-size: 1rem; outline: none; }
    .search-input:focus { border-color: #e94560; }
  `]
})
export class SearchBarComponent {
  readonly searchSubject = new Subject<string>();
  readonly searchChange = output<string>();
  query = '';

  constructor() {
    this.searchSubject.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      takeUntilDestroyed()
    ).subscribe(value => this.searchChange.emit(value));
  }
}