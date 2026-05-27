import { Component, inject, signal, computed } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CursoService } from '../../services/curso.service';
import { CategoriaService } from '../../services/categoria.service';
import { CarritoService } from '../../services/carrito.service';
import { CursoDetalle } from '../../models/curso.model';
import { toSignal } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-cursos',
  standalone: true,
  imports: [RouterLink, MatIconModule],
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.css'],
})
export class CursosComponent {
  private cursoService = inject(CursoService);
  private carritoService = inject(CarritoService);
  private snackBar = inject(MatSnackBar);

  cursos = toSignal(this.cursoService.getCursos(), { initialValue: [] });
  categorias = toSignal(inject(CategoriaService).getCategorias(), { initialValue: [] });

  filtroCategoria = signal<number | null>(null);
  searchQuery = signal('');

  cursosFiltrados = computed(() => {
    let list = this.cursos();
    const cat = this.filtroCategoria();
    if (cat) {
      list = list.filter(c => c.id_categoria === cat);
    }
    const q = this.searchQuery().toLowerCase();
    if (q) {
      list = list.filter(c => c.titulo.toLowerCase().includes(q) || c.descripcion.toLowerCase().includes(q));
    }
    return list;
  });

  cursoDestacado = computed(() => this.cursosFiltrados()[0]);

  cursosRestantes = computed(() => this.cursosFiltrados().slice(1));

  setFiltro(categoriaId: number | null): void {
    this.filtroCategoria.set(categoriaId);
  }

  addToCart(curso: CursoDetalle): void {
    this.carritoService.addItem(curso);
    this.snackBar.open(`"${curso.titulo}" añadido al carrito`, 'OK', { duration: 2000 });
  }
}
