import { Component } from '@angular/core';
import { CourseCardComponent } from '../../shared/components/course-card.component';
import { SearchBarComponent } from '../../shared/components/search-bar.component';

@Component({
  selector: 'app-catalog',
  standalone: true,
  imports: [CourseCardComponent, SearchBarComponent],
  template: `
    <div class="catalog-page">
      <h1>Catálogo de Cursos</h1>
      <app-search-bar (searchChange)="onSearch($event)"></app-search-bar>
      <div class="course-grid">
        <app-course-card
          [course]="demoCourse"
          (clicked)="onCardClick($event)"
        ></app-course-card>
      </div>
    </div>
  `,
  styles: [`
    .catalog-page { padding: 2rem; max-width: 1200px; margin: 0 auto; }
    h1 { color: #1a1a2e; }
    .course-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 1.5rem; margin-top: 2rem; }
  `]
})
export class CatalogComponent {
  demoCourse = {
    id: 1, slug: 'angular-avanzado', title: 'Angular Avanzado', description: 'Curso de Angular con Signals',
    thumbnailUrl: 'https://placehold.co/400x200', price: 49.99, category: { id: 1, name: 'Frontend', slug: 'frontend' },
    instructor: 'John Doe', durationMinutes: 180, rating: 4.8, studentsCount: 1200
  };

  onSearch(query: string) {
    console.log('Searching:', query);
  }

  onCardClick(id: number) {
    console.log('Course clicked:', id);
  }
}