import { Component } from '@angular/core';

@Component({
  selector: 'app-course-detail',
  standalone: true,
  template: `
    <div class="detail-page">
      <h1>Detalle del Curso</h1>
      <p>Contenido próximo a implementar</p>
    </div>
  `,
  styles: [`
    .detail-page { padding: 2rem; max-width: 1200px; margin: 0 auto; }
  `]
})
export class CourseDetailComponent {}