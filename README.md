#  FlowOps — Task & Project Management System

> A full-stack task and project management platform built with **Java, Spring Boot, React, TypeScript, JWT, RBAC, JPA/Hibernate, and Docker**.

FlowOps is a real-world inspired application designed to demonstrate how a maintainable and secure enterprise-style system can be developed using modern backend and frontend technologies.

---

## ✨ Key Features

### 🔐 Authentication & Security

* JWT-based authentication
* Spring Security
* Role-Based Access Control (RBAC)
* Protected REST APIs
* User and role management

### 👥 User Management

* User CRUD operations
* Role assignment
* Department management
* Designation management

### 📁 Project Management

* Create and manage projects
* Project member management
* Assign users to projects
* Project-based task organization

### ✅ Task Management

* Create, update, and delete tasks
* Task assignment
* Task status and priority
* Due dates
* Parent/child task relationships
* Task comments
* Task attachments
* Task history

### ⚡ API & Data Management

* RESTful APIs
* DTO-based API contracts
* Entity/DTO separation
* Pagination
* JPA/Hibernate
* Structured exception handling
* Request validation

---

## 🏗️ Architecture

FlowOps follows a layered backend architecture with a separate React frontend.

```text
                    ┌───────────────────┐
                    │    React Client   │
                    │ TypeScript + MUI  │
                    └─────────┬─────────┘
                              │
                         REST / JSON
                              │
                              ▼
                    ┌───────────────────┐
                    │   Spring Boot API │
                    └─────────┬─────────┘
                              │
              ┌───────────────┼───────────────┐
              ▼               ▼               ▼
        Controllers       Services        Security
              │               │               │
              └───────┬───────┘               │
                      ▼                       │
                 Repositories                │
                      │                       │
                      └──────────┬────────────┘
                                 ▼
                         MySQL / PostgreSQL
```

### Backend flow

```text
Controller
    ↓
Service Interface
    ↓
Service Implementation
    ↓
Repository
    ↓
Database
```

DTOs and converters are used to keep API contracts separated from persistence entities.

---

## 🛡️ Authentication Flow

```text
User Login
    ↓
Spring Security
    ↓
Authentication
    ↓
JWT Token
    ↓
Client
    ↓
Bearer Token
    ↓
JWT Validation
    ↓
Protected REST API
```

Example:

```http
Authorization: Bearer <access-token>
```

---

## 🗄️ Domain Model

Core entities include:

```text
User
 ├── Roles
 ├── Department
 └── Designation

Project
 ├── Project Members
 └── Tasks
      ├── Comments
      ├── Attachments
      ├── History
      └── Child Tasks
```

### Main entities

| Entity          | Responsibility            |
| --------------- | ------------------------- |
| User            | Application users         |
| Role            | Authorization             |
| Department      | Organizational structure  |
| Designation     | User designation          |
| Project         | Project information       |
| Project Member  | User-project relationship |
| Task            | Project work item         |
| Task Comment    | Task collaboration        |
| Task Attachment | Task resources            |
| Task History    | Task audit trail          |

---

## 🛠️ Technology Stack

### Backend

* **Java 21**
* **Spring Boot**
* **Spring MVC**
* **Spring Security**
* **JWT**
* **Spring Data JPA**
* **Hibernate**
* **Maven**

### Frontend

* **React**
* **Vite**
* **Redux Toolkit**
* **RTK Query**
* **Material UI**
* **Tailwind CSS**
* **Formik**
* **Yup**

### Database & DevOps

* **MySQL**
* **PostgreSQL**
* **Docker**
* **Git / GitHub**

---

## 🌐 API Overview

### Authentication

```http
POST /auth/login
```

### Users

```http
POST   /users/save
GET    /users
GET    /users/{id}
DELETE /users/{id}
```

### Roles

```http
POST /role/save
```

### Projects

```http
POST   /projects
GET    /projects
GET    /projects/{id}
PUT    /projects/{id}
DELETE /projects/{id}
```

### Tasks

```http
POST   /tasks
GET    /tasks
GET    /tasks/{id}
PUT    /tasks/{id}
DELETE /tasks/{id}
```

Additional APIs handle project members, comments, attachments, task history, departments, and designations.

---

## 💻 Frontend Architecture

The frontend uses **Redux Toolkit and RTK Query** for application and server-state management.

```text
React Components
       ↓
Redux Toolkit
       ↓
RTK Query
       ↓
REST API
       ↓
Spring Boot
```

RTK Query provides standardized handling for API requests, caching, loading states, errors, and mutations.

---

## 🐳 Docker

The backend can be packaged and executed as a Docker container.

```bash
mvn clean package

docker build -t flowops:v1 .

docker run -p 8080:8080 flowops:v1
```

Application flow:

```text
Source Code
    ↓
Maven Build
    ↓
Spring Boot JAR
    ↓
Docker Image
    ↓
Docker Container
```

---

## 🚀 Getting Started

### Prerequisites

* Java 21+
* Maven
* Node.js
* npm
* MySQL or PostgreSQL
* Docker (optional)
* Git

### Clone

```bash
git clone https://github.com/Azizul-arif/FlowOps.git
cd flowops
```

### Backend

```bash
cd flowOps-service
mvn clean install
mvn spring-boot:run
```

### Frontend

```bash
cd flowOps-frontend
npm install
npm run dev
```

Configure database credentials and other environment-specific settings in the Spring Boot configuration.

---

## 🧩 Engineering Practices

FlowOps demonstrates practical experience with:

* Layered architecture
* SOLID principles
* Dependency Injection
* REST API design
* DTO pattern
* Converter pattern
* Repository pattern
* JPA/Hibernate
* Spring Security
* JWT authentication
* RBAC
* Pagination
* Exception handling
* React component architecture
* Redux Toolkit
* RTK Query
* Docker containerization

---

## 🔮 Roadmap

The project is actively evolving toward a more production-oriented architecture.

* [ ] Refresh Token
* [ ] Idle Session Timeout
* [ ] Automated Testing
* [ ] Redis caching
* [ ] Kafka event processing
* [ ] Real-time notifications
* [ ] Email notification service
* [ ] WebSocket
* [ ] Microservice architecture
* [ ] API Gateway
* [ ] Jenkins CI/CD
* [ ] Docker Compose
* [ ] Cloud deployment
* [ ] Centralized logging and monitoring

---

## 👨‍💻 About

**Azizul Arif**

Software Engineer focused on **Java, Spring Boot, REST APIs, React, and scalable application development**.

### Core Skills

`Java` `Spring Boot` `Spring Security` `REST API` `JPA/Hibernate` `React` `TypeScript` `Redux Toolkit` `RTK Query` `Docker` `SQL` `Microservices`

---

## 📌 Project Status

🟢 **Active Development**

FlowOps is continuously being enhanced with additional security, backend, frontend, distributed-system, and DevOps capabilities.

---

⭐ **If you find the project interesting, feel free to explore the source code and follow the project as it evolves.**
