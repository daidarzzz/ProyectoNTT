export interface User {
  id: number;
  nombre: string;
  apellidos: string;
  email: string;
  password?: string;
  rol: 'cliente' | 'admin';
  estado: 'activo' | 'inactivo';
  fecha_alta?: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  nombre: string;
  apellidos: string;
  email: string;
  password: string;
}
