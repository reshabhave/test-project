# Product Service

A simple Spring Boot REST service that exposes a single endpoint to retrieve all products from an in-memory H2 database. Built as a clean, layered Maven application (controller → service → repository → entity).

## Tech Stack

| Component | Version |
|---|---|
| Java | 17+ (built/tested on JDK 21) |
| Spring Boot | 3.3.5 |
| Spring Web (REST) | via `spring-boot-starter-web` |
| Spring Data JPA | via `spring-boot-starter-data-jpa` |
| Database | H2 (in-memory) |
| Build tool | Maven |
| Testing | JUnit 5, Mockito, Spring Boot Test |
| Coverage | JaCoCo |

## Project Structure

```
test-project/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/example/productservice/
    │   │   ├── ProductServiceApplication.java   # Application entry point
    │   │   ├── controller/
    │   │   │   └── ProductController.java        # REST endpoints
    │   │   ├── service/
    │   │   │   └── ProductService.java           # Business logic
    │   │   ├── repository/
    │   │   │   └── ProductRepository.java        # Spring Data JPA repository
    │   │   ├── entity/
    │   │   │   └── Product.java                   # JPA entity
    │   │   └── config/
    │   │       └── DataLoader.java                # Seeds sample data on startup
    │   └── resources/
    │       └── application.properties            # H2 + JPA configuration
    └── test/
        └── java/com/example/productservice/      # JUnit test suite (100% line coverage)
```

## Prerequisites

- JDK 17 or higher
- Maven 3.9+ (or use the bundled `mvnw` wrapper if added)

## Getting Started

### Run the application

```bash
mvn spring-boot:run
```

The application starts on **http://localhost:8080**.

### Build a runnable JAR

```bash
mvn clean package
java -jar target/product-service-0.0.1-SNAPSHOT.jar
```

## API Reference

### Get all products

```
GET /api/products
```

**Example request**

```bash
curl http://localhost:8080/api/products
```

**Example response** — `200 OK`

```json
[
  { "id": 1, "name": "Laptop",   "price": 1200.0 },
  { "id": 2, "name": "Mouse",    "price": 25.5 },
  { "id": 3, "name": "Keyboard", "price": 45.0 }
]
```

## Database

The service uses an **in-memory H2 database** (`jdbc:h2:mem:productdb`). Data lives only for the lifetime of the application and is **re-seeded on every startup** by [`DataLoader`](src/main/java/com/example/productservice/config/DataLoader.java) with three sample products.

### H2 Web Console

The H2 console is enabled for inspecting the database while the app runs:

- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:productdb`
- Username: `sa`
- Password: *(leave blank)*

## Testing

Run the full test suite:

```bash
mvn test
```

This also generates a JaCoCo coverage report and **fails the build if line coverage drops below 90%**.

- Coverage report: `target/site/jacoco/index.html`
- Current line coverage: **100%**

The suite includes unit tests (Mockito), web-layer tests (`@WebMvcTest`), persistence tests (`@DataJpaTest`), and a full context/boot test (`@SpringBootTest`).

## Configuration

Key settings in [`application.properties`](src/main/resources/application.properties):

| Property | Value | Description |
|---|---|---|
| `spring.datasource.url` | `jdbc:h2:mem:productdb` | In-memory H2 database |
| `spring.jpa.hibernate.ddl-auto` | `update` | Auto-creates schema from entities |
| `spring.jpa.show-sql` | `true` | Logs generated SQL |
| `spring.h2.console.enabled` | `true` | Enables the H2 web console |

## Possible Enhancements

- Add `POST`, `PUT`, and `DELETE` endpoints for full CRUD
- Introduce DTOs and request validation
- Add pagination and filtering to the products endpoint
- Swap the in-memory H2 database for a persistent datastore (PostgreSQL, MySQL)
- Add OpenAPI/Swagger documentation
