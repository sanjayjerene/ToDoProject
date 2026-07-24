🐳 DOCKERIZED TODO APPLICATION

A full-stack Todo Management Application built using Spring Boot, PostgreSQL, and a static HTML/CSS/JavaScript frontend.

The entire system is containerized using Docker and Docker Compose, allowing the application to run with a single command.

The backend image is published on Docker Hub:

baranib812/todo-backend

This allows anyone to run the application without building the backend JAR locally.

🏗 PROJECT ARCHITECTURE

The application runs as three independent containers connected through Docker Compose.

The browser communicates with the frontend container, which interacts with the Spring Boot backend API, which stores data in PostgreSQL.

                +---------------------+
                |      Browser        |
                |  http://localhost   |
                +----------+----------+
                           |
                           v
                +---------------------+
                |   Frontend (Nginx)  |
                |   Container         |
                |   Port 3000         |
                +----------+----------+
                           |
                           v
                +---------------------+
                |  Spring Boot API    |
                |  Backend Container  |
                |  Port 8080          |
                +----------+----------+
                           |
                           v
                +---------------------+
                |   PostgreSQL DB     |
                |   Container         |
                |   Port 5432         |
                +---------------------+

Each component runs in its own container and communicates through the Docker network.

🚀 TECHNOLOGIES USED
⚙ Backend

Java 17

Spring Boot

Spring Data JPA

PostgreSQL

Maven

🎨 Frontend

HTML

CSS

JavaScript

🐳 DevOps / Infrastructure

Docker

Docker Compose

Nginx

📂 PROJECT STRUCTURE
TODO
│
├── docker-compose.yml
├── LICENSE
│
├── TodoBackend
│   ├── Dockerfile
│   ├── pom.xml
│   ├── mvnw
│   ├── mvnw.cmd
│   │
│   ├── src
│   │   ├── main
│   │   └── test
│   │
│   ├── target
│   │
│   └── .mvn
│
└── TodoFrontend
    ├── Dockerfile
    ├── index.html
    ├── register.html
    ├── todos.html
    ├── script.js
    └── style.css
📋 PREREQUISITES

Make sure the following are installed on your system:

🐳 Docker

🐳 Docker Compose

⚠ Java and Maven are NOT required anymore because the backend image is already built and hosted on Docker Hub.

▶ HOW TO RUN THE APPLICATION
1️⃣ Clone the Repository
git clone https://github.com/barani961/todoproject.git
cd todoproject
2️⃣ Start the Application

Run Docker Compose:

docker compose up

Docker will automatically:

Pull the backend image baranib812/todo-backend

Start PostgreSQL

Start the frontend container

Connect all containers through the Docker network

✅ No manual build or JAR creation is required.

🌐 ACCESS THE APPLICATION

Open your browser and visit:

Frontend
http://localhost:3000
Backend API
http://localhost:8080
🔐 AUTHENTICATION FLOW

User registers using register.html

User logs in using login.html

Backend returns a JWT token

Token is stored in localStorage

Authenticated requests include the token for accessing protected endpoints

📡 API ENDPOINTS
Authentication
POST /auth/register
POST /auth/login
Todos
GET /todos
POST /todos
PUT /todos/{id}
DELETE /todos/{id}
🛑 STOPPING THE APPLICATION

To stop running containers:

docker compose down
📦 BACKEND IMAGE

The backend container is built from the Docker image published on Docker Hub:

baranib812/todo-backend

You can pull it manually using:

docker pull baranib812/todo-backend:latest

✅ Your application can now be started with one command:

docker compose up
