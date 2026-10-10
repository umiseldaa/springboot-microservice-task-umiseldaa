# ER Diagram

The service has a single table, `books`. 

```mermaid
erDiagram
    BOOKS {
        BIGINT id PK "auto-generated (identity)"
        VARCHAR title "NOT NULL"
        VARCHAR author "NOT NULL"
        VARCHAR isbn "NOT NULL, UNIQUE, max 20 chars"
        DATE published_date "required by the API"
    }
```

## Column notes

| Column | Type | Constraints / behavior |
|---|---|---|
| id | BIGINT | Primary key, identity |
| title | VARCHAR(255) | NOT NULL |
| author | VARCHAR(255) | NOT NULL |
| isbn | VARCHAR(20) | NOT NULL, UNIQUE |
| published_date | DATE | Required by the API, validated as not in the future |
