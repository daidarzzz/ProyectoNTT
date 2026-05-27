import { Component, inject, signal, computed, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { DatePipe } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { CompraService } from '../../services/compra.service';
import { CursoService } from '../../services/curso.service';
import { Compra } from '../../models/compra.model';
import { CursoDetalle } from '../../models/curso.model';

@Component({
  selector: 'app-mis-cursos',
  standalone: true,
  imports: [RouterLink, MatIconModule, DatePipe],
  templateUrl: './mis-cursos.component.html',
  styleUrls: ['./mis-cursos.component.css'],
})
export class MisCursosComponent implements OnInit {
  private auth = inject(AuthService);
  private compraService = inject(CompraService);
  private cursoService = inject(CursoService);

  protected compras = signal<Compra[]>([]);
  protected cursos = signal<CursoDetalle[]>([]);
  protected loading = signal(true);

  protected misCursos = computed(() => {
    const cs = this.cursos();
    const purchases = this.compras();
    return purchases.map(p => ({
      compra: p,
      curso: cs.find(c => c.id_curso === p.id_curso),
    })).filter((x): x is { compra: Compra; curso: CursoDetalle } => !!x.curso);
  });

  ngOnInit(): void {
    const user = this.auth.currentUser();
    if (!user) return;
    this.cursoService.getCursos().subscribe(allCursos => {
      this.cursos.set(allCursos);
      this.compraService.getComprasByUser(user.id).subscribe({
        next: compras => {
          this.compras.set(compras);
          this.loading.set(false);
        },
        error: () => this.loading.set(false),
      });
    });
  }
}
