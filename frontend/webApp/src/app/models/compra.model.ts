export interface Compra {
  id_compra: number;
  id_usuario: number;
  id_curso: number;
  precio_pagado: number;
  fecha_compra: string;
  estado_pago: string;
}

export interface CompraRequest {
  id_usuario: number;
  id_curso: number;
  precio_pagado: number;
}
