# PRA03: Implementing ManyToMany Relationships in JPA

## CIFO La Violeta - FullStack IFCD0021-24 MF01-02-03

In this practical exercise, you will enhance the `RestaurantManager` project by implementing a `ManyToMany` relationship between `Menu` and `MenuItem` entities using JPA and Spring Boot.

<mark>You'll design, implement, and test this relationship using an in-memory H2 database </mark>and `JpaRepository`, with a focus on the automatic creation of a<mark> join table</mark> using `@JoinTable`.

## Objectives

Implement and test a `ManyToMany` relationship between `Menu` and `MenuItem` entities in the `RestaurantManager` project using **Spring Data JPA**, exploring both <mark>unidirectional and bidirectional relationships.</mark>

### Project Base

- Existing Repository: [Restaurant Manager](https://github.com/AlbertProfe/restaurantManager/commits/master/)
- Reference Lab: [Spring Boot Lab 8.3](https://albertprofe.dev/springboot/sblab8-3.html)
- Reference Lab: [Spring Boot Lab 8.4](https://albertprofe.dev/springboot/sblab8-4.html)

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
   
   - Create or update the `Menu` and `MenuItem` entity classes.
   - Implement a ManyToMany relationship between `Menu` and `MenuItem`.
   - Use appropriate JPA annotations such as `@ManyToMany` and `@JoinTable`.
   - <mark>Implement both unidirectional and bidirectional</mark> cases when it is possible.
   - Address the <mark>infinite recursion issue</mark> that can lead to `StackOverflowError`.

2. **Implement JPA Repositories**
   
   - Create JPA repository interfaces for both `Menu` and `MenuItem`.
   - ~~Add any necessary custom query methods.~~

3. **Create Service Layer**
   
   - Develop service interfaces and implementations for both entities.
   - Include methods to handle the relationship between `Menu` and `MenuItem`.
   - Implement CRUD operations ~~and any additional business logic.~~

4. **Develop REST Controllers**
   
   - Create REST controllers for `Menu` and `MenuItem`.
   - Implement endpoints that demonstrate the relationship between the entities.
   - Use appropriate HTTP methods for each operation.

5. **Configure H2 Database**
   
   - Set up the H2 in-memory database in your `application.properties` or `application.yml` file.
   - Configure Hibernate to create the schema automatically.

6. **Write Unit Tests**
   
   - Create unit tests for your repositories, services, and controllers.
   - Test the `ManyToMany` relationships thoroughly.
   - Verify that CRUD operations work correctly with the relationship in place.

7. **Test with Swagger API**
   
   - Configure Swagger for your Spring Boot application.
   - Use Swagger UI to test all implemented REST endpoints.
   - Verify that the relationship between `Menu` and `MenuItem` is correctly represented and functional.

### Example MenuItem Entity

Here's a proposed example for the `MenuItem` entity:

```java
@Entity
public class MenuItem {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;
    private String description;
    private boolean isSpicy;
    private boolean hasGluten;
    private boolean isAvailable;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    @ManyToMany(mappedBy = "menuItems")
    private Set<Menu> menus = new HashSet<>();

   // Default constructor
    public MenuItem() {
        this.id = UUID.randomUUID().toString();
    }

    // Constructors, getters, and setters
}

public enum CourseType {
    STARTER,
    MAIN,
    DESSERT
}
```

### Unidirectional vs Bidirectional ManyToMany

<mark>Explore the differences between unidirectional and bidirectional </mark>`ManyToMany` relationships:

- **Unidirectional**: Only one entity maintains the relationship.
- **Bidirectional**: Both entities are aware of the relationship.

Address the infinite recursion issue in bidirectional relationships that can lead to StackOverflowError when serializing to JSON.

Implement a solution using  `@JsonIgnore`,`@JsonManagedReference` and `@JsonBackReference` or by customizing the JSON serialization process.



**Unidirectional Relationship**

- Only one entity maintains the relationship
- Simpler to implement and manage
- Less coupling between entities
- May be sufficient for basic use cases

**Bidirectional Relationship**

- Both entities are aware of the relationship
- Provides more flexibility in querying and navigating the relationship
- Requires careful management to maintain consistency
- More suitable for complex domain models

**Addressing Infinite Recursion**

Bidirectional relationships can lead to infinite recursion when serializing to JSON, causing a StackOverflowError. This occurs because each entity references the other, creating an endless loop during serialization.

Solutions

1. **@JsonIgnore Annotation**
   
   - Apply `@JsonIgnore` to one side of the relationship
   - Prevents the annotated property from being serialized
   - Simple but may limit data access in some scenarios
   
   
   
   ```java
   public class Menu { 
   
   @ManyToMany
   @JsonIgnore
   private Set<MenuItem> menuItems; 
   }
   ```

2. **Custom Serialization**
   
   - Implement custom serializer classes
   - Provides fine-grained control over JSON output
   - More complex but offers greater flexibility

3. **DTO Pattern**
   
   - Create Data Transfer Objects (DTOs) for API responses
   - Allows explicit control over what data is transferred
   - Separates API representation from domain model

4. **Lazy Loading**
   
   - Use lazy loading for related entities
   - Prevents automatic loading of related entities unless explicitly requested
   - Requires careful handling in service layer

By implementing one or a combination of these solutions, you can effectively manage bidirectional relationships while avoiding infinite recursion issues in your Spring Boot application.

### Submission Guidelines

- Fork the existing [Restaurant Manager](https://github.com/AlbertProfe/restaurantManager/commits/master/) repository and clone it to your local environment.
- Create a new branch named `PRA03-YourName` from the latest commit.
- Commit your changes with clear, descriptive messages.
- Push your branch to your forked repository.
- Create a pull request to the AlbertProfe repository with a summary of your changes, titled:
  - `PRA03-YourName-ManyToManyRelationshipImplementation`

### Evaluation Criteria

- Correct implementation of the ManyToMany relationship with automatic join table creation.
- Proper use of JPA annotations and Spring Data JPA features.
- Functionality of the service layer and controllers in handling the relationship.
- Quality and coverage of unit tests.
- Successful configuration and use of the H2 in-memory database.
- Clarity and completeness of Swagger API documentation and tests.
- Proper handling of bidirectional relationship and infinite recursion issues.
- Overall code quality, organization, and documentation.

Good luck with your implementation! Remember to test thoroughly and document your code clearly.
