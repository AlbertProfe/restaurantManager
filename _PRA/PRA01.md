# PRA01: Spring Boot JPA Repository and Entity Class Exercise

## CIFO La Violeta - FullStack IFCD0021-24 MF01-02-03

In this practical exercise, you will enhance the existing Restaurant Manager project by implementing JPA repositories, entity classes, and related components. You'll be working with the **TABLE** and **MENUS** classes, transforming them into entities and creating the necessary supporting structures.

## Objectives

Enhance the existing Restaurant Manager project by implementing JPA repositories, entity classes, and related components.

### Project Base

- Existing Repository: [Restaurant Manager](https://github.com/AlbertProfe/restaurantManager/commits/master/)[1]
- Reference Lab: [Spring Boot Lab 8.3](https://albertprofe.dev/springboot/sblab8-3.html)[2]

### Tasks

Summary tasks

- [ ] Update Entity Classe
- [ ] Create Faker Classe
- [ ] Develop Service Layer
- [ ] Implement JPA Repositories
- [ ] Design REST Controllers
- [ ] Test with Swagger API



1. **Update Entity Classes**
   
   - Transform the **TABLE** and **MENUS** classes into JPA entities.
   - Add appropriate annotations such as `@Entity`, `@Id`, and do not use `@GeneratedValue.`Use @id string <mark>UUID</mark>.
   - ~~Define relationships between entities if necessary.~~

2. **Create Faker Classes**
   
   - Implement faker classes for **TABLE** and **MENUS** to generate sample data.
   - Use libraries like **Faker** or **Java Faker** to create realistic mock data.

3. **Develop Service Layer**
   
   - Create service interfaces for **TABLE** and **MENUS**.
   - <mark>Implement the service interfaces </mark>with concrete classes.
   - Include methods for CRUD operations and any additional business logic.

4. **Implement JPA Repositories**
   
   - Create JPA repository `interfaces` for **TABLE** and **MENUS**.
   - Extend `JpaRepository<T, ID>` for each entity.
   - ~~Add any custom query methods if required.~~

5. **Design REST Controllers**
   
   - <mark>Develop REST controllers</mark> for **TABLE** and **MENUS**.
   - Implement endpoints for CRUD operations.
   - Use appropriate HTTP methods (GET, POST, PUT, DELETE) for each operation.

6. **Test with Swagger API**
   
   - Configure `Swagger` for your Spring Boot application.
   - Use `Swagger` UI to test all implemented REST endpoints.
   - Verify CRUD operations for both **TABLE** and **MENUS** entities.

### Submission Guidelines

- Fork the existing [Restaurant Manager](https://github.com/AlbertProfe/restaurantManager/commits/master/) repository and clone to your local environment.
- Create a new branch named `PRA01-YourName`from the commit `PRA01: Spring Boot JPA Repository and Entity Class Exercise`
- Commit your changes with clear, descriptive messages.
- **Push** your branch to your forked repository.
- Create a <mark>pull request to the AlbertProfe repository</mark> with a summary of your changes with title: 
  - `PRA01-YourName-SpringBootJPARepositoryAndEntityClassExercise`

### Evaluation Criteria

- Correct implementation of JPA entities and repositories.
- Proper use of Spring Boot annotations and best practices.
- Functionality of service layer and controllers.
- Quality and coverage of Swagger API tests.
- Code clarity and documentation.

Good luck with your implementation! And remember, **code slow to go far**

Citations:
[1] https://github.com/AlbertProfe/restaurantManager/commits/master/
[2] https://albertprofe.dev/springboot/sblab8-3.ht
