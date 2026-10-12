# 🛕 Moksha Marg — Religious Tourism Platform (Backend)

A robust, enterprise-grade **Spring Boot backend** for the Moksha Marg multi-vendor travel and booking platform. It handles secure user authentication, role-based access control, complex booking workflows, data persistence, and high-performance API delivery.

---

## ✨ Core Backend Features

- 🔐 **Spring Security & RBAC** — Granular role-based access control supporting multiple user personas (Admin, Guide, Restaurant Owner, User)
- 🗄️ **Spring Data JPA / Hibernate** — Robust relational database modeling, entity mapping, and optimized query repositories
- ⚡ **Redis Caching & Pagination** — Server-side caching layers and pagination for high-volume datasets, minimizing database load and reducing API latency
- 💳 **Payment & Third-Party Integration** — Secure webhook handling and REST endpoints for payment gateway integration (Razorpay)
- 🚀 **RESTful Architecture** — Clean, scalable REST APIs built with standardized exception handling and DTO patterns

---

## 🏗️ Project Architecture
<img width="976" height="540" alt="image" src="https://github.com/user-attachments/assets/b5873aad-517c-4a74-841b-643122e4b366" />

---

## 🛠️ Tech Stack

| Component | Technology |
|---|---|
| Language | Java 17+ |
| Framework | Spring Boot |
| Security | Spring Security, JWT |
| Data Access | Spring Data JPA, Hibernate |
| Database | MySQL / PostgreSQL |
| Caching | Redis |
| Build Tool | Maven |
| Deployment | AWS (EC2 / Elastic Beanstalk), Docker |

---

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK 17+)
- Maven
- MySQL Server
- Redis Server (optional, for caching)

### Installation & Setup

```bash
# Clone the repository
git clone [https://github.com/nareshgupta99/mokshamarg_backend.git](https://github.com/nareshgupta99/mokshamarg_backend.git)

# Navigate to project directory
cd mokshamarg_backend

# Configure your database credentials in src/main/resources/application.yml
spring.datasource.url=jdbc:mysql://localhost:3306/moksha_marg
spring.datasource.username=your_username
spring.datasource.password=your_password

# Build the project using Maven
mvn clean install

# Run the application
mvn spring-boot:run
