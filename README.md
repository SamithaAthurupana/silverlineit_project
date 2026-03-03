# Course Content Management System

A robust, full-stack solution for managing academic resources. This application leverages **Spring Boot 3** and **React** to provide a secure environment for uploading, storing, and retrieving course-related files.

## 🚀 Features

* **Secure File Handling:** Multipart file upload and download capabilities.
* **Content Management:** Full CRUD-style listing and retrieval of course materials.
* **Security First:** Stateless authentication using **JWT (JSON Web Tokens)**.
* **Containerized:** Fully Dockerized backend with `docker-compose` for instant deployment.
* **Architecture:** Adheres to **Clean MVC** principles for high maintainability.

## 🛠 Tech Stack
```
| Layer | Technology |
| :--- | :--- |
| **Backend** | Java 21, Spring Boot 3.x, Spring Data JPA |
| **Security** | Spring Security, JWT |
| **Frontend** | React, Axios |
| **Database** | PostgreSQL |
| **DevOps** | Docker, Docker Compose |
```
## 📂 Project Structure

```
src/main/java/com/example/demo
├── controller    # REST Endpoints
├── service       # Business Logic & File Processing
├── repository    # Database Abstraction (JPA)
├── entity        # Database Models
├── security      # JWT & Filter Configurations
└── storage       # File System Logic
```

🚦 Getting StartedOption 

1: Run with Docker (Recommended)Ensure you have Docker Desktop installed and running.
Build and Start:Bashdocker ```compose up --build```
Access the API:The backend will be available at ```http://localhost:8080/api/course```.Stop Services:Bashdocker ```compose down```

Option 2: 
Local DevelopmentDatabase 
Setup: Create a PostgreSQL database named ```course_db```.
Configure: Update ```src/main/resources/application.properties```:
Properties
```spring.datasource.url=jdbc:postgresql://localhost:5432/course_db```
```spring.datasource.username=postgres```
```spring.datasource.password=admin```
Build & Run: Using Maven Wrapper
```./mvnw clean package```
```java -jar target/demo-0.0.1-SNAPSHOT.jar```

📑 API Endpoints
Authentication 
```Method  Endpoint        Access
    POST   /api/auth/**    Public
    ANY    /api/course/**  Requires JWT```
```
Course Management
Method   Endpoint                  Description
POST     /api/course/upload        Upload file (Multipart)
GET      /api/course               Fetch all contents
GET      /api/course/{id}          Fetch metadata by ID
GET      /api/course/download/{id} Download file

🔧 Troubleshooting
Port Conflict: If 8080 or 5432 is taken, run docker ps to find and stop conflicting containers.
Connection Refused: Ensure the PostgreSQL container is fully initialized before the Spring Boot app attempts to connect. (Check logs with docker compose logs -f).🔮 Future Roadmap[ ] Integrate Swagger/OpenAPI 3 for interactive documentation.[ ] Migration from local storage to AWS S3.[ ] Implement automated CI/CD pipelines via GitHub Actions.[ ] Advanced file type validation and UI polish.Author: Samitha Athurupana

Would you like me to help you create a `docker-compose.yml` file to match this configur
