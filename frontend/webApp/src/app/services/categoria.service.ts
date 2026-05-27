import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, delay } from 'rxjs';
import { Categoria } from '../models/categoria.model';

@Injectable({ providedIn: 'root' })
export class CategoriaService {
  private apiUrl = '/api/categorias';

  private mockCategorias: Categoria[] = [
    { id_categoria: 1, nombre: 'Programación', descripcion: 'Cursos de desarrollo de software y programación' },
    { id_categoria: 2, nombre: 'Diseño UX/UI', descripcion: 'Cursos de diseño de experiencia de usuario e interfaces' },
    { id_categoria: 3, nombre: 'Data Science', descripcion: 'Cursos de ciencia de datos, machine learning e IA' },
    { id_categoria: 4, nombre: 'Marketing Digital', descripcion: 'Cursos de marketing online y estrategia digital' },
    { id_categoria: 5, nombre: 'Desarrollo Móvil', descripcion: 'Cursos de apps Android, iOS y multiplataforma' },
    { id_categoria: 6, nombre: 'Ciberseguridad', descripcion: 'Cursos de seguridad informática y ethical hacking' },
  ];

  constructor(private http: HttpClient) {}

  getCategorias(): Observable<Categoria[]> {
    return of(this.mockCategorias).pipe(delay(300));
  }
}
