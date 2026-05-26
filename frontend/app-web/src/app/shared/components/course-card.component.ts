import { Component, input, output } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { Course } from '../../core/models/course.model';

@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [CurrencyPipe],
  template: `
    <article class="course-card" (click)="onCardClick()">
      <img [src]="course().thumbnailUrl" [alt]="course().title" class="thumbnail" loading="lazy" />
      <div class="card-body">
        <h3 class="title">{{ course().title }}</h3>
        <p class="instructor">{{ course().instructor }}</p>
        <div class="meta">
          <span class="rating">⭐ {{ course().rating }}/5</span>
          <span class="students">{{ course().studentsCount }} estudiantes</span>
        </div>
        <div class="footer">
          <span class="price">{{ course().price | currency:'USD' }}</span>
          <button class="add-btn">Agregar</button>
        </div>
      </div>
    </article>
  `,
  styles: [`
    .course-card { border: 1px solid #e0e0e0; border-radius: 12px; overflow: hidden; cursor: pointer; transition: transform 0.2s, box-shadow 0.2s; background: white; }
    .course-card:hover { transform: translateY(-4px); box-shadow: 0 8px 24px rgba(0,0,0,0.1); }
    .thumbnail { width: 100%; height: 180px; object-fit: cover; }
    .card-body { padding: 1rem; }
    .title { font-size: 1.1rem; margin: 0 0 0.5rem; }
    .instructor { color: #666; font-size: 0.9rem; margin-bottom: 0.5rem; }
    .meta { display: flex; justify-content: space-between; font-size: 0.85rem; color: #888; margin-bottom: 1rem; }
    .footer { display: flex; justify-content: space-between; align-items: center; }
    .price { font-weight: 700; font-size: 1.2rem; color: #1a1a2e; }
    .add-btn { background: #e94560; color: white; border: none; padding: 0.5rem 1rem; border-radius: 6px; cursor: pointer; }
  `]
})
export class CourseCardComponent {
  readonly course = input.required<Course>();
  readonly clicked = output<number>();

  onCardClick(): void {
    this.clicked.emit(this.course().id);
  }
}