# How to Run

## 1. Prerequisites

Make sure the following are installed:

- **Java 17 or later** (the project is built with Java 21)
- **Maven**
- **PostgreSQL**

## 2. Create the Database

Create an empty PostgreSQL database named `megasyariah`:

```sql
CREATE DATABASE megasyariah;
```

The `books` table will be generated automatically when the application starts. No additional DDL scripts are required.

> **Note:** Automatic table generation is for this assessment only. In a real project, tables are created through a DDL script sent to the relevant team, not by the application

## 3. Configure Database Credentials

Open `src/main/resources/application.properties` and configure your PostgreSQL connection settings.

| Property | Example |
|---|---|
| `spring.datasource.url` | `jdbc:postgresql://localhost:5432/megasyariah` |
| `spring.datasource.username` | `postgres` |
| `spring.datasource.password` | Your PostgreSQL password |

Example configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/megasyariah
spring.datasource.username=postgres
spring.datasource.password=your_password
```

Replace the example values with your local PostgreSQL credentials.

> **Security:** Do not commit real database credentials to the repository.

## 4. Run the Application

From the project root directory, run:

```bash
mvn spring-boot:run
```

Once the application starts successfully, the API will be available at:

`http://localhost:9021`

## 5. API Examples

The following examples can be tested using Postman.

### Create a Book

- **Method:** `POST`
- **URL:** `http://localhost:9021/api/books`
- **Body:** `raw` → `JSON`

```json
{
  "title": "The Alchemist",
  "author": "Paulo Coelho",
  "isbn": "456",
  "publishedDate": "2026-10-10"
}
```

### Get All Books

- **Method:** `GET`
- **URL:** `http://localhost:9021/api/books`

### Get a Book by ID

- **Method:** `GET`
- **URL:** `http://localhost:9021/api/books/3`

### Update a Book

- **Method:** `PUT`
- **URL:** `http://localhost:9021/api/books/3`
- **Body:** `raw` → `JSON`

```json
{
  "title": "Aku",
  "author": "Tere Liye",
  "isbn": "120",
  "publishedDate": "2024-10-09"
}
```

### Partially Update a Book

- **Method:** `PATCH`
- **URL:** `http://localhost:9021/api/books/3`
- **Body:** `raw` → `JSON`

```json
{
  "title": "Hujan"
}
```

> **Note:** The book IDs used in these examples are illustrative. Use an ID that exists in your database when testing the `GET`, `PUT`, and `PATCH` endpoints.

## 6. Postman Collection
See [Postman Collection](./docs/MegaSyariah.postman_collection.json)

## 7. ER Diagram

See [ER Diagram](./docs/er-diagram.md) for the database schema and column constraints.

## Limitations and possible improvements

This API is intentionally kept simple, so a few safeguards and features are not implemented:
- **No "no change" check on update.** A PUT or PATCH with values identical to the stored ones is still processed and returns a normal success response.
- **Hard delete.** `DELETE` removes the row permanently. There is no soft delete or way to restore a book.
- **No timestamps.** The table has no `created_at`, `modified_at` or `deleted_at` columns, so there is no history of when a book was added, changed or removed.
- **Simple validation only.** The ISBN is accepted as any non-blank text of 1 to 20 characters. Its format (for example 10 or 13 digits) and check digit are not verified.

