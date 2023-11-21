# Backend Task Empik

This repository contains the backend implementation for the Empik backend task. The application is built using Spring Boot and provides endpoints to fetch user information from the GitHub API and perform related calculations.

## Getting Started

### Prerequisites

- Java JDK 11 or later
- Maven

### Build and Run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/backend-task-empik.git
   cd backend-task-empik

2. **Build the application:**

   ```bash
   mvn clean install

3. **Run the application:**

   ```bash
   mvn spring-boot:run

The application will start at http://localhost:8080.

## API Endpoints
### Get User by Login
* **Endpoint:**

   ```bash
   GET /users/{login}

* **Description:**

      Fetch user information from GitHub by providing the user's login.
