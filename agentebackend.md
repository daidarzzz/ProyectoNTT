# Agent: Backend LearnHub

Guía para el agente de IA al trabajar en el backend de LearnHub.

## Stack técnico

- **Java 17** — lenguaje
- **Spring Boot 3.4.4** — framework base
- **Spring Data JPA** — persistencia
- **Spring Security** — autenticación y autorización
- **H2** — base de datos en memoria (dev)
- **JWT (jjwt 0.12.6)** — tokens de acceso
- **Lombok** — reducción de boilerplate
- **MapStruct 1.6.3** — mapeo domain ↔ entity
- **SpringDoc OpenAPI** — Swagger UI en `/swagger-ui.html`
- **Maven** — build tool

## Arquitectura (DDD + Hexagonal ligero)

Cada **bounded context** es un módulo independiente dentro del proyecto:

```
com.learnhub/
├── config/               ← Configuración global (Security, JWT, Swagger)
├── shared/               ← Código compartido (excepciones, utilidades)
├── user/                 ← Bounded context: Usuarios / Auth
├── course/               ← Bounded context: Cursos
├── category/             ← Bounded context: Categorías
└── purchase/             ← Bounded context: Compras
```

### Capas por bounded context

```
<modulo>/
├── domain/
│   ├── <Entidad>.java           ← POJO de dominio (sin anotaciones Spring/JPA)
│   ├── <Enum>.java              ← Enumeraciones del dominio
│   └── <Entidad>Repository.java ← Puerto (interfaz)
│
├── application/
│   ├── <Entidad>Service.java    ← Casos de uso (lógica de negocio)
│   └── dto/
│       ├── <*>Request.java      ← DTOs de entrada (records)
│       └── <*>Response.java     ← DTOs de salida (records)
│
└── infrastructure/
    ├── persistence/
    │   ├── <Entidad>Entity.java       ← @Entity JPA
    │   ├── <Entidad>EntityMapper.java ← MapStruct domain ↔ entity
    │   ├── Jpa<Entidad>Repository.java ← Adaptador (implementa el puerto)
    │   └── SpringData<Entidad>Repository.java ← Spring Data JPA
    └── rest/
        └── <Entidad>Controller.java   ← Endpoints REST
```

## Convenciones de código

### Naming

| Elemento | Convención | Ejemplo |
|----------|-----------|---------|
| Clases de dominio | Sustantivo sin prefijo | `User`, `Course`, `Purchase` |
| Interfaces repositorio | `<Entidad>Repository` | `UserRepository` |
| Entidades JPA | `<Entidad>Entity` | `UserEntity` |
| Mappers MapStruct | `<Entidad>EntityMapper` | `UserEntityMapper` |
| Adaptadores repositorio | `Jpa<Entidad>Repository` | `JpaUserRepository` |
| DTOs request | `<Acción>Request` | `LoginRequest`, `CreateCourseRequest` |
| DTOs response | `<Entidad>Response` | `UserResponse` |
| Controladores | `<Entidad>Controller` | `AuthController`, `CourseController` |
| Servicios | `<Entidad>Service` | `AuthService`, `CourseService` |

### Estilo

- **DTOs como `record`** — inmutables, con validación vía `jakarta.validation`
- **Inyección por constructor** — nunca `@Autowired` directo en campos
- **`@Transactional` en servicios** — `readOnly = true` en consultas
- **Excepciones** — lanzar `ResourceNotFoundException` o `DuplicateResourceException` desde servicios
- **Mapeo** — nunca mapear manualmente; usar MapStruct en `*EntityMapper`
- **Sin comentarios** — el código debe ser autoexplicativo
- **Spring Security** — endpoints públicos en `SecurityConfig`, el resto requiere JWT

## BD y perfiles

- **Perfil por defecto**: H2 en memoria (`jdbc:h2:mem:learnhub`), consola en `/h2-console`
- **Perfil `mysql`**: `spring-boot.run.profiles=mysql` para MySQL local

Al arrancar, JPA crea las tablas automáticamente (`ddl-auto: create-drop` en H2, `update` en MySQL).

## Endpoints existentes

| Método | Ruta | Auth | Descripción |
|--------|------|------|-------------|
| POST | `/api/auth/login` | No | Login |
| POST | `/api/auth/register` | No | Registro |
| GET | `/api/auth/profile/{id}` | JWT | Perfil usuario |
| GET | `/api/usuarios/{id}` | JWT | Obtener usuario |
| GET | `/api/cursos` | No | Listar cursos (`?categoria=1`) |
| GET | `/api/cursos/{id}` | No | Detalle curso |
| POST | `/api/cursos` | JWT | Crear curso |
| PUT | `/api/cursos/{id}` | JWT | Actualizar curso |
| DELETE | `/api/cursos/{id}` | JWT | Eliminar curso |
| GET | `/api/categorias` | No | Listar categorías |
| GET | `/api/categorias/{id}` | No | Detalle categoría |
| POST | `/api/compras` | JWT | Realizar compra |
| GET | `/api/compras/{id}` | JWT | Detalle compra |
| GET | `/api/compras/usuario/{id}` | JWT | Compras de usuario |

## Cómo agregar una nueva funcionalidad

### Nuevo endpoint en un contexto existente

1. Agregar método en el **Service** (application layer)
2. Agregar DTO `record` si hace falta
3. Agregar endpoint en el **Controller**

### Nuevo bounded context (ej: `reviews`)

1. Crear paquete `com.learnhub.reviews`
2. Crear capas: `domain/`, `application/`, `infrastructure/persistence/`, `infrastructure/rest/`
3. En `domain/`: entidad + repositorio interfaz
4. En `application/`: service + DTOs (request/response)
5. En `infrastructure/persistence/`: Entity, EntityMapper, JpaRepository, adapter
6. En `infrastructure/rest/`: Controller
7. Si aplica, agregar ruta pública en `SecurityConfig`

### Nueva entidad JPA con relación

- Usar `@ManyToOne`, `@OneToMany`, etc. en la `*Entity`
- El dominio NO debe tener anotaciones JPA
- El mapper MapStruct ignora relaciones circulares con `@Mapping(target = "...", ignore = true)`

## Comandos útiles

```bash
# Compilar
mvn compile

# Ejecutar
mvn spring-boot:run

# Ejecutar con perfil MySQL
mvn spring-boot:run -Dspring-boot.run.profiles=mysql

# Tests
mvn test

# Package
mvn package -DskipTests

# Limpiar y compilar
mvn clean compile
```

## Swagger

Una vez arrancado:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs
- **Consola H2**: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:learnhub`)
