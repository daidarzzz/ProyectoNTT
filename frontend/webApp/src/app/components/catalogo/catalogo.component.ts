import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CursoService } from '../../services/curso.service';
import { CarritoService } from '../../services/carrito.service';
import { CursoDetalle } from '../../models/curso.model';
import { toSignal } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-catalogo',
  standalone: true,
  imports: [RouterLink, MatIconModule],
  templateUrl: './catalogo.component.html',
  styleUrls: ['./catalogo.component.css'],
})
export class CatalogoComponent {
  private cursoService = inject(CursoService);
  private carritoService = inject(CarritoService);
  private snackBar = inject(MatSnackBar);

  cursos = toSignal(this.cursoService.getCursos(), { initialValue: [] });

  get featuredCursos(): CursoDetalle[] {
    return this.cursos().slice(0, 3);
  }

  addToCart(curso: CursoDetalle): void {
    this.carritoService.addItem(curso);
    this.snackBar.open(`"${curso.titulo}" añadido al carrito`, 'OK', { duration: 2000 });
  }

  protected testimonios = [
    {
      nombre: 'María García',
      rol: 'Desarrolladora Frontend',
      avatar: 'M',
      texto: 'Gracias a LearnHub pasé de diseñadora a desarrolladora frontend en 6 meses. Los cursos son increíblemente prácticos.',
      estrellas: 5,
      gradiente: 'linear-gradient(135deg, #667eea, #764ba2)',
    },
    {
      nombre: 'Carlos Mendoza',
      rol: 'Data Scientist en Google',
      avatar: 'C',
      texto: 'El curso de Data Science me dio las herramientas que necesitaba para conseguir mi trabajo soñado. 100% recomendado.',
      estrellas: 5,
      gradiente: 'linear-gradient(135deg, #4facfe, #00f2fe)',
    },
    {
      nombre: 'Ana López',
      rol: 'Emprendedora Digital',
      avatar: 'A',
      texto: 'La ruta de aprendizaje personalizada con IA me ahorró meses de investigación. Sabía exactamente qué estudiar y en qué orden.',
      estrellas: 5,
      gradiente: 'linear-gradient(135deg, #f093fb, #f5576c)',
    },
  ];

  protected beneficios = [
    { icono: 'school', titulo: 'Expertos de la industria', desc: 'Aprende de profesionales que trabajan en las empresas más innovadoras del mundo.' },
    { icono: 'speed', titulo: 'A tu ritmo', desc: 'Acceso ilimitado 24/7. Estudia cuando y donde quieras con contenido siempre disponible.' },
    { icono: 'verified', titulo: 'Certificación oficial', desc: 'Obtén certificados reconocidos por las principales empresas tecnológicas globales.' },
    { icono: 'group', titulo: 'Comunidad activa', desc: 'Conecta con miles de estudiantes y mentores. Comparte, aprende y crece en comunidad.' },
    { icono: 'devices', titulo: 'Multiplataforma', desc: 'Accede desde cualquier dispositivo. Sincroniza tu progreso en web, tablet y móvil.' },
    { icono: 'support', titulo: 'Soporte prioritario', desc: 'Resuelve tus dudas con mentores expertos. Respuesta garantizada en menos de 24 horas.' },
  ];
}
