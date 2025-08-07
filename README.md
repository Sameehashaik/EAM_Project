# 📦 EAM Microservices Project

This project demonstrates a microservices-based stock trading platform using Spring Boot and Eureka Discovery. It includes 3 core services (Order, Market, and User), all built with Java 21 and MongoDB, with REST-based communication between services.

---

## Team Members

- Sameeha Shaik (N01649346)
- Kalwala Siddhartha Reddy (N01649317)
- Manohar Reddy (N01661510)

---

## ⚙️ Tech Stack

- Java 21
- Spring Boot 3
- Eureka Discovery Server
- Spring Web (REST API)
- Spring Data MongoDB
- Lombok
- RestTemplate (for inter-service calls)
- MongoDB
- Postman (for testing)

---

## 🧱 Microservices Overview

| Service        | Port | Description                             |
| -------------- | ---- | --------------------------------------- |
| Eureka Server  | 8761 | Service registry and discovery          |
| Order Service  | 9091 | Places stock orders and calls Market    |
| Market Service | 9092 | Accepts stock orders from Order Service |
| User Service   | 9093 | Registers and fetches user information  |

---

## 🔁 System Flow

1. User is registered via User Service.
2. User places an order via Order Service.
3. Order Service saves the order and calls Market Service.
4. Market Service stores market order details.
5. All services are registered with Eureka for discovery.

---

## 🚀 How to Run the Application

1. Start **MongoDB** locally (port `27017`)
2. Run **Eureka Server** (port `8761`)
3. Run the three services:
   - `Order Service` (port `9091`)
   - `Market Service` (port `9092`)
   - `User Service` (port `9093`)
4. Open [http://localhost:8761](http://localhost:8761) to view registered services.

---

## 📬 API Endpoints

### 🛍️ Order Service (http://localhost:9091)

#### ➕ Place an Order

**POST** `/order/place`  
**Body:**

```json
{
  "stockSymbol": "NVDA",
  "quantity": 20,
  "price": 145.4,
  "username": "test"
}
```

> Also triggers a REST call to Market Service to create a stock entry.

#### 📄 View All Orders

**GET** `/order`

#### 🔍 Get Orders by Username

**GET** `/order/user/{username}`

---

### 📈 Market Service ([http://localhost:9092](http://localhost:9092))

#### ➕ Place a Market Stock Entry

**POST** `/market/place`
**Body:**

```json
{
  "stockSymbol": "AAPL",
  "quantity": 50,
  "price": 184.35
}
```

#### 📄 View All Market Entries

**GET** `/market`

---

### 👤 User Service ([http://localhost:9093](http://localhost:9093))

#### ➕ Register a User

**POST** `/users/register`
**Body:**

```json
{
  "username": "admin",
  "password": "admin123",
  "typeOfUser": "admin"
}
```

#### 🔍 Fetch User by ID

**GET** `/users/{id}`

#### 📄 View All Users

**GET** `/users`

---

## ✅ Project Highlights

- ✅ Spring Cloud Eureka for service discovery
- ✅ 3 decoupled microservices communicating via REST
- ✅ MongoDB for persistent storage
- ✅ Jackson used for JSON serialization/deserialization
- ✅ RESTTemplate used for internal service calls
- ✅ Full CRUD support and validation in all services
- ✅ Tested with Postman

---

## 🎥 Demo Video

📹 *https://youtu.be/c7Y6e2nqjvs*

---

## 📁 Project Structure

```
EAM_Project/
├── ProjectEurekaServer/
├── ProjectOrderService/
├── ProjectMarketService/
└── ProjectUserService/
```

---

## 👨‍🏫 Instructor Requirements Covered

- [x] Eureka Server using `@EnableEurekaServer`
- [x] 3 Microservices with `@EnableDiscoveryClient`
- [x] One key operation per service (plus additional CRUD)
- [x] MongoDB integration in all services
- [x] REST chaining (Order → Market)
- [x] Uses Jackson and dynamic REST URIs
- [x] Spring Validation with `@Valid`
- [x] Global Exception Handling
- [x] GitHub + video-ready submission

---

## 🔗 GitHub Repo

*https://github.com/Sameehashaik/EAM_Project.git*
