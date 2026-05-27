import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';
import { map, switchMap } from 'rxjs';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CursoService } from '../../services/curso.service';
import { CategoriaService } from '../../services/categoria.service';
import { CarritoService } from '../../services/carrito.service';

@Component({
  selector: 'app-curso-detalle',
  standalone: true,
  imports: [RouterLink, MatIconModule],
  templateUrl: './curso-detalle.component.html',
  styleUrls: ['./curso-detalle.component.css'],
})
export class CursoDetalleComponent {
  private route = inject(ActivatedRoute);
  private cursoService = inject(CursoService);
  private carritoService = inject(CarritoService);
  private snackBar = inject(MatSnackBar);
  protected categorias = toSignal(inject(CategoriaService).getCategorias(), { initialValue: [] });

  curso = toSignal(
    this.route.paramMap.pipe(
      map(params => Number(params.get('id'))),
      switchMap(id => this.cursoService.getCursoById(id))
    )
  );

  addToCart(): void {
    const c = this.curso();
    if (!c) return;
    this.carritoService.addItem(c);
    this.snackBar.open(`"${c.titulo}" añadido al carrito`, 'OK', { duration: 2000 });
  }
}
