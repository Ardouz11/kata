# Kata Repository

## Table of Contents

- [Prerequisites](#prerequisites)
- [Cloning the Repository](#cloning-the-repository)
- [Building the Project](#building-the-project)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- [Java Development Kit (JDK) 21 or higher](https://www.oracle.com/fr/java/technologies/downloads/#java21)
- [Apache Maven](https://maven.apache.org/install.html)
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

## Cloning the Repository

To clone the repository to your local machine, open your terminal and run:

```bash
git clone https://github.com/Ardouz11/kata.git
```
## Building the Project

Navigate to the project's root directory:

```bash
cd kata
```
Then build the project using Maven
```bash
./mvnw clean install
```
Or, if you have Maven installed globally:
```bash
mvn clean install
```
This will compile the code, resolve dependencies, and run tests.

## Running the Application

To start the Spring Boot application, use:
```bash
./mvnw spring-boot:run
```
Or, if Maven is installed globally:
```bash
mvn spring-boot:run
```

Once the application starts:
```bash
curl -X GET -s http://localhost:8080/api/v1/general-ledger
```

## Running Tests

To execute all tests, run:
```bash
./mvnw test
```
or 
```bash
mvn test

```

