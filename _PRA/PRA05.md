# PRA05: Spring Boot JPA Inheritance and Abstraction

## CIFO La Violeta - FullStack IFCD0021-24 MF04-05

In this practical exercise, you will enhance the `RestaurantManager` project by implementing <mark>inheritance and abstraction concepts</mark>.



You'll refactor the `MenuItem` class, create subclasses, and implement an interface using JPA inheritance strategies.

### Objectives

- Refactor `MenuItem` to an <mark>abstract</mark> class
- <mark>Create subclasses</mark> for different menu item types
- Implement an interface for menu items
- Use JPA inheritance strategies

### Project Base

- Existing Repository: [Restaurant Manager](https://github.com/AlbertProfe/restaurantManager/)
- **Branch** with Abstract refactor: [RestaurantMananger feature-order-abstract]([GitHub - AlbertProfe/restaurantManager at b1bdddcf75c6772d4fd89d71d8370d778511abfe](https://github.com/AlbertProfe/restaurantManager/tree/b1bdddcf75c6772d4fd89d71d8370d778511abfe))
- Reference: [JPA Inheritance Strategies]([Spring Boot: JPA Inherence – albertprofe wiki](https://albertprofe.dev/springboot/boot-concepts-jpa-5.html))

### Tasks

1. Refactor `MenuItem` to Abstract Class
   
   - Convert the existing `MenuItem` class to an abstract class
   - Identify common properties and methods to keep in the abstract class

2. Create Subclasses
   
   - Create two subclasses: `MainCourse` and `Dessert`
   - Implement specific properties and methods for each subclass

3. Create `Interface` Class
   
   - Design an `interface` (e.g., `IMenuItem`) with common methods for menu items
   - Ensure the abstract `MenuItem` class implements this interface

4. Implement JPA <mark>Inheritance Strategy</mark>
   
   - Use the `@Inheritance annotation with strategy = InheritanceType.TABLE_PER_CLASS`
   - Adjust entity mappings for the abstract class and subclasses

5. Update Repository and Service Classes
   
   - Modify existing repository interfaces to work with the new class hierarchy
   - Update service classes to handle the abstract class and its subclasses

6. Create Test Classes
   
   - Develop test classes for the new class structure
   - Include tests for inheritance-specific scenarios

7. Update API Endpoints
   
   - Modify existing endpoints to work with the new class hierarchy
   - Add new endpoints if necessary to demonstrate subclass-specific operations

### Example Code Structure

```java
public interface IMenuItem {
    String getName();
    double getPrice();
    // Other common methods
}

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MenuItem implements IMenuItem {
    // Common properties and methods
}

@Entity
public class MainCourse extends MenuItem {
    // MainCourse specific properties and methods
}

@Entity
public class Dessert extends MenuItem {
    // Dessert specific properties and methods
}
```

### Submission Guidelines

- Fork the existing Restaurant Manager repository and clone to your local environment.
- Create a new branch named `PRA05-YourName` from the latest commit
- Implement the required changes and tests
- Commit your changes with clear, descriptive messages
- Push your branch to your forked repository
- Create a pull request to the AlbertProfe repository with a summary of your changes with title:
  `PRA05-YourName-InheritanceAndAbstraction`

### Evaluation Criteria

- Correct implementation of abstract class, subclasses, and interface
- Proper use of JPA inheritance strategy
- Comprehensive test coverage for the new class structure
- Effective use of abstraction and inheritance principles
- Code clarity and documentation quality
- Adherence to Spring Data JPA best practices

Remember to focus on creating a clean, well-structured inheritance hierarchy that demonstrates good object-oriented design principles. Good luck!
