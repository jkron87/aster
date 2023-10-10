# Aster Meeting Management API

A RESTful API designed for managing meetings and meeting notes, built with Scala and Spring Boot.

## Technical Stack

- Language: Scala
- Framework: Spring Boot
- Build Tool: Gradle
- Database: MySQL
- Containerization: Docker

## Setup

### Prerequisites

- Java JDK 11
- Docker & Docker Compose
- Gradle (optional, since you can use the Gradle Wrapper)

### Building and Running the Application

1. **Building the JAR:**

    ```bash
    ./gradlew bootJar
    ```

2. **Building and running with Docker Compose:**

    ```bash
    docker-compose up --build
    ```

## API Endpoints

### Meetings

- `POST /meetings`: Schedule a new meeting.
- `GET /meetings`: Retrieve a list of scheduled meetings.

### Meeting Notes

- `POST /meeting-notes`: Add notes associated with a specific meeting.
- `GET /meeting-notes/{id}`: Retrieve notes based on a meeting ID.

## Database Configuration

The application uses MySQL as a database. The database configurations are specified in the `docker-compose.yml` file. Default configurations are:

- **Username:** `user`
- **Password:** `userpassword`
- **Database:** `mydatabase`

