# Property Management System

The Property Management System is a Java-based application designed for managing properties, property owners, and property repairs. It features a user-friendly console interface and is built using the Java Persistence API (JPA) for seamless interaction with the database.

## Features
- **Property Owner Management**: Create, update, and delete property owners.
- **Property Management**: Add, update, and delete properties, and view all properties associated with an owner.
- **Property Repair Management**: Submit repair requests, update repair statuses, and view repair histories.
- **Reports Generation**: Generate and view reports for properties and repairs.
- **Console-based UI**: Simple and interactive console menu for managing all operations.

## Package Overview

### 1. `exceptions`
**Purpose**: This package contains custom exception classes used throughout the application to handle specific error scenarios, such as invalid data input or failed operations. These exceptions provide more meaningful error messages and help in debugging and maintaining the application.

### 2. `models`
**Purpose**: This package includes the entity classes that represent the core objects in the system, `Property`, `PropertyOwner`, and `PropertyRepair`. These classes are mapped to database tables and are used by the JPA to persist data.

### 3. `repositories`
**Purpose**: The repository package provides interfaces and their implementations for data access operations. These classes handle the interaction with the database, such as querying, saving, updating, and deleting records for the entities.

### 4. `com.services`
**Purpose**: This package contains service classes that implement the business logic of the application. The service layer interacts with the repository layer to perform operations and apply any necessary business rules before data is persisted or retrieved.

### 5. `ui`
**Purpose**: This package houses the console-based user interface classes. These classes handle user interaction, provide menus, and collect input from users to perform operations like creating, updating, or viewing properties and repairs.

### 6. `enums`
**Purpose**: This package contains enumerations used in the application, such as `PropertyType` and `RepairType`. Enums provide a predefined set of constants that make the code more readable and reduce the likelihood of invalid values being used.

Each package is organized to follow a layered architecture, ensuring separation of concerns and making the application more modular, maintainable, and scalable.
