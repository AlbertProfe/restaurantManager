# PRA04: Refactoring Many-to-Many Relationship in RestaurantManager

## CIFO La Violeta - FullStack IFCD0021-24 MF04-05

> In this practical exercise, you will refactor the `RestaurantManager` project to enhance the relationship between `Order` and `Menu` entities.

The goal is to transform the current many-to-many relationship **without a join table** into a **many-to-many relationship with a join table entity**, allowing for multiple instances of the same menu in an order.

Objectives

- <mark>Refactor</mark> the existing many-to-many relationship between `Order` and `Menu`
- Implement a new entity to serve as a join table
- <mark>Update</mark> repositories and services to accommodate the new structure
- Modify and create new test cases for the refactored relationship

Project Base

- Existing Repository: Restaurant Manager[1]
- Reference: Spring Data JPA Many-to-Many Relationship Documentation[2]

Tasks

1. Create a New Join Table Entity
   
   - **Create a new entity class (e.g., OrderMenuQty) to serve as the join table**
   - Include necessary fields such as id, quantity, and any other relevant information
   - Establish relationships with `Order` and `Menu` entities

2. Update Order and Menu Entities
   
   - **Modify the existing relationship annotations** in `Order` and `Menu` classes
   - Update to use the <mark>new join table entity</mark>

3. Create Repository for Join Table Entity
   
   - Implement a new repository interface for the join table entity
   - Add any necessary derived query methods

4. Update Service Layer
   
   - Modify existing service methods to work with the new structure
   - Implement new methods to handle operations involving multiple menu items in an order

5. Refactor Existing Tests
   
   - Update existing test cases to reflect the new relationship structure
   - Ensure all previous functionality is still correctly tested

6. Create New Test Cases
   
   - Implement new test cases to verify the ability to add multiple instances of the same menu to an order
   - ~~Test scenarios involving quantity changes and removals~~

7. Update API Endpoints (if applicable and optional)
   
   - If your project includes a REST API, update the relevant endpoints to support the new structure

8. Documentation (optional)
   
   - ~~Update JavaDoc/Swagger comments for all modified and new classes/methods~~
   - Provide clear explanations of the new relationship structure in the project README or an updated UML in the documentation project.

Example structure for the new join table entity:

```java
@Entity
public class OrderMenuQty {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private int quantity;

    // Constructors, getters, setters
}
```

Submission Guidelines

- **Fork** the existing `RestaurantManager` repository and clone to your local environment.
- Create a new branch named `PRA04-YourName` from the latest commit
- **Implement** the required changes and tests
- **Commit** your changes with clear, descriptive messages
- **Push** your branch to your forked repository
- <mark>Create a pull request</mark> to the **AlbertProfe repository** with a summary of your changes with title:
  `PRA04-YourName-ManyToManyRefactoring`

Evaluation Criteria

- Correct implementation of the new many-to-many relationship with join table entity
- Proper updating of existing code to work with the new structure
- Comprehensive test coverage for new and modified functionality
- Clear and informative documentation of changes
- Adherence to Spring Data JPA best practices

Focus on maintaining the existing functionality while implementing the new relationship structure. Ensure your changes are well-tested and documented. Good luck!

Citations:
[1] https://github.com/AlbertProfe/restaurantManager/
[2] https://docs.spring.io/spring-data/jpa/reference/jpa/entity-persistence.html#jpa.entity-persistence.association.many-to-many
