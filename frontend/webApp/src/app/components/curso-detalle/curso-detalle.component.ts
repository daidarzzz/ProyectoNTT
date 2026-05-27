import { Component, inject, computed, signal, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';
import { map, switchMap } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DatePipe } from '@angular/common';
import { CursoService } from '../../services/curso.service';
import { CategoriaService } from '../../services/categoria.service';
import { CarritoService } from '../../services/carrito.service';
import { AuthService } from '../../services/auth.service';
import { CompraService } from '../../services/compra.service';
import { ResenaService } from '../../services/resena.service';

@Component({
  selector: 'app-curso-detalle',
  standalone: true,
  imports: [RouterLink, FormsModule, MatIconModule, DatePipe],
  templateUrl: './curso-detalle.component.html',
  styleUrls: ['./curso-detalle.component.css'],
})
export class CursoDetalleComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private cursoService = inject(CursoService);
  private carritoService = inject(CarritoService);
  private snackBar = inject(MatSnackBar);
  protected auth = inject(AuthService);
  private compraService = inject(CompraService);
  private resenaService = inject(ResenaService);
  protected categorias = toSignal(inject(CategoriaService).getCategorias(), { initialValue: [] });

  curso = toSignal(
    this.route.paramMap.pipe(
      map(params => Number(params.get('id'))),
      switchMap(id => this.cursoService.getCursoById(id))
    )
  );

  protected resenas = signal<any[]>([]);
  protected comprasUsuario = signal<any[]>([]);

  // Review form
  protected reviewPuntuacion = signal(0);
  protected reviewComentario = signal('');
  protected hoverPuntuacion = signal(0);
  protected submitting = signal(false);

  protected puedeReseniar = computed(() => {
    const user = this.auth.currentUser();
    const c = this.curso();
    if (!user || !c) return false;
    const compro = this.comprasUsuario().some(cmp => cmp.id_curso === c.id_curso);
    if (!compro) return false;
    const yaResenio = this.resenas().some(r => r.id_usuario === user.id);
    return !yaResenio;
  });

  private cursoId: number | null = null;

  ngOnInit(): void {
    this.route.paramMap.pipe(map(params => Number(params.get('id')))).subscribe(id => {
      this.cursoId = id;
      this.loadResenas(id);
      this.loadComprasUsuario();
    });
  }

  private loadResenas(id: number): void {
    this.resenaService.getResenasByCurso(id).subscribe(data => this.resenas.set(data));
  }

  private loadComprasUsuario(): void {
    const user = this.auth.currentUser();
    if (!user) return;
    this.compraService.getComprasByUser(user.id).subscribe(data => this.comprasUsuario.set(data));
  }

  submitReview(): void {
    const user = this.auth.currentUser();
    const c = this.curso();
    if (!user || !c || this.reviewPuntuacion() === 0) return;
    this.submitting.set(true);
    this.resenaService.createResena({
      id_curso: c.id_curso,
      id_usuario: user.id,
      puntuacion: this.reviewPuntuacion(),
      comentario: this.reviewComentario(),
    }).subscribe(() => {
      this.snackBar.open('Reseña publicada', 'OK', { duration: 2000 });
      this.reviewPuntuacion.set(0);
      this.reviewComentario.set('');
      this.submitting.set(false);
      this.loadResenas(c.id_curso);
    });
  }

  setPuntuacion(v: number): void {
    this.reviewPuntuacion.set(v);
  }

  setHoverPuntuacion(v: number): void {
    this.hoverPuntuacion.set(v);
  }

  addToCart(): void {
    const c = this.curso();
    if (!c) return;
    this.carritoService.addItem(c);
    this.snackBar.open(`"${c.titulo}" añadido al carrito`, 'OK', { duration: 2000 });
  }
}
