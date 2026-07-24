TODO APPLICATION

A full-stack Todo Management Application built using Spring Boot, PostgreSQL, and a static HTML/CSS/JavaScript frontend.

🏗 PROJECT ARCHITECTURE

The frontend is served as static HTML/CSS/JavaScript and communicates with the Spring Boot backend API, which persists data in PostgreSQL.

            +---------------------+
            |      Browser        |
            |  http://localhost   |
            +----------+----------+
                       |
                       v
            +---------------------+
            |    Frontend         |
            |   Static Files      |
            |   Port 3000*        |
            +----------+----------+
                       |
                       v
            +---------------------+
            |  Spring Boot API    |
            |    Backend          |
            |   Port 8080         |
            +----------+----------+
                       |
                       v
            +---------------------+
            |   PostgreSQL DB     |
            |   Local Database    |
            |   Port 5432         |
            +---------------------+

🚀 TECHNOLOGIES USED

Backend
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

Frontend
- HTML
- CSS
- JavaScript

📂 PROJECT STRUCTURE
TODO
│
├── LICENSE
│
├── TodoBackend
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
- Java 17 installed
- PostgreSQL installed and running locally
- Maven installed or use the bundled Maven wrapper

▶ HOW TO RUN THE APPLICATION
1️⃣ Clone the repository:
   git clone https://github.com/sanjayjerene/ToDoProject.git
   cd todoproject

2️⃣ Start PostgreSQL locally and ensure it is accessible on port 5432.

3️⃣ Run the backend from the `TodoBackend` directory:
   cd TodoBackend
   ./mvnw spring-boot:run
   (on Windows use `mvnw.cmd spring-boot:run`)

4️⃣ Open `TodoFrontend/index.html` in your browser, or serve `TodoFrontend` with a simple local server.

🌐 ACCESS THE APPLICATION
- Frontend: open `TodoFrontend/index.html`
- Backend API: http://localhost:8080

🔐 AUTHENTICATION FLOW
- User registers using `register.html`
- User logs in using `login.html`
- Backend returns a JWT token
- Token is stored in `localStorage`
- Authenticated requests include the token for protected endpoints

📡 API ENDPOINTS
Authentication
- POST /auth/register
- POST /auth/login

Todos
- GET /todos
- POST /todos
- PUT /todos/{id}
- DELETE /todos/{id}
