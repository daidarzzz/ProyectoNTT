-- Categorias
INSERT INTO categorias (id, nombre, descripcion) VALUES (1, 'Bases de Datos', 'Cursos sobre SQL, MySQL y modelado de bases de datos');
INSERT INTO categorias (id, nombre, descripcion) VALUES (2, 'Java', 'Cursos de programación en Java y POO');
INSERT INTO categorias (id, nombre, descripcion) VALUES (3, 'Desarrollo Web', 'Cursos de HTML, CSS, JavaScript y frontend');
INSERT INTO categorias (id, nombre, descripcion) VALUES (4, 'Ciberseguridad', 'Cursos relacionados con seguridad informática');
INSERT INTO categorias (id, nombre, descripcion) VALUES (5, 'Python', 'Cursos de programación en Python');
ALTER TABLE categorias ALTER COLUMN id RESTART WITH 6;

-- Cursos
INSERT INTO cursos (id, nombre, descripcion, precio, id_categoria, horas, autor)
VALUES (1, 'MySQL desde cero', 'Aprende bases de datos MySQL paso a paso', 49.99, 1, 20, 'Raul Cobaltt');
INSERT INTO cursos (id, nombre, descripcion, precio, id_categoria, horas, autor)
VALUES (2, 'Java POO', 'Curso completo de programación orientada a objetos en Java', 79.99, 2, 40, 'Raul Cobaltt');
INSERT INTO cursos (id, nombre, descripcion, precio, id_categoria, horas, autor)
VALUES (3, 'HTML y CSS', 'Crea páginas web modernas desde cero', 39.99, 3, 15, 'Raul Cobaltt');
INSERT INTO cursos (id, nombre, descripcion, precio, id_categoria, horas, autor)
VALUES (4, 'JavaScript Avanzado', 'Aprende JavaScript moderno y asincronía', 69.99, 3, 30, 'Raul Cobaltt');
INSERT INTO cursos (id, nombre, descripcion, precio, id_categoria, horas, autor)
VALUES (5, 'Introducción a Python', 'Curso básico de Python para principiantes', 59.99, 5, 25, 'Raul Cobaltt');
ALTER TABLE cursos ALTER COLUMN id RESTART WITH 6;

-- Curso imagenes
INSERT INTO curso_imagenes (id, curso_id, url) VALUES (1, 1, '/uploads/cursos/mysql.jpg');
INSERT INTO curso_imagenes (id, curso_id, url) VALUES (2, 2, '/uploads/cursos/java.jpg');
INSERT INTO curso_imagenes (id, curso_id, url) VALUES (3, 3, '/uploads/cursos/htmlcss.jpg');
INSERT INTO curso_imagenes (id, curso_id, url) VALUES (4, 4, '/uploads/cursos/javascript.jpg');
INSERT INTO curso_imagenes (id, curso_id, url) VALUES (5, 5, '/uploads/cursos/python.jpg');
ALTER TABLE curso_imagenes ALTER COLUMN id RESTART WITH 6;

-- Usuarios (passwords bcrypt: "admin123" y "cliente123")
INSERT INTO usuarios (id, nombre, apellidos, email, password_hash, rol, estado, fecha_alta)
VALUES (1, 'Raul', 'Cobaltt', 'admin@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN', 'ACTIVO', '2026-01-10');
INSERT INTO usuarios (id, nombre, apellidos, email, password_hash, rol, estado, fecha_alta)
VALUES (2, 'Daria', 'Koba', 'daria@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'CLIENTE', 'ACTIVO', '2026-02-15');
INSERT INTO usuarios (id, nombre, apellidos, email, password_hash, rol, estado, fecha_alta)
VALUES (3, 'John', 'Perez', 'john@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'CLIENTE', 'ACTIVO', '2026-03-01');
ALTER TABLE usuarios ALTER COLUMN id RESTART WITH 4;

-- Compras (Purchase module)
INSERT INTO compras (id, id_usuario, id_curso, precio_pagado, fecha_compra, estado_pago)
VALUES (1, 2, 1, 49.99, '2026-05-20T12:00:00', 'COMPLETADO');
INSERT INTO compras (id, id_usuario, id_curso, precio_pagado, fecha_compra, estado_pago)
VALUES (2, 2, 2, 79.99, '2026-05-20T12:00:00', 'COMPLETADO');
INSERT INTO compras (id, id_usuario, id_curso, precio_pagado, fecha_compra, estado_pago)
VALUES (3, 3, 3, 39.99, '2026-05-21T15:30:00', 'PENDIENTE');
ALTER TABLE compras ALTER COLUMN id RESTART WITH 4;

-- Pedidos (Order module)
INSERT INTO pedidos (id, usuario_id, fecha, total, estado)
VALUES (1, 2, '2026-05-20T12:00:00', 129.98, 'PAGADO');
INSERT INTO pedidos (id, usuario_id, fecha, total, estado)
VALUES (2, 3, '2026-05-21T15:30:00', 39.99, 'PENDIENTE');
ALTER TABLE pedidos ALTER COLUMN id RESTART WITH 3;

-- Detalle Pedido
INSERT INTO detalle_pedido (id, pedido_id, curso_id, cantidad, precio_unitario)
VALUES (1, 1, 1, 1, 49.99);
INSERT INTO detalle_pedido (id, pedido_id, curso_id, cantidad, precio_unitario)
VALUES (2, 1, 2, 1, 79.99);
INSERT INTO detalle_pedido (id, pedido_id, curso_id, cantidad, precio_unitario)
VALUES (3, 2, 3, 1, 39.99);
ALTER TABLE detalle_pedido ALTER COLUMN id RESTART WITH 4;

-- Resenas
INSERT INTO resenas (id, usuario_id, curso_id, puntuacion, comentario, fecha)
VALUES (1, 2, 1, 5, 'Excelente curso para iniciarse en MySQL', '2026-05-22T10:00:00');
INSERT INTO resenas (id, usuario_id, curso_id, puntuacion, comentario, fecha)
VALUES (2, 2, 2, 4, 'Muy buen curso de Java POO', '2026-05-22T11:00:00');
INSERT INTO resenas (id, usuario_id, curso_id, puntuacion, comentario, fecha)
VALUES (3, 3, 3, 5, 'Perfecto para empezar en desarrollo web', '2026-05-23T09:00:00');
ALTER TABLE resenas ALTER COLUMN id RESTART WITH 4;
