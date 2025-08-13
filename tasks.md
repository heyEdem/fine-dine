# Food Delivery Service - Expanded Jira Tickets

## Epic: Food Delivery Service Development
**Epic ID**: FDS-1  
**Description**: Build a microservices-based food delivery application with user, restaurant, order, delivery, payment, and admin functionalities, focusing on learning service decomposition, inter-service communication, and deployment.

### Story: Setup Project Infrastructure
**Ticket ID**: FDS-2  
**Title**: Initialize Project and Microservices Skeleton  
**Description**: Set up the multi-module Maven project, initialize Spring Boot applications for all microservices, and configure Docker for local development.  
**Sub-Tasks**:
1. Create a multi-module Maven project with separate modules for User, Restaurant, Order, Delivery, Payment, and Admin services.
2. Initialize Spring Boot applications with basic configurations (e.g., application.properties, pom.xml).
3. Create Dockerfiles for each service, ensuring Java 17 compatibility.
4. Set up Docker Compose to run PostgreSQL, Redis, and Kafka containers.
5. Implement a `/health` endpoint for each service to verify startup.
6. Document setup steps in a README.md file.
**Acceptance Criteria**:
- Project builds successfully with `mvn clean install`.
- Docker Compose starts all services, databases, and Kafka.
- Each service responds with 200 OK to `/health` endpoint.
- README includes setup and run instructions.
**Story Points**: 5  
**Priority**: High  

### Story: Configure Centralized Logging
**Ticket ID**: FDS-10  
**Title**: Implement Centralized Logging for Microservices  
**Description**: Set up logging to track service interactions and errors, using a centralized logging solution (e.g., SLF4J with Logback).  
**Sub-Tasks**:
1. Add SLF4J and Logback dependencies to all services.
2. Configure log levels (INFO, ERROR) in application.properties.
3. Implement structured logging for key actions (e.g., API calls, errors).
4. Set up a basic log aggregator (e.g., file-based or ELK stack if time permits).
5. Write a test to verify log output for a sample API call.
**Acceptance Criteria**:
- All services log requests and errors in a consistent format.
- Logs are written to a centralized location (e.g., file or ELK).
- Test confirms logs are generated for a sample API call.
**Story Points**: 3  
**Priority**: Medium  

### Story: User Service - Authentication
**Ticket ID**: FDS-3  
**Title**: Implement User Registration and Authentication  
**Description**: Build user registration and login with JWT-based authentication for secure access.  
**Sub-Tasks**:
1. Create REST endpoints: `POST /register`, `POST /login`, `GET /profile`.
2. Configure Spring Security with JWT for authentication.
3. Design PostgreSQL schema: users (id, email, hashed_password, name, address, phone).
4. Implement password hashing (e.g., BCrypt).
5. Add input validation for registration (e.g., email format, password strength).
6. Write unit tests for registration, login, and JWT validation.
7. Document API endpoints in Swagger/OpenAPI.
**Acceptance Criteria**:
- Users can register with valid email/password and log in to receive a JWT.
- `/profile` returns user details for authenticated users.
- Invalid inputs (e.g., weak password) return appropriate error messages.
- Unit tests achieve 80% coverage.
- Swagger UI lists all endpoints.
**Story Points**: 8  
**Priority**: High  

### Story: User Service - Profile Management
**Ticket ID**: FDS-11  
**Title**: Implement User Profile Management  
**Description**: Allow users to view and update their profile details (name, address, phone).  
**Sub-Tasks**:
1. Create REST endpoints: `PUT /profile`, `GET /profile`.
2. Update PostgreSQL schema to support profile fields.
3. Implement validation for profile updates (e.g., phone format).
4. Secure endpoints with JWT authentication.
5. Write unit tests for profile CRUD operations.
6. Update Swagger documentation with profile endpoints.
**Acceptance Criteria**:
- Authenticated users can update and retrieve profile details.
- Invalid updates (e.g., invalid phone) return 400 Bad Request.
- Unit tests achieve 80% coverage.
- Swagger reflects updated endpoints.
**Story Points**: 5  
**Priority**: Medium  

### Story: Restaurant Service - Core Functionality
**Ticket ID**: FDS-4  
**Title**: Implement Restaurant and Menu Management  
**Description**: Enable restaurants to register and manage menus, and users to browse restaurants and menus.  
**Sub-Tasks**:
1. Create REST endpoints: `POST /restaurants`, `GET /restaurants`, `GET /restaurants/{id}/menu`, `POST /restaurants/{id}/menu`, `PUT /restaurants/{id}/menu/{menuId}`, `DELETE /restaurants/{id}/menu/{menuId}`.
2. Design PostgreSQL schema: restaurants (id, name, address, status), menus (id, restaurant_id, name, price, description).
3. Implement CRUD operations for restaurants and menus.
4. Add validation for menu items (e.g., positive price).
5. Secure restaurant management endpoints with JWT (restaurant role).
6. Write unit tests for restaurant and menu operations.
7. Document endpoints in Swagger.
**Acceptance Criteria**:
- Restaurants can create/update/delete menu items.
- Users can browse restaurants and menus via public GET endpoints.
- Invalid menu data returns appropriate errors.
- Unit tests achieve 80% coverage.
- Swagger lists all endpoints.
**Story Points**: 8  
**Priority**: High  

### Story: Restaurant Service - Order Notifications
**Ticket ID**: FDS-12  
**Title**: Implement Restaurant Order Notifications  
**Description**: Enable restaurants to receive notifications when new orders are placed, using Kafka.  
**Sub-Tasks**:
1. Configure Kafka consumer in Restaurant Service to listen for “order placed” events.
2. Store order notifications in PostgreSQL (notifications table: id, restaurant_id, order_id, timestamp).
3. Create REST endpoint: `GET /restaurants/{id}/notifications`.
4. Secure endpoint with JWT (restaurant role).
5. Write unit tests for notification handling.
6. Update Swagger documentation.
**Acceptance Criteria**:
- Restaurants receive and store order notifications via Kafka.
- Authenticated restaurants can retrieve notifications.
- Unit tests achieve 80% coverage.
**Story Points**: 5  
**Priority**: Medium  

### Story: Order Service - Core Functionality
**Ticket ID**: FDS-5  
**Title**: Implement Order Placement and Status Tracking  
**Description**: Allow users to place orders and track status, publishing events to Kafka.  
**Sub-Tasks**:
1. Create REST endpoints: `POST /orders`, `GET /orders/{id}`, `GET /orders` (user-specific).
2. Design PostgreSQL schema: orders (id, user_id, restaurant_id, items, status, total_price, created_at).
3. Implement order creation with validation (e.g., valid menu items).
4. Configure Kafka producer to publish “order placed” events.
5. Implement status updates (placed, preparing, dispatched, delivered).
6. Secure endpoints with JWT (user role).
7. Write unit tests for order creation and status updates.
8. Document endpoints in Swagger.
**Acceptance Criteria**:
- Users can place orders with valid menu items.
- Order status updates are published to Kafka.
- Users can retrieve order history.
- Unit tests achieve 80% coverage.
- Swagger lists all endpoints.
**Story Points**: 8  
**Priority**: Medium  

### Story: Order Service - Error Handling
**Ticket ID**: FDS-13  
**Title**: Implement Order Service Error Handling  
**Description**: Add robust error handling for invalid orders and edge cases.  
**Sub-Tasks**:
1. Implement global exception handling in Order Service (e.g., @ControllerAdvice).
2. Handle cases: invalid menu items, unavailable restaurant, duplicate orders.
3. Return meaningful error messages (e.g., 400 Bad Request with details).
4. Log errors using SLF4J/Logback.
5. Write unit tests for error scenarios.
**Acceptance Criteria**:
- Invalid orders return specific error messages.
- Errors are logged appropriately.
- Unit tests cover error cases with 80% coverage.
**Story Points**: 3  
**Priority**: Medium  

### Story: Delivery Service - Driver Management
**Ticket ID**: FDS-14  
**Title**: Implement Driver Registration and Availability  
**Description**: Allow drivers to register and update their availability status.  
**Sub-Tasks**:
1. Create REST endpoints: `POST /drivers`, `PUT /drivers/{id}/availability`, `GET /drivers`.
2. Design PostgreSQL schema: drivers (id, name, phone, availability, status).
3. Implement driver registration and availability updates.
4. Secure endpoints with JWT (driver role).
5. Write unit tests for driver operations.
6. Document endpoints in Swagger.
**Acceptance Criteria**:
- Drivers can register and update availability.
- Admins can view driver list.
- Unit tests achieve 80% coverage.
- Swagger lists all endpoints.
**Story Points**: 5  
**Priority**: Medium  

### Story: Delivery Service - Order Assignment and Tracking
**Ticket ID**: FDS-6  
**Title**: Implement Delivery Assignment and Real-Time Tracking  
**Description**: Assign orders to drivers and provide real-time tracking via WebSockets.  
**Sub-Tasks**:
1. Create REST endpoints: `POST /deliveries`, `GET /deliveries/{id}`.
2. Design PostgreSQL schema: deliveries (id, order_id, driver_id, status, location).
3. Configure Kafka consumer to listen for “order placed” events.
4. Implement logic to assign orders to available drivers (e.g., simple round-robin).
5. Set up WebSocket endpoint: `/ws/delivery/{id}` for real-time updates.
6. Simulate location updates (e.g., mock coordinates).
7. Secure endpoints with JWT (user, driver roles).
8. Write unit tests for assignment and tracking.
9. Document endpoints in Swagger.
**Acceptance Criteria**:
- Orders are assigned to available drivers via Kafka events.
- Users and drivers receive real-time updates via WebSockets.
- Unit tests achieve 80% coverage.
- Swagger lists all endpoints.
**Story Points**: 10  
**Priority**: Medium  

### Story: Payment Service - Core Functionality
**Ticket ID**: FDS-7  
**Title**: Implement Simulated Payment Processing  
**Description**: Simulate payment processing for orders with a mock API, triggered by Kafka events.  
**Sub-Tasks**:
1. Create REST endpoint: `POST /payments`.
2. Design PostgreSQL schema: payments (id, order_id, status, amount, created_at).
3. Configure Kafka consumer to listen for “order placed” events.
4. Implement mock payment API (e.g., 90% success, 10% failure).
5. Publish payment status events to Kafka.
6. Secure endpoint with JWT (internal service call).
7. Write unit tests for payment processing.
8. Document endpoint in Swagger.
**Acceptance Criteria**:
- Payments are processed after order placement via Kafka.
- Payment status is recorded and retrievable.
- Unit tests achieve 80% coverage.
- Swagger lists endpoint.
**Story Points**: 5  
**Priority**: Medium  

### Story: Payment Service - Error Handling
**Ticket ID**: FDS-15  
**Title**: Implement Payment Service Error Handling  
**Description**: Handle payment failures and edge cases with proper error responses.  
**Sub-Tasks**:
1. Implement global exception handling in Payment Service.
2. Handle cases: failed payments, invalid order IDs.
3. Return meaningful error messages and log errors.
4. Notify Order Service of payment failures via Kafka.
5. Write unit tests for error scenarios.
**Acceptance Criteria**:
- Failed payments return specific error messages.
- Errors are logged and communicated to Order Service.
- Unit tests cover error cases with 80% coverage.
**Story Points**: 3  
**Priority**: Medium  

### Story: Admin Service - Core Functionality
**Ticket ID**: FDS-8  
**Title**: Implement Admin Management Features  
**Description**: Build admin features to manage restaurant and driver registrations.  
**Sub-Tasks**:
1. Create REST endpoints: `GET /admin/restaurants`, `PUT /admin/restaurants/{id}/approve`, `PUT /admin/restaurants/{id}/reject`, `GET /admin/drivers`, `PUT /admin/drivers/{id}/approve`, `PUT /admin/drivers/{id}/reject`.
2. Design PostgreSQL schema: admin_actions (id, entity_type, entity_id, action, timestamp).
3. Implement approval/rejection logic for restaurants and drivers.
4. Secure endpoints with JWT (admin role).
5. Write unit tests for admin operations.
6. Document endpoints in Swagger.
**Acceptance Criteria**:
- Admins can approve/reject restaurant and driver registrations.
- Admin endpoints are secured with JWT.
- Unit tests achieve 80% coverage.
- Swagger lists all endpoints.
**Story Points**: 5  
**Priority**: Low  

### Story: Admin Service - Reporting
**Ticket ID**: FDS-16  
**Title**: Implement Admin Sales and Delivery Reports  
**Description**: Provide admins with reports on sales and delivery performance.  
**Sub-Tasks**:
1. Create REST endpoints: `GET /admin/reports/sales`, `GET /admin/reports/deliveries`.
2. Implement queries to aggregate order and delivery data from PostgreSQL.
3. Secure endpoints with JWT (admin role).
4. Write unit tests for report generation.
5. Document endpoints in Swagger.
**Acceptance Criteria**:
- Admins can retrieve sales and delivery reports.
- Reports include total orders, revenue, and delivery times.
- Unit tests achieve 80% coverage.
- Swagger lists endpoints.
**Story Points**: 5  
**Priority**: Low  

### Story: Integration and Testing
**Ticket ID**: FDS-9  
**Title**: Integrate Microservices and Perform End-to-End Testing  
**Description**: Ensure all microservices communicate correctly and test the full system.  
**Sub-Tasks**:
1. Configure service discovery with Eureka or hardcode service URLs.
2. Test REST API calls between services (e.g., Order Service to Restaurant Service).
3. Test Kafka event flows (e.g., order placement to payment and delivery).
4. Write end-to-end tests using Postman or RestAssured for core flows (e.g., register, place order, track delivery).
5. Deploy all services using Docker Compose.
6. Document integration setup and test cases in README.
**Acceptance Criteria**:
- Services communicate correctly via REST and Kafka.
- End-to-end tests pass for core user flows (e.g., order placement to delivery).
- System runs locally with Docker Compose.
- README includes integration and testing instructions.
**Story Points**: 8  
**Priority**: High  

### Story: Monitoring and Metrics
**Ticket ID**: FDS-17  
**Title**: Implement Basic Monitoring for Microservices  
**Description**: Add monitoring to track service health and performance using Spring Actuator.  
**Sub-Tasks**:
1. Add Spring Boot Actuator to all services.
2. Expose endpoints: `/actuator/health`, `/actuator/metrics`.
3. Configure metrics for API response times and error rates.
4. Test actuator endpoints with Postman.
5. Document monitoring setup in README.
**Acceptance Criteria**:
- All services expose health and metrics endpoints.
- Metrics are accessible and accurate (e.g., response times).
- README includes monitoring instructions.
**Story Points**: 3  
**Priority**: Low  

## Development Guidelines
- **Socratic Learning**: For each ticket, ask: “How does this service interact with others? What happens if it fails?” Experiment with REST vs. Kafka to learn trade-offs.
- **Modularity**: Keep services focused. Use Spring Boot best practices (e.g., dependency injection, layered architecture).
- **Testing**: Write unit tests (JUnit, Mockito) and aim for 80% coverage. Use Postman for API testing.
- **Resources**: Refer to Spring Boot guides (spring.io) and open-source projects (e.g., “spring-boot-microservices” on GitHub).
- **Next Steps**: Start with FDS-2 (project setup), then proceed with high-priority tickets (FDS-3, FDS-4, FDS-9). Use FDS-10 (logging) early to aid debugging.