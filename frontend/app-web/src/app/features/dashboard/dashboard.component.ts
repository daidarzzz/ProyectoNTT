import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  template: `
    <div class="dashboard-page">
      <h1>Mi Dashboard</h1>
      <p>Bienvenido a tu panel de aprendizaje</p>
      <div class="courses-grid">
        <div class="course-card">Curso Demo</div>
      </div>
    </div>
  `,
  styles: [`
    .dashboard-page { max-width: 1200px; margin: 2rem auto; padding: 0 2rem; }
    h1 { margin-bottom: 1rem; }
    .courses-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 1.5rem; }
  `]
})
export class DashboardComponent {}