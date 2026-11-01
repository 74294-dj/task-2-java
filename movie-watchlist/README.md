# Movie Watchlist (Task 2)

REST API (backend only) for a personal movie watchlist.
Spring Boot, Spring Data JPA, H2 in-memory database, bean validation, Swagger UI.

## Run
1. Java 17+ and Maven.
2. `mvn spring-boot:run` or run `WatchlistApplication` in IntelliJ.

| Tool | URL |
|------|-----|
| Swagger UI | http://localhost:8080/swagger-ui/index.html |
| H2 console | http://localhost:8080/h2 (JDBC URL `jdbc:h2:mem:watchlistdb`, user `sa`, empty password) |

## Endpoints
Genres: ACTION, COMEDY, DRAMA, HORROR, SCIFI, OTHER

| Method | URL | Body | Success | Errors |
|--------|-----|------|---------|--------|
| POST | `/api/movies` | `{"title":"Dune","genre":"SCIFI","rating":9}` | 201 + movie | 400 validation |
| GET | `/api/movies/{id}` | | 200 + movie | 404 |
| GET | `/api/movies` | | 200 + list | |
| GET | `/api/movies?watched=false` | | 200 + filtered list | |
| PUT | `/api/movies/{id}` | same as POST | 200 + movie | 400, 404 |
| PATCH | `/api/movies/{id}/watched` | none | 200 + movie | 404 |
| DELETE | `/api/movies/{id}` | | 204 | 404 |

Error example: `{"message":"Validation failed","details":["title must not be blank"]}`

Check the table in the H2 console: `SELECT * FROM MOVIES;`

(Add screenshots of each request/response here.)
