![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

# Game Collection Manager MVC Application

## Overview

Game Collection Manager is a Spring Boot MVC web application that allows users to manage their personal video game collections. Users can browse available games, add them to their libraries, track their progress, rate titles, and manage their collections through an intuitive web interface.

The application demonstrates modern Java web development practices, including MVC architecture, authentication and authorization, database management, and clean separation of concerns.

---

## Features

### User Features

* User registration and authentication
* Browse available games
* Add games to a personal collection
* Update game status and ratings
* Remove games from the collection
* View personal profile and library

### Admin Features

* Administrative dashboard
* View and manage registered users
* Manage user roles and permissions
* User management functionality

---

## Security

The application uses Spring Security to provide:

* Secure authentication and login handling
* Role-based authorization (`USER`, `ADMIN`)
* Protected endpoints and restricted access
* Secure logout functionality

---

##  Technology Stack

### Backend

* Java
* Spring Boot
* Spring MVC
* Spring Security
* Hibernate / JPA

### Frontend

* Thymeleaf
* HTML5
* CSS3
* JavaScript (ES6)

### Database & Build Tools

* MySQL
* Maven

---

##  Architecture

The project follows the **Model–View–Controller (MVC)** architectural pattern:

### Model

Represents application data and database entities.

### View

Thymeleaf templates responsible for rendering the user interface.

### Controller

Handles HTTP requests and coordinates application flow.

### Service Layer

Contains business logic and application rules.

### Repository Layer

Provides database access through Spring Data JPA.

---

## Core Entities

### User

Represents registered application users.

### Game

Stores information about available games.

### UserGame

Join entity that manages the relationship between users and games, including personal ratings and status tracking.

---

##  Error Handling

Custom exception handling is implemented throughout the application to provide meaningful feedback and improve the user experience by avoiding default server error pages.

---

##  Getting Started

### Running the Application
### Prerequisites:
* Java 21
* MySQL
* Maven
* Database Configuration

### The application uses a MySQL database named:

game_collection_manager

The database will be created automatically if it does not already exist.

#### The application expects the following environment variable:

DB_PASSWORD

If DB_PASSWORD is not provided, an empty password is used by default.

### Installation

1. Clone the repository:
   git clone <repository-url>
2. Start your MySQL server.
   Configure the database password if required:
   DB_PASSWORD=your_password
3. Run the application:
   mvn spring-boot:run
4. Open your browser and navigate to:
   http://localhost:8080

---

##  Future Improvements

* Pagination for large game libraries
* Advanced search and filtering
* REST API implementation
* Enhanced UI/UX design
* Unit and integration testing
* Game cover image support
* Sorting and recommendation features

---

##  Learning Outcomes

This project demonstrates:

* Full-stack Java web development
* Spring Boot application development
* MVC architecture implementation
* Authentication and authorization with Spring Security
* Relational database design
* JPA/Hibernate entity relationships
* Clean architecture and separation of concerns
* Server-side rendering with Thymeleaf
