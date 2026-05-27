-- ============================================
-- USUARIOS
-- ============================================
INSERT INTO usuarios (nombre, apellidos, email, password, rol, estado, fecha_alta) VALUES ('Admin', 'Sistema', 'admin@learnhub.com', '$2a$10$5UW3x/rSNRjv285iUfoOne4jxiHU3jb6Y.3kxr9wr.1XmbO1uQoL.', 'ADMIN', 'ACTIVO', NOW());
INSERT INTO usuarios (nombre, apellidos, email, password, rol, estado, fecha_alta) VALUES ('Daria', 'Koba', 'daria@gmail.com', '$2a$10$f4EgZuWs6CTcm26xwgoOzu8aM0ct3/BeEL6Ao9eR.MsBaJd2whkzO', 'CLIENTE', 'ACTIVO', NOW());

-- ============================================
-- CATEGORIAS
-- ============================================
INSERT INTO categorias (nombre, descripcion) VALUES ('Programacion', 'Cursos de desarrollo de software y programacion');
INSERT INTO categorias (nombre, descripcion) VALUES ('Diseno UX/UI', 'Cursos de diseno de experiencia de usuario e interfaces');
INSERT INTO categorias (nombre, descripcion) VALUES ('Data Science', 'Cursos de ciencia de datos, machine learning e IA');
INSERT INTO categorias (nombre, descripcion) VALUES ('Marketing Digital', 'Cursos de marketing online y estrategia digital');
INSERT INTO categorias (nombre, descripcion) VALUES ('Desarrollo Movil', 'Cursos de apps Android, iOS y multiplataforma');
INSERT INTO categorias (nombre, descripcion) VALUES ('Ciberseguridad', 'Cursos de seguridad informatica y ethical hacking');

-- ============================================
-- CURSOS
-- ============================================
INSERT INTO cursos (titulo, descripcion, descripcion_larga, precio, imagen_url, id_categoria, horas, autor) VALUES ('Angular de cero a experto', 'Domina Angular desde cero hasta aplicaciones empresariales con senales, lazy loading y mas.', 'Curso completo de Angular: componentes, servicios, routing, formularios reactivos, senales, lazy loading.', 49.99, 'https://picsum.photos/seed/angular/800/400', 1, 40, 'Carlos Martinez');
INSERT INTO cursos (titulo, descripcion, descripcion_larga, precio, imagen_url, id_categoria, horas, autor) VALUES ('Diseno UX/UI profesional', 'Aprende Figma, prototipado y principios de diseno centrado en el usuario.', 'Aprenderas a disenar interfaces modernas con Figma: wireframes, prototipos interactivos y accesibilidad.', 39.99, 'https://picsum.photos/seed/uxui/800/400', 2, 30, 'Ana Gomez');
INSERT INTO cursos (titulo, descripcion, descripcion_larga, precio, imagen_url, id_categoria, horas, autor) VALUES ('Python para Data Science', 'Analisis de datos, visualizacion y machine learning con Python y pandas.', 'Pandas, NumPy, Scikit-learn, TensorFlow. Datasets reales y modelos predictivos.', 59.99, 'https://picsum.photos/seed/datascience/800/400', 3, 50, 'Dr. Roberto Sanchez');
INSERT INTO cursos (titulo, descripcion, descripcion_larga, precio, imagen_url, id_categoria, horas, autor) VALUES ('Marketing Digital 360', 'Estrategias de SEO, SEM, redes sociales y growth hacking.', 'SEO, SEM, email marketing, redes sociales y analitica web con Google Analytics.', 34.99, 'https://picsum.photos/seed/marketing/800/400', 4, 25, 'Laura Jimenez');
INSERT INTO cursos (titulo, descripcion, descripcion_larga, precio, imagen_url, id_categoria, horas, autor) VALUES ('React Native: Apps Moviles', 'Crea apps nativas para iOS y Android con React Native.', 'Apps moviles completas con React Native: navegacion, estado global, APIs nativas y testing.', 44.99, 'https://picsum.photos/seed/reactnative/800/400', 5, 35, 'Miguel Torres');
INSERT INTO cursos (titulo, descripcion, descripcion_larga, precio, imagen_url, id_categoria, horas, autor) VALUES ('Ciberseguridad: Ethical Hacking', 'Pentesting, analisis de vulnerabilidades y seguridad ofensiva.', 'Curso practico de ciberseguridad: reconocimiento, escaneo, explotacion, post-explotacion y reportes profesionales.', 69.99, 'https://picsum.photos/seed/cybersec/800/400', 6, 45, 'Elena Ruiz');
