# 🏨 Backend Service – Microservices Architecture with Spring Boot & Keycloak

## 📌 Overview
This backend application is built using **Spring Boot** and follows a **microservices architecture**.  
It uses **Keycloak** for authentication & authorization, **MySQL** for persistent storage, and an **API Gateway** to route requests securely to different services.  
The system is designed to be scalable, secure, and easy to extend.

---

## 🛠️ Tech Stack
- **Java 17**  
- **Spring Boot** (Web, Data JPA, Cloud)  
- **Spring Cloud Gateway** (API Gateway)  
- **Spring Security** (OAuth2 with Keycloak)  
- **Keycloak** (Identity & Access Management)  
- **MySQL** (Relational Database)  
- **Hibernate** (ORM)  
- **Docker** (Optional for containerization)

---

## 🏗️ Architecture

👤
User
→
🌐
CDN
→
🚪
API Gateway
→
🔐
Auth Service
→
🏨
Microservices
→
🗄️
Databases


### Components:
1. **API Gateway**
   - Routes incoming requests to the correct microservice.
   - Handles authentication and authorization checks.

2. **Auth Service (Keycloak)**
   - Manages user authentication via OAuth2 / OpenID Connect.
   - Issues JWT tokens for secure communication.

3. **Microservices**
   - Independently deployable services (e.g., Order Service, User Service, Product Service).
   - Each has its own database schema (**Database per Service** pattern).

4. **Databases**
   - **MySQL** used for persistent data storage.
   - Separate schemas for each microservice.

---

## 🔐 Authentication & Authorization
- Integrated with **Keycloak** for secure user management.
- Uses **JWT** for stateless authentication.
- Supports **Role-Based Access Control (RBAC)**.

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3+
- MySQL installed & running
- Keycloak server running (can use Docker)

### Installation
1. **Clone the repository**
   ```bash
   git clone https://github.com/DanukaKoshitha/Hotel-Management-System.git
