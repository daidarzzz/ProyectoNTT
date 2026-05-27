-- Categorias
INSERT INTO categorias (id, nombre, descripcion, deleted) VALUES (1, 'Bases de Datos', 'Cursos sobre SQL, MySQL y modelado de bases de datos', false);
INSERT INTO categorias (id, nombre, descripcion, deleted) VALUES (2, 'Java', 'Cursos de programación en Java y POO', false);
INSERT INTO categorias (id, nombre, descripcion, deleted) VALUES (3, 'Desarrollo Web', 'Cursos de HTML, CSS, JavaScript y frontend', false);
INSERT INTO categorias (id, nombre, descripcion, deleted) VALUES (4, 'Ciberseguridad', 'Cursos relacionados con seguridad informática', false);
INSERT INTO categorias (id, nombre, descripcion, deleted) VALUES (5, 'Python', 'Cursos de programación en Python', false);
ALTER TABLE categorias ALTER COLUMN id RESTART WITH 6;

-- Cursos
INSERT INTO cursos (id, nombre, descripcion, descripcion_larga, precio, id_categoria, horas, autor, deleted)
VALUES (1, 'MySQL desde cero', 'Aprende bases de datos MySQL paso a paso', 'Curso completo de MySQL desde nivel principiante hasta intermedio, cubriendo consultas, joins, índices y administración.', 49.99, 1, 20, 'Raul Cobaltt', false);
INSERT INTO cursos (id, nombre, descripcion, descripcion_larga, precio, id_categoria, horas, autor, deleted)
VALUES (2, 'Java POO', 'Curso completo de programación orientada a objetos en Java', 'Aprende Java desde cero con ejemplos prácticos de POO, herencia, polimorfismo, interfaces y excepciones.', 79.99, 2, 40, 'Raul Cobaltt', false);
INSERT INTO cursos (id, nombre, descripcion, descripcion_larga, precio, id_categoria, horas, autor, deleted)
VALUES (3, 'HTML y CSS', 'Crea páginas web modernas desde cero', 'Domina HTML5 y CSS3 para crear sitios web responsivos y modernos con Flexbox, Grid y animaciones.', 39.99, 3, 15, 'Raul Cobaltt', false);
INSERT INTO cursos (id, nombre, descripcion, descripcion_larga, precio, id_categoria, horas, autor, deleted)
VALUES (4, 'JavaScript Avanzado', 'Aprende JavaScript moderno y asincronía', 'Profundiza en JavaScript: closures, promesas, async/await, módulos y patrones de diseño.', 69.99, 3, 30, 'Raul Cobaltt', false);
INSERT INTO cursos (id, nombre, descripcion, descripcion_larga, precio, id_categoria, horas, autor, deleted)
VALUES (5, 'Introducción a Python', 'Curso básico de Python para principiantes', 'Aprende Python desde cero: variables, estructuras de control, funciones, y primeros proyectos.', 59.99, 5, 25, 'Raul Cobaltt', false);
ALTER TABLE cursos ALTER COLUMN id RESTART WITH 6;

-- Curso imagenes
INSERT INTO curso_imagenes (id, curso_id, url) VALUES (1, 1, '/uploads/cursos/mysql.jpg');
INSERT INTO curso_imagenes (id, curso_id, url) VALUES (2, 2, '/uploads/cursos/java.jpg');
INSERT INTO curso_imagenes (id, curso_id, url) VALUES (3, 3, '/uploads/cursos/htmlcss.jpg');
INSERT INTO curso_imagenes (id, curso_id, url) VALUES (4, 4, '/uploads/cursos/javascript.jpg');
INSERT INTO curso_imagenes (id, curso_id, url) VALUES (5, 5, '/uploads/cursos/python.jpg');
ALTER TABLE curso_imagenes ALTER COLUMN id RESTART WITH 6;

-- Usuarios (passwords bcrypt: "admin123" y "cliente123")
INSERT INTO usuarios (id, nombre, apellidos, email, password_hash, rol, estado, fecha_alta, deleted)
VALUES (1, 'Raul', 'Cobaltt', 'admin@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN', 'ACTIVO', '2026-01-10', false);
INSERT INTO usuarios (id, nombre, apellidos, email, password_hash, rol, estado, fecha_alta, deleted)
VALUES (2, 'Daria', 'Koba', 'daria@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'CLIENTE', 'ACTIVO', '2026-02-15', false);
INSERT INTO usuarios (id, nombre, apellidos, email, password_hash, rol, estado, fecha_alta, deleted)
VALUES (3, 'John', 'Perez', 'john@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'CLIENTE', 'ACTIVO', '2026-03-01', false);
ALTER TABLE usuarios ALTER COLUMN id RESTART WITH 4;

-- Compras (Purchase module)
INSERT INTO compras (id, id_usuario, id_curso, precio_pagado, fecha_compra, estado_pago, deleted)
VALUES (1, 2, 1, 49.99, '2026-05-20T12:00:00', 'COMPLETADO', false);
INSERT INTO compras (id, id_usuario, id_curso, precio_pagado, fecha_compra, estado_pago, deleted)
VALUES (2, 2, 2, 79.99, '2026-05-20T12:00:00', 'COMPLETADO', false);
INSERT INTO compras (id, id_usuario, id_curso, precio_pagado, fecha_compra, estado_pago, deleted)
VALUES (3, 3, 3, 39.99, '2026-05-21T15:30:00', 'PENDIENTE', false);
ALTER TABLE compras ALTER COLUMN id RESTART WITH 4;

-- Pedidos (Order module)
INSERT INTO pedidos (id, usuario_id, fecha, total, estado, deleted)
VALUES (1, 2, '2026-05-20T12:00:00', 129.98, 'PAGADO', false);
INSERT INTO pedidos (id, usuario_id, fecha, total, estado, deleted)
VALUES (2, 3, '2026-05-21T15:30:00', 39.99, 'PENDIENTE', false);
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
INSERT INTO resenas (id, usuario_id, curso_id, puntuacion, comentario, fecha, deleted)
VALUES (1, 2, 1, 5, 'Excelente curso para iniciarse en MySQL', '2026-05-22T10:00:00', false);
INSERT INTO resenas (id, usuario_id, curso_id, puntuacion, comentario, fecha, deleted)
VALUES (2, 2, 2, 4, 'Muy buen curso de Java POO', '2026-05-22T11:00:00', false);
INSERT INTO resenas (id, usuario_id, curso_id, puntuacion, comentario, fecha, deleted)
VALUES (3, 3, 3, 5, 'Perfecto para empezar en desarrollo web', '2026-05-23T09:00:00', false);
ALTER TABLE resenas ALTER COLUMN id RESTART WITH 4;
