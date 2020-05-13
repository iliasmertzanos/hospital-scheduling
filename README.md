# App description
This is my current project currently working on.

This application provides functionalities based on microservices architecture, so that patient treatment appointments can be scheduled taking into account the plan and availability of the medical doctors as well as the free capacities of the hospital.

## Stack
- **Java 1.8**
- **Spring Boot**
- **MySql** as Database
- **RabbitMQ** as a Message Broker
- **Feign** to create REST Clients
- **Ribbon** to Client Side Load Balance
- **Eureka** to a Service Discovery
- **Sleuth** and **Zipkin** to a Distributed Tracing
- **Prometheus** as a Metrics Collector
- **Grafana** as Metrics Analytics and UI
- **Swagger** to create an OpenAPI documentation
 
## TODO List

- [ ] Create just a simple version of the app using MySql data base
 - Create Appointment booking service for scheduling a treatment 
 - Create Medical service to determine an appropriate treatment and doctor given a patients disease
 - Create Financial service that manages insurance and bill issues of a patient
- [ ] Build in Feign REST Client for Service Invocation
- [ ] Build in load balancing with Ribbon
- [ ] Build in Service Discovery (Eureka)
- [ ] Setting up Zuul API Gateway
- [ ] Setting up Spring Cloud Sleuth
- [ ] Build in a RabbitMQ Messaging Broker 
- [ ] Setting up Distributed Tracing with Zipkin  
- [ ] Integrate Prometheus with: RabbitMQ Cluster, All MySql containers, All Services containers, Zipkin
- [ ] Create a docker-compose to up the whole environment

