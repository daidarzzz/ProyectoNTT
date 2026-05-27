import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, delay } from 'rxjs';
import { Curso, CursoDetalle } from '../models/curso.model';

@Injectable({ providedIn: 'root' })
export class CursoService {
  private apiUrl = '/api/cursos';

  private mockCursos: CursoDetalle[] = [
    {
      id_curso: 1,
      titulo: 'Angular de cero a experto',
      descripcion: 'Domina Angular desde cero hasta aplicaciones empresariales con señales, lazy loading y más.',
      descripcion_larga: 'Este curso completo te guiará a través de todo el ecosistema Angular: componentes, servicios, routing, formularios reactivos, señales, lazy loading y más. Construirás aplicaciones reales del mundo empresarial con las mejores prácticas del sector.',
      precio: 49.99,
      imagen_url: 'https://picsum.photos/seed/angular/800/400',
      id_categoria: 1,
      horas: 40,
      autor: 'Carlos Martínez',
      gradiente_card: 'linear-gradient(135deg, #667eea, #764ba2)',
      icono_abstracto: 'code',
    },
    {
      id_curso: 2,
      titulo: 'Diseño UX/UI profesional',
      descripcion: 'Aprende Figma, prototipado y principios de diseño centrado en el usuario.',
      descripcion_larga: 'Aprenderás a diseñar interfaces modernas utilizando Figma, desde wireframes hasta prototipos interactivos. Cubriremos arquitectura de información, sistemas de diseño, accesibilidad y testing con usuarios reales.',
      precio: 39.99,
      imagen_url: 'https://picsum.photos/seed/uxui/800/400',
      id_categoria: 2,
      horas: 30,
      autor: 'Ana Gómez',
      gradiente_card: 'linear-gradient(135deg, #f093fb, #f5576c)',
      icono_abstracto: 'palette',
    },
    {
      id_curso: 3,
      titulo: 'Python para Data Science',
      descripcion: 'Análisis de datos, visualización y machine learning con Python y pandas.',
      descripcion_larga: 'Desde Pandas y NumPy hasta Scikit-learn y TensorFlow. Trabajarás con datasets reales, crearás visualizaciones impactantes con Matplotlib y Seaborn, y construirás modelos predictivos listos para producción.',
      precio: 59.99,
      imagen_url: 'https://picsum.photos/seed/datascience/800/400',
      id_categoria: 3,
      horas: 50,
      autor: 'Dr. Roberto Sánchez',
      gradiente_card: 'linear-gradient(135deg, #4facfe, #00f2fe)',
      icono_abstracto: 'analytics',
    },
    {
      id_curso: 4,
      titulo: 'Marketing Digital 360',
      descripcion: 'Estrategias de SEO, SEM, redes sociales y growth hacking.',
      descripcion_larga: 'Domina SEO, SEM, email marketing, redes sociales, content marketing y analítica web. Aprenderás a diseñar estrategias multicanal, optimizar conversiones y medir resultados con Google Analytics y herramientas profesionales.',
      precio: 34.99,
      imagen_url: 'https://picsum.photos/seed/marketing/800/400',
      id_categoria: 4,
      horas: 25,
      autor: 'Laura Jiménez',
      gradiente_card: 'linear-gradient(135deg, #43e97b, #38f9d7)',
      icono_abstracto: 'trending_up',
    },
    {
      id_curso: 5,
      titulo: 'React Native: Apps Móviles',
      descripcion: 'Crea apps nativas para iOS y Android con React Native.',
      descripcion_larga: 'Aprende a construir apps móviles completas con React Native. Desde la configuración del entorno hasta la publicación en las stores. Incluye navegación, estado global, APIs nativas, animaciones y testing.',
      precio: 44.99,
      imagen_url: 'https://picsum.photos/seed/reactnative/800/400',
      id_categoria: 5,
      horas: 35,
      autor: 'Miguel Torres',
      gradiente_card: 'linear-gradient(135deg, #a18cd1, #fbc2eb)',
      icono_abstracto: 'smartphone',
    },
    {
      id_curso: 6,
      titulo: 'Ciberseguridad: Ethical Hacking',
      descripcion: 'Pentesting, análisis de vulnerabilidades y seguridad ofensiva.',
      descripcion_larga: 'Curso práctico de ciberseguridad donde aprenderás reconocimiento, escaneo, explotación de vulnerabilidades, post-explotación y elaboración de informes profesionales. Usarás Kali Linux, Metasploit, Burp Suite y más herramientas del sector.',
      precio: 69.99,
      imagen_url: 'https://picsum.photos/seed/cybersec/800/400',
      id_categoria: 6,
      horas: 45,
      autor: 'Elena Ruiz',
      gradiente_card: 'linear-gradient(135deg, #ff0844, #ffb199)',
      icono_abstracto: 'security',
    },
  ];

  constructor(private http: HttpClient) {}

  getCursos(): Observable<CursoDetalle[]> {
    return of(this.mockCursos).pipe(delay(400));
  }

  getCursoById(id: number): Observable<CursoDetalle | undefined> {
    const curso = this.mockCursos.find(c => c.id_curso === id);
    return of(curso).pipe(delay(300));
  }
}
