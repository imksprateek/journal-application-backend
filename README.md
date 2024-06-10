# Journal Application Backend - UNDER DEVELOPMENT

Welcome to the backend repository for the Journal Application, a Spring Boot-based project that leverages Kafka, Redis, Swagger UI, and MongoDB to provide a robust and scalable journaling platform.

## Table of Contents

- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This project serves as the backend for a journal application, handling all data storage, retrieval, and processing needs. It is designed to be scalable and efficient, making use of modern technologies such as Kafka for message brokering, Redis for caching, Swagger UI for API documentation, and MongoDB for data storage.

## Technologies Used

- **Spring Boot**: Framework for building production-ready applications.
- **Apache Kafka**: Distributed event streaming platform.
- **Redis**: In-memory data structure store, used as a cache.
- **Swagger UI**: Tool for visualizing APIs.
- **MongoDB**: NoSQL database for storing journal entries.

## Getting Started

### Prerequisites

- Java 11 or higher
- Apache Kafka
- Redis
- MongoDB
- Maven

### Installation

1. **Clone the repository:**

    ```sh
    git clone https://github.com/imksprateek/journal-application-backend.git
    cd journal-application-backend
    ```

2. **Install dependencies:**

    ```sh
    mvn clean install
    ```

### Running the Application

1. **Start Kafka, Redis, and MongoDB:**

    Make sure Kafka, Redis, and MongoDB services are running.


3. **Run the Spring Boot application:**

    ```sh
    mvn spring-boot:run
    ```

    The application will start on `http://localhost:8080`.

## Configuration

The application configuration is managed via the `application.properties` file located in the `src/main/resources` directory. Key configurations include:

- Kafka connection details
- Redis connection details
- MongoDB connection details

## API Documentation

Swagger UI is used for API documentation. Once the application is running, you can access the API documentation at:
  ```sh
   http://localhost:8080/swagger-ui.html
  ```

## Contributing

We welcome contributions to the Journal Application Backend. To contribute:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

Please ensure your code adheres to the existing style guidelines and includes appropriate tests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

Thank you for using the Journal Application Backend! If you have any questions or feedback, please open an issue on GitHub.

