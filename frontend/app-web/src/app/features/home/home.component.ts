import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  template: `
    <section class="hero">
      <h1>Aprende las habilidades del futuro</h1>
      <p>Cursos online con instructores expertos. 100% prácticos.</p>
      <a routerLink="/cursos" class="cta">Explorar Cursos</a>
    </section>
    <section class="features">
      <div class="feature-card">
        <span class="icon">🎓</span>
        <h3>Instructores expertos</h3>
        <p>Profesionales de la industria</p>
      </div>
      <div class="feature-card">
        <span class="icon">⚡</span>
        <h3>Aprendizaje a tu ritmo</h3>
        <p>Acceso 24/7 a todo el contenido</p>
      </div>
      <div class="feature-card">
        <span class="icon">🏆</span>
        <h3>Certificados</h3>
        <p>Obtén certificados al completar</p>
      </div>
    </section>
  `,
  styles: [`
    .hero { text-align: center; padding: 4rem 2rem; background: linear-gradient(135deg, #1a1a2e 0%, #16213e 100%); color: white; }
    .hero h1 { font-size: 2.5rem; margin-bottom: 1rem; }
    .hero p { font-size: 1.2rem; color: #ccc; margin-bottom: 2rem; }
    .cta { display: inline-block; background: #e94560; color: white; padding: 1rem 2rem; border-radius: 8px; text-decoration: none; font-weight: 600; }
    .features { display: grid; grid-template-columns: repeat(3, 1fr); gap: 2rem; max-width: 1200px; margin: 3rem auto; padding: 0 2rem; }
    .feature-card { text-align: center; padding: 2rem; border-radius: 12px; background: #f8f9fa; }
    .icon { font-size: 2.5rem; }
    .feature-card h3 { margin: 1rem 0 0.5rem; }
  `]
})
export class HomeComponent {}