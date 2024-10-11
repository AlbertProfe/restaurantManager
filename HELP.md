# Restaurant Manager Application

This Spring Boot application is designed to manage various aspects of a restaurant's operations. It provides functionality for customer management, bookings, menu items, orders, and tableRestaurant management.

## Project Structure

- **Controller Layer**: Handles HTTP requests and defines API endpoints.
    - `CustomerController`: REST API for customer operations.
    - `CustomerWebController`: Web controller for customer-related views.

- **Model Layer**: Defines the data structures.
    - `Customer`: Represents customer information.
    - `Booking`: Manages reservation details.
    - `Menu`: Stores menu items.
    - `Order`: Tracks customer orders.
    - `Table`: Represents restaurant tables.

- **Repository Layer**: Interfaces with the database.
    - `CustomerRepository`: Handles CRUD operations for customers.

- **Utilities**: Contains helper classes.
    - `CustomerDataLoader`: Generates fake customer data for testing/development.

## Key Features

- Customer management (CRUD operations)
- Booking system
- Menu management
- Order tracking
- Table management

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Thymeleaf (for server-side templating)
- Maven (for dependency management)

## Getting Started

1. Clone the repository
2. Navigate to the project root
3. Run `mvn spring-boot:run` to start the application
4. Access the application at `http://localhost:8080`

This project follows a standard Spring Boot structure, making it easy to extend and maintain. The separation of concerns is clear, with distinct layers for controllers, models, and data access.