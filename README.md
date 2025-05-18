# User Management Microservice

## 🎯 About
This is a microservice responsible for user management as part of a distributed architecture. It handles all operations related to users, such as registration, authentication, and profile management.

## 🚀 Technologies Used

### Backend
- **Java 17**
- **Spring Framework**
  - Spring Boot
  - Spring Security (with password encryption support)
  - Spring Cloud (Microservices support)
  - Spring Validation (Data and payload validation)
  - Spring Data JPA

### Architecture
- **Clean Architecture**
- **Hexagonal Architecture (Ports and Adapters)**
  - Clear separation between domain, application, and infrastructure layers

- **Microservices Architecture**
  - Independent and decoupled services
  - Horizontal scalability
  - Resilience and fault tolerance


### Database
- **MySQL**
  - Relational database
  - ACID transaction support
  - Indexing and query optimization

### Database Migration
- **Flyway**
  - Database version control
  - Automated migrations
  - Change history tracking

### Containerization
- **Docker**
  - Application containerization
  - Docker Compose for orchestration
  - Multi-stage builds
  - Persistent volumes

### Messaging
- **RabbitMQ**
  - Asynchronous communication between microservices
  - Publisher/Subscriber messaging pattern
  - Queue and event management

### Validation & Security
- **Bean Validation**
  - Custom validations
  - Validation annotations (`@NotNull`, `@Size`, etc.)
  - Validation groups
- **Spring Security**
  - Authentication and Authorization
  - JWT (JSON Web Tokens)
  - Password encryption

### Infrastructure
- **Docker Compose**

---

## 🧱 Project Structure
