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

-- Nota: compras, pedidos, resenas sin seed — se crean via endpoints cuando existan usuarios
