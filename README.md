**Architecture Overview**
Presentation Layer (Controller)
Receives HTTP requests from clients (e.g., web or mobile apps).
Maps REST API endpoints (GET, POST, DELETE).
Delegates processing to the service layer.
Returns HTTP responses (JSON) to clients.

Service Layer
Contains business logic and rules.
Interacts with the repository layer for data operations.
Handles exceptions such as restaurant or item not found.
Coordinates operations involving multiple entities or transactions.

Data Access Layer (Repository)
Interfaces with the database using Spring Data JPA.
Provides CRUD operations for Restaurant and Item entities.
Abstracts away SQL queries using JPA and method naming conventions.

Domain Layer (Entity Classes)
Models the main data structures, i.e., Restaurant and Item.
Contains JPA annotations for ORM mapping to database tables.
Includes essential business logic related to domain, e.g., menu management and open/closed status.

Database Layer
MySQL database with two tables: restaurant and item.
Enforces foreign key relationship with cascade delete.
Schema created and optionally pre-populated via SQL script (DBLoadScript.sql).

**Data Flow**
1.Client Request:
A client sends an HTTP request to a REST endpoint, e.g., GET /api/v1/restaurants.

2.Controller Handling:
The RestaurantController receives this request and calls the appropriate method on the RestaurantService.

3.Business Logic Processing:
The RestaurantService performs the required business logic. For retrieving restaurants, it calls findAll() on the RestaurantRepository.

4.Database Interaction:
The RestaurantRepository interfaces with MySQL using JPA to fetch the restaurant data and their menu items.

5.Data Mapping:
JPA maps SQL result sets to Java entities (Restaurant and Item).

6.Returning Response:
The service returns the data to the controller, which sends it back as JSON in the HTTP response.

7.Error Handling:
If something goes wrong (e.g., restaurant not found), exceptions propagate from repository to service to controller, which returns appropriate HTTP error responses.
