# URL Shortener

A production-inspired URL Shortener REST API built using Spring Boot. The application allows users to generate shortened URLs, redirect to the original URL, customize aliases, track analytics, cache frequently accessed URLs using Redis, and automatically remove expired URLs using a scheduler.

## Features

- Generate short URLs
- Custom alias support
- Automatic URL expiration
- Redirect to original URL
- URL analytics (click count)
- Redis caching for faster redirection
- Automatic deletion of expired URLs
- Request validation
- Global exception handling
- Swagger/OpenAPI documentation
- Docker support (MySQL & Redis)
- Logging using SLF4J

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Redis
- Docker & Docker Compose
- Maven
- Hibernate
- Swagger / OpenAPI
- Lombok

## Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dtos
 ├── util
 ├── exception
 ├── scheduler
 ├── config
 └── security
```

## API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/urls` | Create Short URL |
| GET | `/{shortCode}` | Redirect to Original URL |
| GET | `/api/urls/{shortCode}/analytics` | Get URL Analytics |

## Running the Project

### Clone

```bash
git clone https://github.com/hemanth-sembilli-1860/urlshortener.git
```

### Configure

Create `application.properties` from `application-example.properties` and provide your database credentials.

### Run Docker

```bash
docker compose up -d
```

### Run Application

```bash
mvn spring-boot:run
```

or run `UrlshortenerApplication.java` from your IDE.

## Swagger UI

```
http://localhost:8080/swagger-ui.html
```

## Future Enhancements

- JWT Authentication
- User Registration & Login
- User-specific URL Management
- QR Code Generation
- Pagination
- Sorting
- Search
- Unit Testing
- Cloud Deployment

## Author

**Hemanth Sembilli**
