# Strangler Fig Modernization: Java 21 & Spring Cloud

This project demonstrates the **Strangler Fig Pattern** used to incrementally migrate functionality from a Legacy Monolith to a Modern Microservice architecture using **Java 21 Virtual Threads**.

## 🏗 Architecture
The system consists of four main components:
1. **Discovery Server (Eureka):** Service registry for dynamic lookups.
2. **API Gateway (Spring Cloud Gateway):** The "Traffic Cop" that routes requests and implements Circuit Breakers.
3. **Legacy Monolith:** The existing application handling `/api/users`.
4. **Modern Service:** High-performance Java 21 service handling `/api/orders`.

## 🚀 Key Features
- **Project Loom (Virtual Threads):** The Modern Service is optimized for massive concurrency.
- **Resilience:** Integrated Resilience4j Circuit Breaker to prevent cascading failures.
- **Dynamic Scaling:** Automatic load balancing across multiple instances via Eureka.
- **Zero-Downtime Migration:** Path rewriting allows the frontend to stay consistent while the backend evolves.

## 📊 Benchmark Results
In a stress test of 200 concurrent users (2,000 total requests):
- **Modern (Java 21):** ~1,267 req/sec | 99th% Latency: 400ms
- **Legacy (Java 17):** ~626 req/sec | 99th% Latency: 1,540ms

## 🛠 Running the Project

### Prerequisites
- JDK 21
- Maven 3.9+
- Docker (Optional)

### Step-by-Step Start
1. **Start Discovery Server:** `mvn spring-boot:run` in `discovery-server`
2. **Start Legacy Monolith:** `mvn spring-boot:run` in `legacy-monolith`
3. **Start Gateway:** `mvn spring-boot:run` in `gateway-service`
4. **Start Modern Service:** `mvn spring-boot:run` in `modern-service`

### Testing the "Strangler" Routes
- **Legacy Route:** `GET http://localhost:8080/api/users/1`
- **Modern Route:** `GET http://localhost:8080/api/orders/123`
- **Fallback Test:** Stop the Modern Service and hit the order route to see the Circuit Breaker in action.

## 🧪 Testing
Run the full suite including context loads and routing tests:
```bash
mvn clean test
```

### 3. Final GitHub Push Checklist
To make your repository look professional, ensure your folder structure looks like this:

```text
strangler-fig-project/
├── .gitignore              <-- (Include target/, .idea/, .DS_Store)
├── pom.xml                 <-- (The Parent POM)
├── README.md
├── discovery-server/
├── gateway-service/
├── legacy-monolith/
└── modern-service/
```

## LICENSE
MIT