# School Management System - JavaFX Application

## Overview
This project implements a School Management System using JavaFX for the graphical user interface. The application provides functionalities for managing users, events, rooms, reservations, and terrains, ensuring a modular and well-organized structure.

## Project Structure
The project follows the Model-View-Controller (MVC) architecture, ensuring separation of concerns for better maintainability and scalability. Below is an explanation of the main directories:

### 1. `App`
- **`MainApp.java`**: The entry point of the application that initializes and launches the JavaFX application.

### 2. `dao`
- Contains Data Access Objects (DAO) for interacting with the database.
- Each entity (e.g., User, Event, Room) has its corresponding DAO for CRUD operations.

### 3. `model`
- Holds the data models representing the application's entities.
- Examples: User, Event, Room, Reservation, and Terrain.

### 4. `view`
- Contains JavaFX views for different parts of the application. Each view is represented by a separate class:
  - **`DashboardView.java`**: Displays an overview of the application.
  - **`EvenementView.java`**: Handles the user interface for managing events.
  - **`LoginView.java`**: Provides the login interface for users.
  - **`RegisterView.java`**: Allows users to register in the system.
  - **`ReservationView.java`**: Facilitates reservation management.
  - **`SalleView.java`**: Manages room-related information.
  - **`TerrainView.java`**: Manages terrain-related data.

### 5. `utils`
- Contains utility classes for shared functionality such as input validation, date formatting, and database connection management.

### 6. `resource`
- Holds external resources like the database file.

### 7. `test`
- Includes Junit tests to verify the application's functionality.

## Features
1. **Login and Registration**:
   - Secure user authentication.
   - User registration with input validation.

2. **Dashboard**:
   - Provides an overview of key metrics and navigation options.

3. **Event Management**:
   - Create, view, update, and delete events.

4. **Room and Terrain Management**:
   - Manage details for rooms and terrains.

5. **Reservation System**:
   - Allows users to reserve rooms and terrains with specific requirements.

## Setup and Execution
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Import the project into your IDE (e.g., IntelliJ IDEA or Eclipse).
3. Ensure Java 17 and JavaFX SDK are installed.
4. Run `MainApp.java` to start the application.

## Example Code
**Launching the Main Application:**
```java
@Override
    public void start(Stage stage) {
        primaryStage = stage;
        instance = this;
        showLogin();
    }
```

## Future Enhancements
1. Add role-based access control for different user types.
2. Integrate notifications for upcoming reservations and events.
3. Implement data visualization for metrics on the dashboard.
