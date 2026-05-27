export interface Curso {
  id_curso: number;
  titulo: string;
  descripcion: string;
  precio: number;
  imagen_url: string;
  id_categoria: number;
  horas: number;
  autor: string;
  gradiente_card?: string;
  icono_abstracto?: string;
}

export interface CursoDetalle extends Curso {
  descripcion_larga: string;
}
