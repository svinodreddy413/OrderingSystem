

# Ordering System application for a fictional organization that sells bricks with Spring Boot

Build Ordering System Application using Spring Boot, H2, JPA and Hibernate.

## Requirements

1. Java

2. Maven

3. H2

4. Mockito Framework

5. JPA

6. Hibernate

## Steps to Setup

**1. Import the Project to Eclipse / STS workspace **


**2. Build the Application**
```bash
mvn clean install
```

**3. Build and Run the application using maven**

+ Goto main class right click run as java application or spring boot application

The application will start running at <http://localhost:9090>

## Explore Rest APIs

The application defines following APIs.

	POST /api/v1/order/createOrder

    GET /api/v1/order/getOrder?orderReference=ORD_1

	GET /api/v1/order/getAllOrders
	
	POST /api/v1/order/updateOrder?isDispatchRequest=false
	

You can test them using postman or any other rest client.


