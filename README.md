# Game Collection Manager – MVC Application

## Overview

Game Collection Manager is a Spring Boot web application that allows users to manage their personal video game collections. Users can browse available games, add them to their libraries, track their progress, rate titles, and manage their collections through an intuitive web interface.

The application has been developed using a **microservice architecture**, separating the main MVC application from a dedicated REST-based Game Service responsible for game-related data and operations.

The project demonstrates modern Java and Spring development practices, including MVC architecture, REST APIs, authentication and authorization, database management, inter-service communication, validation, exception handling, caching, scheduled tasks, and automated testing.

---

## Architecture

The project consists of two Spring Boot applications:

### Main MVC Application

The main application is responsible for:

* User registration and authentication
* User profiles and game libraries
* Managing User and UserGame data
* Rendering the web interface using Thymeleaf
* Communicating with the Game Service
* Authentication and authorization
* Scheduled tasks and caching

### Game Service

The Game Service is a separate REST microservice responsible for:

* Game CRUD operations
* Genre management
* Platform management
* Game-related validation
* REST API endpoints
* Game data persistence

**Game Service Repository:**

> https://github.com/Reyruko/game-service

The Main MVC Application communicates with the Game Service through REST-based clients.

---

## Features

### User Features

* User registration and authentication
* Browse available games
* Add games to a personal collection
* Update game status and ratings
* Track hours played
* Mark games as favorites
* Remove games from the collection
* View personal profile and game library
* Manage personal game information

### Admin & Moderator Features

* Administrative dashboard
* View and manage registered users
* Manage user roles and permissions
* Add new games
* Edit existing games
* Manage genres
* Manage platforms
* Moderator access to game management functionality

---

## Security

The application uses **Spring Security** to provide:

* Secure authentication and login handling
* Role-based authorization
* `USER`, `ADMIN`, and `MODERATOR` roles
* Protected endpoints and restricted access
* Secure logout functionality
* Restricted administrative and game management operations

---

## Data Validation & Error Handling

Validation is implemented across the application's different layers to ensure valid and consistent data.

This includes:

* DTO validation using Jakarta Bean Validation
* Entity-level validation
* Service-layer business validation
* Validation of REST API requests
* Custom exception classes
* Global exception handling
* Meaningful HTTP status codes
* User-friendly error messages
* Duplicate game, genre, and platform handling
* Resource-not-found handling

The Game Service exposes appropriate REST error responses, while the Main MVC Application handles API-related errors and presents meaningful feedback to users.

---

## Scheduling & Caching

The application uses Spring's scheduling and caching mechanisms.

### Scheduled Tasks

The project contains:

* A scheduled job using a **cron expression**
* A scheduled job using a **non-cron trigger**

The scheduled tasks affect application behaviour and demonstrate the use of Spring's scheduling functionality.

### Caching

Spring's caching mechanism is used to reduce unnecessary database and service calls.

Caching is implemented for frequently accessed data and includes appropriate cache management when relevant data is created, updated, or deleted.

---

## Inter-Service Communication

The Main MVC Application communicates with the Game Service using REST clients.

The project demonstrates:

* REST-based microservice communication
* Spring `RestClient`
* Declarative communication using **OpenFeign**
* DTO-based communication between applications
* REST error handling
* Separation of responsibilities between services

---

## Technology Stack

### Backend

* Java 21
* Spring Boot
* Spring MVC
* Spring Security
* Spring Data JPA
* Hibernate / JPA
* Spring REST
* Spring Cloud OpenFeign
* Spring Scheduling
* Spring Cache
* Jakarta Bean Validation

### Frontend

* Thymeleaf
* HTML5
* CSS3
* JavaScript (ES6)

### Database & Build Tools

* MySQL
* Maven
* JUnit 5

---

## Architecture – Main Application

The Main MVC Application follows a layered **Model–View–Controller** architecture.

### Model

Represents application data and database entities.

### View

Thymeleaf templates responsible for rendering the user interface.

### Controller

Handles HTTP requests and coordinates application flow.

### Service Layer

Contains business logic and communication with the Game Service.

### Repository Layer

Provides database access through Spring Data JPA.

### REST Client Layer

Responsible for communication between the Main MVC Application and the Game Service.

---

## Architecture – Game Service

The Game Service follows a REST-based layered architecture.

### Controller Layer

Exposes REST API endpoints for game, genre, and platform operations.

### Service Layer

Contains game-related business logic and validation.

### Repository Layer

Provides database access using Spring Data JPA.

### DTO Layer

Handles data transfer between the REST API and clients while keeping the API separate from the persistence entities.

---

## Core Entities

### User

Represents registered application users.

### UserGame

Join entity that manages the relationship between users and games, including:

* Game status
* Personal rating
* Hours played
* Favorite status
* Collection information

### Game

Represents a video game managed by the Game Service.

Game information includes:

* Name
* Developer
* Publisher
* Description
* Release date
* Genres
* Platforms

### Genre

Represents a game genre managed by the Game Service.

### Platform

Represents a gaming platform managed by the Game Service.

---

## Error Handling

Custom exception handling is implemented throughout both applications to provide meaningful feedback and avoid default server error pages.

Examples include:

* User not found
* Game not found
* Duplicate game
* Duplicate username
* Duplicate email
* Genre not found
* Platform not found
* Unauthorized access
* Invalid requests
* REST API communication errors

The Game Service uses a global exception handler to return appropriate HTTP responses, while the Main MVC Application converts service/API errors into user-friendly responses.

---

## Getting Started

### Prerequisites

* Java 21
* MySQL
* Maven

### Database Configuration

The Main MVC Application uses a MySQL database named:

```text
game_collection_manager
```

The database will be created automatically if it does not already exist.

The application expects the following environment variable:

```text
DB_PASSWORD
```

If `DB_PASSWORD` is not provided, an empty password is used by default.

### Installation

1. Clone the Main MVC Application repository:

```bash
git clone <repository-url>
```

2. Clone the Game Service repository:

```bash
git clone <game-service-repository-url>
```

3. Start your MySQL server.

4. Configure the database password if required:

```text
DB_PASSWORD=your_password
```

5. Start the Game Service on its configured port.

6. Start the Main MVC Application:

```bash
mvn spring-boot:run
```

7. Open the application in your browser:

```text
http://localhost:8080
```

---

## Future Improvements

* Pagination for large game libraries
* Advanced search and filtering
* Enhanced UI/UX design
* More extensive automated test coverage
* Advanced game recommendation features
* Additional microservices and service integrations

---

## Learning Outcomes

This project demonstrates:

* Full-stack Java web development
* Spring Boot application development
* MVC architecture
* Microservice architecture
* REST API development
* REST client communication
* OpenFeign
* Authentication and authorization with Spring Security
* Role-based access control
* Relational database design
* JPA/Hibernate entity relationships
* DTO-based application design
* Data validation
* Global exception handling
* Spring caching
* Scheduled tasks
* Clean architecture and separation of concerns
* Server-side rendering with Thymeleaf
