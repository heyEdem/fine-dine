# Food Delivery Service

## Overview
This project is a **microservices-based** food delivery application built to help mid-level Java developers learn microservices concepts using **Spring Boot**.

It includes services for:
- User management
- Restaurant and menu management
- Order processing
- Delivery tracking
- Payment processing
- Admin functionalities

The system uses **REST APIs**, **Kafka** for event-driven communication, and **WebSockets** for real-time delivery tracking.

---

## Microservices
- **User Service** – Handles user registration, authentication (JWT), and profile management.
- **Restaurant Service** – Manages restaurant profiles, menus, and order notifications.
- **Order Service** – Processes orders and tracks status.
- **Delivery Service** – Assigns orders to drivers and provides real-time tracking.
- **Payment Service** – Simulates payment processing.
- **Admin Service** – Manages restaurant/driver approvals and reports.

---

## Prerequisites
- **Java 17** – For Spring Boot applications.
- **Maven 3.8+** – Dependency management and building.
- **Docker** – For containerizing services and running PostgreSQL, Redis, and Kafka.
- **Postman** – For API testing.
- **Git** – Version control.
- **IDE** – IntelliJ IDEA or Eclipse recommended.
- **Kafka** – Requires Zookeeper and Kafka brokers (included in Docker Compose).

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
cd food-delivery-service
```

### 2. Verify Prerequisites
- Ensure Java 17 and Maven are installed:
  ```bash
  java -version
  mvn -version
  ```
- Confirm Docker and Docker Compose are installed:
  ```bash
  docker --version
  docker-compose --version
  ```

### 3. Configure Databases and Kafka
- Use the provided `docker-compose.yml` to set up PostgreSQL, Redis, and Kafka:
  ```bash
  docker-compose up -d
  ```
- Verify containers are running:
  ```bash
  docker ps
  ```
- Note: `docker-compose.yml` includes configurations for:
    - PostgreSQL (port: 5432)
    - Redis (port: 6379)
    - Kafka with Zookeeper (ports: 2181, 9092)

### 4. Build the Project
- Build all microservices using Maven:
  ```bash
  mvn clean install
  ```

### 5. Run Microservices
- Start each service individually via Maven (from their respective directories):
  ```bash
  cd user-service
  mvn spring-boot:run
  ```
  Repeat for `restaurant-service`, `order-service`, `delivery-service`, `payment-service`, and `admin-service`.
- Alternatively, run all services using Docker Compose:
  ```bash
  cd ..
  docker-compose up --build
  ```

### 6. Verify Setup
- Check health endpoints for each service (e.g., `http://localhost:8081/health` for User Service).
- Use Postman to test health endpoints (expect 200 OK).
- Access Swagger UI for API documentation (e.g., `http://localhost:8081/swagger-ui.html`).

---

## Project Structure
```
food-delivery-service/
├── user-service/         # User management (port: 8081)
├── restaurant-service/   # Restaurant and menu management (port: 8082)
├── order-service/        # Order processing (port: 8083)
├── delivery-service/     # Delivery assignment and tracking (port: 8084)
├── payment-service/      # Payment processing (port: 8085)
├── admin-service/        # Admin management (port: 8086)
├── docker-compose.yml    # Docker configuration for databases and Kafka
├── README.md            # This file
```

---

## Running the Application
1. **Start Services**: Ensure Docker Compose is running, then start all microservices (ports 8081–8086).
2. **Test APIs**:
    - Register a user: `POST http://localhost:8081/register`
    - Browse restaurants: `GET http://localhost:8082/restaurants`
    - Place an order: `POST http://localhost:8083/orders`
    - Check Swagger for full API details (e.g., `http://localhost:8081/swagger-ui.html`).
3. **Test Kafka Events**:
    - Place an order and verify notifications in Restaurant and Delivery Services.
    - Use a Kafka client (e.g., Confluent CLI) to inspect topics.
4. **Test WebSockets**:
    - Connect to `ws://localhost:8084/ws/delivery/{id}` to receive delivery updates.
5. **Monitor Services**:
    - Check health and metrics: `http://localhost:8081/actuator/health`, `/actuator/metrics`.

---

## Development Guidelines
- **Jira Tickets**: Follow tickets (FDS-2 to FDS-17) for modular development (see Jira artifact for details). Start with:
    - FDS-2: Project setup
    - FDS-10: Centralized logging for debugging
    - FDS-3, FDS-4, FDS-9: Core functionality and integration
- **Socratic Learning**:
    - For each service, ask: “What’s its single responsibility? How does it handle failures?”
    - Experiment with REST vs. Kafka to understand synchronous vs. asynchronous communication.
- **Coding Practices**:
    - Use Spring Boot best practices (e.g., dependency injection, layered architecture).
    - Maintain clear service boundaries to avoid tight coupling.
- **Testing**:
    - Write unit tests with JUnit and Mockito (aim for 80% coverage).
    - Use Postman or RestAssured for API and end-to-end testing.
- **Logging**: Enable SLF4J/Logback (FDS-10) for structured logging.
- **Monitoring**: Use Spring Actuator (FDS-17) for health checks and metrics.
- **Resources**:
    - [Spring Boot Guides](https://spring.io/guides)
    - [Microservices Patterns](https://github.com/search?q=spring+boot+microservices)
    - [Kafka Documentation](https://www.confluent.io/)
    - [Swagger/OpenAPI](https://swagger.io/)

---

## Troubleshooting
- **Service Fails to Start**: Check Docker logs (`docker-compose logs`) and ensure ports 8081–8086, 5432, 6379, 2181, and 9092 are free.
- **Kafka Connection Issues**: Verify Zookeeper and Kafka containers are running (`docker ps`). Check `docker-compose.yml` for correct host/port settings.
- **API Errors**: Review logs in `logs/` directory or ELK stack (if configured via FDS-10). Ensure JWT tokens are included in requests.
- **Database Issues**: Confirm PostgreSQL/Redis are accessible (e.g., `psql -h localhost -U postgres`).
- **WebSocket Issues**: Test connection with a WebSocket client (e.g., Postman) and verify delivery service is running.

