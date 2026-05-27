import { Component, inject, signal, computed, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DatePipe } from '@angular/common';
import { CursoService } from '../../services/curso.service';
import { UsuarioService } from '../../services/usuario.service';
import { CompraService } from '../../services/compra.service';
import { CategoriaService } from '../../services/categoria.service';
import { AuthService } from '../../services/auth.service';
import { CursoDetalle } from '../../models/curso.model';
import { User } from '../../models/user.model';
import { Compra } from '../../models/compra.model';
import { Categoria } from '../../models/categoria.model';

@Component({
  selector: 'app-admin-panel',
  standalone: true,
  imports: [RouterLink, FormsModule, MatIconModule, DatePipe],
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css'],
})
export class AdminPanelComponent implements OnInit {
  private cursoService = inject(CursoService);
  private usuarioService = inject(UsuarioService);
  private compraService = inject(CompraService);
  private categoriaService = inject(CategoriaService);
  private snackBar = inject(MatSnackBar);
  protected auth = inject(AuthService);

  protected seccion = signal<'cursos' | 'usuarios' | 'compras'>('cursos');

  // Cursos
  protected cursos = signal<CursoDetalle[]>([]);
  protected categorias = signal<Categoria[]>([]);
  protected editingCursoId = signal<number | null>(null);
  protected showNewCurso = signal(false);
  protected newCursoForm = this.emptyCursoForm();

  // Usuarios
  protected usuarios = signal<User[]>([]);
  protected editingUserId = signal<number | null>(null);

  // Compras
  protected compras = signal<Compra[]>([]);
  protected selectedUserId = signal<number | null>(null);
  protected userCompras = signal<Compra[]>([]);

  protected loading = signal(false);

  // Edit course form
  protected editCursoForm = signal<CursoDetalle | null>(null);
  // Edit user form
  protected editUserForm = signal<Partial<User> | null>(null);

  ngOnInit(): void {
    this.loadCursos();
    this.loadCategorias();
    this.loadUsuarios();
  }

  // ===== CURSOS =====
  private loadCursos(): void {
    this.cursoService.getCursos().subscribe(data => this.cursos.set(data));
  }

  private loadCategorias(): void {
    this.categoriaService.getCategorias().subscribe(data => this.categorias.set(data));
  }

  startEditCurso(curso: CursoDetalle): void {
    this.editingCursoId.set(curso.id_curso);
    this.editCursoForm.set({ ...curso });
    this.showNewCurso.set(false);
  }

  cancelEditCurso(): void {
    this.editingCursoId.set(null);
    this.editCursoForm.set(null);
  }

  saveCurso(): void {
    const form = this.editCursoForm();
    if (!form) return;
    this.cursoService.updateCurso(form.id_curso, form).subscribe(() => {
      this.snackBar.open('Curso actualizado', 'OK', { duration: 2000 });
      this.cancelEditCurso();
      this.loadCursos();
    });
  }

  deleteCurso(id: number): void {
    if (!confirm('¿Eliminar este curso?')) return;
    this.cursoService.deleteCurso(id).subscribe(() => {
      this.snackBar.open('Curso eliminado', 'OK', { duration: 2000 });
      this.loadCursos();
    });
  }

  toggleNewCurso(): void {
    this.showNewCurso.update(v => !v);
    if (this.showNewCurso()) {
      this.newCursoForm = this.emptyCursoForm();
      this.editingCursoId.set(null);
    }
  }

  createCurso(): void {
    const f = this.newCursoForm;
    if (!f.titulo || !f.descripcion || !f.descripcion_larga || !f.precio) {
      this.snackBar.open('Completa todos los campos obligatorios', 'OK', { duration: 2000 });
      return;
    }
    this.cursoService.createCurso(f as any).subscribe(() => {
      this.snackBar.open('Curso creado', 'OK', { duration: 2000 });
      this.showNewCurso.set(false);
      this.loadCursos();
    });
  }

  private emptyCursoForm() {
    return { titulo: '', descripcion: '', descripcion_larga: '', precio: 0, imagen_url: '', id_categoria: 1, horas: 0, autor: '', gradiente_card: 'linear-gradient(135deg, #667eea, #764ba2)', icono_abstracto: 'school' };
  }

  // ===== USUARIOS =====
  private loadUsuarios(): void {
    this.usuarioService.getUsuarios().subscribe(data => this.usuarios.set(data));
  }

  startEditUser(user: User): void {
    this.editingUserId.set(user.id);
    this.editUserForm.set({ nombre: user.nombre, apellidos: user.apellidos, email: user.email, rol: user.rol, estado: user.estado });
  }

  cancelEditUser(): void {
    this.editingUserId.set(null);
    this.editUserForm.set(null);
  }

  private syncCurrentUserIfNeeded(userId: number): void {
    if (userId === this.auth.currentUser()?.id) {
      this.auth.refreshCurrentUser();
    }
  }

  saveUser(userId: number): void {
    const form = this.editUserForm();
    if (!form) return;
    this.usuarioService.updateUsuario(userId, form).subscribe({
      next: () => {
        if (form.estado) {
          this.usuarioService.updateEstado(userId, form.estado).subscribe({
            next: () => this.finishSaveUser(userId),
            error: () => this.finishSaveUser(userId),
          });
        } else {
          this.finishSaveUser(userId);
        }
      },
      error: (err) => {
        console.error('saveUser error', err);
        this.snackBar.open('Error al guardar: ' + (err.error?.message || err.statusText || 'Error'), 'OK', { duration: 5000 });
      },
    });
  }

  private finishSaveUser(userId: number): void {
    this.snackBar.open('Usuario actualizado', 'OK', { duration: 2000 });
    this.cancelEditUser();
    this.syncCurrentUserIfNeeded(userId);
    this.loadUsuarios();
  }

  toggleRol(user: User): void {
    const newRol = user.rol === 'admin' ? 'cliente' : 'admin';
    this.usuarioService.updateUsuario(user.id, {
      nombre: user.nombre,
      apellidos: user.apellidos,
      email: user.email,
      rol: newRol,
    }).subscribe({
      next: () => {
        this.snackBar.open(`Usuario ahora es ${newRol}`, 'OK', { duration: 1500 });
        this.syncCurrentUserIfNeeded(user.id);
        this.loadUsuarios();
      },
      error: (err) => {
        console.error('toggleRol error', err);
        this.snackBar.open('Error al cambiar rol: ' + (err.error?.message || err.statusText || 'Error'), 'OK', { duration: 5000 });
      },
    });
  }

  toggleEstado(user: User): void {
    const newEstado = user.estado === 'activo' ? 'inactivo' : 'activo';
    this.usuarioService.updateEstado(user.id, newEstado).subscribe({
      next: () => {
        this.snackBar.open(`Usuario ${newEstado === 'activo' ? 'activado' : 'desactivado'}`, 'OK', { duration: 1500 });
        this.syncCurrentUserIfNeeded(user.id);
        this.loadUsuarios();
      },
      error: (err) => {
        console.error('toggleEstado error', err);
        this.snackBar.open('Error al cambiar estado: ' + (err.error?.message || err.statusText || 'Error'), 'OK', { duration: 5000 });
      },
    });
  }

  deleteUser(id: number): void {
    if (id === this.auth.currentUser()?.id) {
      this.snackBar.open('No puedes eliminarte a ti mismo', 'OK', { duration: 2000 });
      return;
    }
    if (!confirm('¿Eliminar este usuario?')) return;
    this.usuarioService.deleteUsuario(id).subscribe(() => {
      this.snackBar.open('Usuario eliminado', 'OK', { duration: 2000 });
      this.loadUsuarios();
    });
  }

  // ===== COMPRAS =====
  onUserSelect(): void {
    const uid = this.selectedUserId();
    if (!uid) {
      this.userCompras.set([]);
      return;
    }
    this.compraService.getComprasByUser(uid).subscribe(data => this.userCompras.set(data));
  }

  getCursoTitle(id_curso: number): string {
    return this.cursos().find(c => c.id_curso === id_curso)?.titulo || 'Curso #' + id_curso;
  }

  getUsuarioName(id_usuario: number): string {
    const u = this.usuarios().find(u => u.id === id_usuario);
    return u ? `${u.nombre} ${u.apellidos}` : 'Usuario #' + id_usuario;
  }
}
