Lightweight web application for **tracking issues within existing projects**.  
Users can browse seeded projects, create and update issues, assign them to team members, change their status or priority, and add comments.  
The Angular frontend provides a simple UI, while the backend exposes a REST API secured with Basic Auth.


---

## ✨ Features
- 🔐 **Authentication** with Basic Auth
- 📝 **Issues** – CRUD, filters by status/priority/assignee, pagination & sorting
- 💬 **Comments** – add & view comments per issue
- ⚡ **REST API** – clean endpoints with validation and global exception handling
- 🖥 **Frontend (Angular 17)** – UI for projects, issues, comments
- 📦 **Dockerized** – runs with PostgreSQL via docker-compose

---

## 🛠️ Technologies Used
### Backend
- Java 21, Spring Boot (Web, Data JPA, Security, Validation, Web Services)
- Hibernate, MapStruct, Lombok, Flyway
- PostgreSQL, H2 (tests)

### Frontend
- Angular 17 (standalone components)
- Bootstrap 5


### DevOps
- Maven
- Docker, docker-compose

### Design Patterns
- Repository, DTO/Mapper
- Strategy, Factory, Template Method

---
