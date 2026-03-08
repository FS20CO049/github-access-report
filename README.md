# GitHub Access Report API

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-green?logo=springboot)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8-blue?logo=apachemaven)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey)](https://opensource.org/licenses/MIT)

---

## Overview
This project is a **Spring Boot REST API** that generates a **GitHub access report**.  
It fetches repository access details and returns the users who have access to a repository.  

This project can be useful for teams or organizations to **track repository access** for better security and auditing.

---

## Technologies Used
- **Java 17**
- **Spring Boot 3.2.5**
- **REST API**
- **Maven**
- **GitHub API**

---

## Features
- Fetch GitHub repository collaborators  
- Generate access report dynamically  
- REST endpoint to retrieve report  
- Easy to configure and run  

---

## Prerequisites
- Java JDK 17 or above  
- Maven 3.8+  
- Git  
- Internet connection (for GitHub API requests)  
-  GitHub Personal Access Token for private repos  

---

## Project Structure
src/main/java/com/example/githubreport/
```text
src/main/java/com/example/githubreport/
├── client/
│   └── GithubClient.java       # Handles communication with GitHub API
├── controller/
│   └── AccessReportController.java  # REST endpoints
├── service/
│   └── GithubService.java      # Business logic to generate reports
├── model/
│   ├── Repo.java               # Repository model
│   └── User.java               # User model
└── GithubAccessReportApplication.java  # Main Spring Boot application
---
##. Architecture diagram  
images/architecture-diagram.png
## API Endpoint

**GET** `/api/access-report`

## How to Call the API Endpoint

**Endpoint:** `/api/access-report`  
**Method:** GET  
**Content-Type:** application/json  

**Example using curl:**

```bash
curl -X GET http://localhost:8080/api/access-report

**Example Response:**

{
  "repository-name": [
    "user1",
    "user2"
  ]
}
## Authentication

This API uses a **GitHub Personal Access Token** to authenticate requests to private repositories.  

- Add your token in `application.properties` or as an environment variable:

```properties
github.token=YOUR_PERSONAL_ACCESS_TOKEN

## How to Run the Project

1.Clone the repository:

git clone https://github.com/FS20CO049/github-access-report.git
cd github-access-report

2.Run the Spring Boot application:

mvn spring-boot:run
3.Open your browser or Postman and access:
http://localhost:8080/api/access-report

**Example:**

## Assumptions and Design Decisions

- `GithubClient` handles all communication with the GitHub API.  
- `GithubService` contains business logic to generate access reports.  
- Models `Repo` and `User` include only fields required for reporting.  
- If no personal access token is provided, only **public repositories** are accessible.  
- The API currently fetches **all contributors** for each repository.

Author
Kavyashri Nandewar
Email: kavyanandewar08@gmail.com
GitHub: https://github.com/FS20CO049
