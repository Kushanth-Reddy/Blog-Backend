# Blog-Backend (Spring Boot)

A robust backend REST API for a blogging platform, built with **Spring Boot**. This application handles user management, article publishing, and social interaction features with secure JWT authentication.

## 🚀 Features

### Core Business Logic
* **User Authentication:** Stateless session management using **Spring Security** and **JWT**.
* **Article Management:** CRUD operations for articles with support for tags.
* **Comments:** Threaded or linear comments associated with specific articles.

### Advanced Querying & Sorting
* **Filtering:** Retrieve articles by specific **Tags**.
* **Sorting:** * Sort Articles by *Date Published*.
    * Sort Comments by *Date Published* (Ascending/Descending).
* **Feeds:** Specialized endpoints to fetch the latest content.

## 🛠 Tech Stack

* **Language:** Java
* **Framework:** Spring Boot 3+
* **Security:** Spring Security, JWT (JSON Web Tokens)
* **Database:** [e.g., PostgreSQL / MySQL / H2]
* **Persistence:** Spring Data JPA / Hibernate
* **Build Tool:** [Maven / Gradle]

## ⚙️ Configuration

1.  **Clone the repository**
    ```bash
    git clone [https://github.com/yourusername/blog-backend.git](https://github.com/yourusername/blog-backend.git)
    cd blog-backend
    ```

2.  **Database Setup**
    Update `src/main/resources/application.properties` (or `application.yml`) with your database credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    
    # JWT Secret Configuration
    app.jwtSecret=your_secure_random_secret_key
    app.jwtExpirationMs=86400000
    ```

3.  **Build and Run**
    You can run the application using the Maven/Gradle wrapper included in the project.

    **Using Maven:**
    ```bash
    ./mvnw spring-boot:run
    ```

    **Using Gradle:**
    ```bash
    ./gradlew bootRun
    ```

## 🔌 API Endpoints

The API is accessible at `http://localhost:8080`.

### 🔐 Authentication
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Register a new user account |
| `POST` | `/api/auth/login` | Login and receive a JWT Bearer token |

### 📝 Articles
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/articles` | Get all articles (supports `?tag=` and `?sort=date`) |
| `GET` | `/api/articles/feed` | Get recent articles feed |
| `GET` | `/api/articles/{slug}` | Get a single article details |
| `POST` | `/api/articles` | Publish a new article (Requires Token) |
| `PUT` | `/api/articles/{slug}` | Update an article (Requires Token) |

### 💬 Comments
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/articles/{slug}/comments` | Get comments (supports `?sort=date`) |
| `POST` | `/api/articles/{slug}/comments` | Add a comment to an article (Requires Token) |

## 🧪 Testing

Since this is a backend-only project, you can test the endpoints using:
* **Postman** or **Insomnia**
* **cURL** (Command Line)
* **Swagger UI** (if configured at `/swagger-ui.html`)

**Example: Create Article**
```bash
curl -X POST http://localhost:8080/api/articles \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"title": "My First Post", "body": "Hello World", "tags": ["java", "spring"]}'