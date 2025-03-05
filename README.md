# Spring Boot REST API

A simple REST API project built with Spring Boot 3.2.3 that demonstrates CRUD operations for customer management.

## Technologies Used

- Java 21
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Lombok
- Maven

## Prerequisites

- Java 21 or later
- Maven 3.6 or later

## Getting Started

1. Clone the repository
2. Navigate to the project directory
3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Customer Management

- `GET /api/customers` - Get all customers
- `GET /api/customers/{id}` - Get a specific customer by ID
- `POST /api/customers` - Create a new customer
- `PUT /api/customers/{id}` - Update an existing customer
- `DELETE /api/customers/{id}` - Delete a customer

### H2 Database Console

- Access the H2 database console at `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:customerdb`
- Username: `sa`
- Password: `password`

## Project Structure

```
src/main/java/com/example/api/
├── Application.java
├── controller/
│   └── CustomerController.java
├── entity/
│   └── Customer.java
└── repository/
    └── CustomerRepository.java
```

## Features

- RESTful API design
- JPA/Hibernate for database operations
- In-memory H2 database
- Lombok for reducing boilerplate code
- Maven for dependency management and build
