# PRA02: Implementing OneToMany and ManyToOne Relationships in JPA

## CIFO La Violeta - FullStack IFCD0021-24 MF01-02-03

In this practical exercise, you will enhance the <mark>RestaurantManager</mark> project by implementing a `OneToMany` and `ManyToOne` relationship between `EatInOrder` and `TableRestaurant` entities using JPA.

You'll design, implement, and test this relationship using an in-memory H2 database and `JpaRepository`.

## Objectives

Implement and test a <mark>OneToMany/ManyToOne</mark> relationship between `EatInOrder` and `TableRestaurant` entities in the RestaurantManager project using **Spring Data JPA**.

### Project Base

- Existing Repository: [Restaurant Manager](https://github.com/AlbertProfe/restaurantManager/commits/master/)
- Reference Lab: [Spring Boot Lab 8.3](https://albertprofe.dev/springboot/sblab8-3.html)
- Reference Lab: [Spring Boot Lab 8.4]([Lab#SB08-4: JPA – albertprofe wiki](https://albertprofe.dev/springboot/sblab8-4.html))
- Commit project: RestaurantManager [BookingTable]([GitHub - AlbertProfe/restaurantManager at c562d8da650da2f758754312c0e1ecea2d8909af](https://github.com/AlbertProfe/restaurantManager/tree/c562d8da650da2f758754312c0e1ecea2d8909af))

### Tasks

Summary tasks:

- [ ] Design Entity Classes

- [ ] Implement JPA Repositories

- [ ] Create Service Layer

- [ ] Develop REST Controllers

- [ ] Configure H2 Database

- [ ] Write Unit Tests

- [ ] Test with Swagger API

Tasks:

1. **Design Entity Classes**
   
   - Create or update the `EatInOrder` and `TableRestaurant` entity classes.
   - Implement a OneToMany relationship from `TableRestaurant` to `EatInOrder`.
   - Implement the corresponding ManyToOne relationship from `EatInOrder` to `TableRestaurant`.
   - Use appropriate JPA annotations such as `@OneToMany`, `@ManyToOne`, and `@JoinColumn`, etc.
   - Implement both cases: unidirectional/biderctional.

2. **Implement JPA Repositories**
   
   - Create JPA repository interfaces for both `EatInOrder` and `TableRestaurant`.
   - ~~Extend `JpaRepository<T, ID>` for each entity.~~
   - Add any necessary custom query methods.

3. **Create Service Layer**
   
   - Develop service interfaces and implementations for both entities.
   - Include methods to handle the relationship between `EatInOrder` and `TableRestaurant`.
   - Implement CRUD operations and any additional business logic.

4. **Develop REST Controllers**
   
   - Create REST controllers for `EatInOrder` and `TableRestaurant`.
   - Implement endpoints that demonstrate the relationship between the entities.
   - Use appropriate HTTP methods for each operation.

5. **Configure H2 Database**
   
   - Set up the H2 <mark>in-memory</mark> database in your `application.properties` or `application.yml` file.
   - Configure Hibernate to create the schema automatically.

6. **Write Unit Tests**
   
   - Create unit tests for your repositories, services, and controllers.
   - Test the OneToMany and ManyToOne relationships thoroughly.
   - Verify that CRUD operations work correctly with the relationship in place.

7. **Test with Swagger API**
   
   - Configure Swagger for your Spring Boot application.
   - Use Swagger UI to test all implemented REST endpoints.
   - Verify that the relationship between `EatInOrder` and `TableRestaurant` is correctly represented and functional.

### Submission Guidelines

- Fork the existing [Restaurant Manager](https://github.com/AlbertProfe/restaurantManager/commits/master/) repository and clone it to your local environment.
- Create a new branch named `PRA03-YourName` from the latest commit.
- Commit your changes with clear, descriptive messages.
- Push your branch to your forked repository.
- <mark>Create a pull request to the AlbertProfe</mark> repository with a summary of your changes, titled:
  - `PRA03-YourName-OneToManyManyToOneRelationshipImplementation`

### Evaluation Criteria

- Correct implementation of the OneToMany and ManyToOne relationships.
- Proper use of JPA annotations and Spring Data JPA features.
- Functionality of the service layer and controllers in handling the relationship.
- Quality and coverage of unit tests.
- Successful configuration and use of the H2 in-memory database.
- Clarity and completeness of Swagger API documentation and tests.
- Overall code quality, organization, and documentation.

Good luck with your implementation! Remember to test thoroughly and document your code clearly.

Citations:
[1] https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
[2] https://www.youtube.com/watch?v=4xoZ0rsUwbc
[3] https://codingnomads.com/spring-data-jpa-one-to-many-relationship
[4] https://discourse.hibernate.org/t/how-to-nest-two-abstract-inheritancetype-table-per-class-entities-with-a-onetomany-relationship-to-each-other/5236
[5] https://dev.to/sohailshah/jpa-one-to-many-many-to-one-and-many-to-many-relationships-3an1
[6] https://www.youtube.com/watch?v=ctwRpskAeIU
[7] https://www.baeldung.com/jpa-mapping-single-entity-to-multiple-tables
[8] https://stackoverflow.com/questions/22119155/loading-and-sorting-two-onetomany-relationships-at-once-with-jpa
