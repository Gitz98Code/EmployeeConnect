# Employee-Connect

**Employee-Connect** is a microservices-based project built with Spring Boot and Spring Cloud. It demonstrates how multiple services work together with service discovery, API gateway, centralized configuration, messaging, inter-service communication, and containerization.

## 🏗️ Microservices Included

- **Service Registry (Eureka Server)** → Manages service discovery.  
- **API Gateway** → Routes client requests to the correct microservice.  
- **Config Server** → Centralized configuration for all services.  
- **Department Service** → Handles department-related data.  
- **Employee Service** → Manages employee information.  
- **Organization Service** → Handles organization-level details.  

## ⚙️ Infrastructure Components

- **RabbitMQ** → Message broker for asynchronous communication.  
- **Docker & Docker Compose** → Containerized deployment of services + RabbitMQ.  

## 🔗 Inter-Service Communication

Services talk to each other using **Spring Cloud OpenFeign**.  
Feign Clients make it simple to call REST APIs of other microservices without writing boilerplate `RestTemplate` code.  

**Example:**
- Employee Service ↔ Department Service  
- Employee Service ↔ Organization Service  

## 🚀 Key Features

- Microservices communication with Feign Client  
- Service discovery using Eureka  
- Centralized configuration with Config Server  
- API Gateway as a single entry point  
- Asynchronous messaging with RabbitMQ  
- Dockerized services for easy deployment
