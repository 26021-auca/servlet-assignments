# Spring Boot RESTful API - Complete Assignment

This project contains implementations for all 5 questions plus the bonus question from the Spring Boot RESTful API practical assignment.

## Project Structure

```
springboot-restful-api/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── restapi/
│       │           ├── library/          # Question 1: Library API
│       │           │   ├── model/
│       │           │   │   └── Book.java
│       │           │   └── controller/
│       │           │       └── BookController.java
│       │           ├── student/          # Question 2: Student API
│       │           │   ├── model/
│       │           │   │   └── Student.java
│       │           │   └── controller/
│       │           │       └── StudentController.java
│       │           ├── restaurant/       # Question 3: Restaurant API
│       │           │   ├── model/
│       │           │   │   └── MenuItem.java
│       │           │   └── controller/
│       │           │       └── MenuController.java
│       │           ├── ecommerce/        # Question 4: E-commerce API
│       │           │   ├── model/
│       │           │   │   └── Product.java
│       │           │   └── controller/
│       │           │       └── ProductController.java
│       │           ├── task/             # Question 5: Task Management API
│       │           │   ├── model/
│       │           │   │   └── Task.java
│       │           │   └── controller/
│       │           │       └── TaskController.java
│       │           ├── user/             # Bonus: User Profile API
│       │           │   ├── model/
│       │           │   │   ├── UserProfile.java
│       │           │   │   └── ApiResponse.java
│       │           │   └── controller/
│       │           │       └── UserProfileController.java
│       │           └── RestApiApplication.java
│       └── resources/
│           └── application.properties
└── pom.xml
```

## Prerequisites

- Java JDK 17 or higher
- Maven 3.6+ or IDE with Maven support (IntelliJ IDEA, Eclipse, VS Code)
- Postman (for testing APIs)

## How to Run the Application

### Option 1: Using Maven Command Line
```bash
cd springboot-restful-api
mvn spring-boot:run
```

### Option 2: Using IDE
1. Import the project as a Maven project
2. Locate `RestApiApplication.java`
3. Right-click and select "Run"

### Option 3: Using JAR file
```bash
mvn clean package
java -jar target/springboot-restful-api-1.0.0.jar
```

The application will start on **http://localhost:8080**

---

## Question 1: Library Book Management API

**Base URL:** `/api/books`

### Endpoints

#### 1. Get All Books
- **Method:** GET
- **URL:** `http://localhost:8080/api/books`
- **Description:** Returns all books in the library
- **Response Status:** 200 OK
- **Sample Response:**
```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
  },
  {
    "id": 2,
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "isbn": "978-0134685991",
    "publicationYear": 2017
  }
]
```

#### 2. Get Book by ID
- **Method:** GET
- **URL:** `http://localhost:8080/api/books/{id}`
- **Example:** `http://localhost:8080/api/books/1`
- **Response Status:** 200 OK (found) or 404 Not Found

#### 3. Search Books by Title
- **Method:** GET
- **URL:** `http://localhost:8080/api/books/search?title={title}`
- **Example:** `http://localhost:8080/api/books/search?title=clean`
- **Response Status:** 200 OK

#### 4. Add New Book
- **Method:** POST
- **URL:** `http://localhost:8080/api/books`
- **Headers:** `Content-Type: application/json`
- **Request Body:**
```json
{
  "title": "Design Patterns",
  "author": "Gang of Four",
  "isbn": "978-0201633610",
  "publicationYear": 1994
}
```
- **Response Status:** 201 Created

#### 5. Delete Book
- **Method:** DELETE
- **URL:** `http://localhost:8080/api/books/{id}`
- **Example:** `http://localhost:8080/api/books/1`
- **Response Status:** 204 No Content (success) or 404 Not Found

---

## Question 2: Student Registration API

**Base URL:** `/api/students`

### Endpoints

#### 1. Get All Students
- **Method:** GET
- **URL:** `http://localhost:8080/api/students`
- **Response Status:** 200 OK

#### 2. Get Student by ID
- **Method:** GET
- **URL:** `http://localhost:8080/api/students/{studentId}`
- **Example:** `http://localhost:8080/api/students/1`
- **Response Status:** 200 OK or 404 Not Found

#### 3. Get Students by Major
- **Method:** GET
- **URL:** `http://localhost:8080/api/students/major/{major}`
- **Example:** `http://localhost:8080/api/students/major/Computer Science`
- **Response Status:** 200 OK

#### 4. Filter Students by Minimum GPA
- **Method:** GET
- **URL:** `http://localhost:8080/api/students/filter?gpa={minGpa}`
- **Example:** `http://localhost:8080/api/students/filter?gpa=3.5`
- **Response Status:** 200 OK

#### 5. Register New Student
- **Method:** POST
- **URL:** `http://localhost:8080/api/students`
- **Request Body:**
```json
{
  "firstName": "Alice",
  "lastName": "Johnson",
  "email": "alice.j@email.com",
  "major": "Computer Science",
  "gpa": 3.7
}
```
- **Response Status:** 201 Created

#### 6. Update Student Information
- **Method:** PUT
- **URL:** `http://localhost:8080/api/students/{studentId}`
- **Request Body:** (Complete student object)
- **Response Status:** 200 OK or 404 Not Found

---

## Question 3: Restaurant Menu API

**Base URL:** `/api/menu`

### Endpoints

#### 1. Get All Menu Items
- **Method:** GET
- **URL:** `http://localhost:8080/api/menu`
- **Response Status:** 200 OK

#### 2. Get Menu Item by ID
- **Method:** GET
- **URL:** `http://localhost:8080/api/menu/{id}`
- **Example:** `http://localhost:8080/api/menu/1`
- **Response Status:** 200 OK or 404 Not Found

#### 3. Get Items by Category
- **Method:** GET
- **URL:** `http://localhost:8080/api/menu/category/{category}`
- **Example:** `http://localhost:8080/api/menu/category/Dessert`
- **Response Status:** 200 OK

#### 4. Get Available Items
- **Method:** GET
- **URL:** `http://localhost:8080/api/menu/available?available=true`
- **Response Status:** 200 OK

#### 5. Search by Name
- **Method:** GET
- **URL:** `http://localhost:8080/api/menu/search?name={name}`
- **Example:** `http://localhost:8080/api/menu/search?name=chicken`
- **Response Status:** 200 OK

#### 6. Add New Menu Item
- **Method:** POST
- **URL:** `http://localhost:8080/api/menu`
- **Request Body:**
```json
{
  "name": "Pizza",
  "description": "Italian style pizza",
  "price": 14.99,
  "category": "Main Course",
  "available": true
}
```
- **Response Status:** 201 Created

#### 7. Toggle Item Availability
- **Method:** PUT
- **URL:** `http://localhost:8080/api/menu/{id}/availability`
- **Example:** `http://localhost:8080/api/menu/1/availability`
- **Response Status:** 200 OK or 404 Not Found

#### 8. Delete Menu Item
- **Method:** DELETE
- **URL:** `http://localhost:8080/api/menu/{id}`
- **Response Status:** 204 No Content or 404 Not Found

---

## Question 4: E-Commerce Product API

**Base URL:** `/api/products`

### Endpoints

#### 1. Get All Products (with Pagination)
- **Method:** GET
- **URL:** `http://localhost:8080/api/products?page={page}&limit={limit}`
- **Example:** `http://localhost:8080/api/products?page=0&limit=5`
- **Response Status:** 200 OK

#### 2. Get Product by ID
- **Method:** GET
- **URL:** `http://localhost:8080/api/products/{productId}`
- **Response Status:** 200 OK or 404 Not Found

#### 3. Get Products by Category
- **Method:** GET
- **URL:** `http://localhost:8080/api/products/category/{category}`
- **Example:** `http://localhost:8080/api/products/category/Electronics`
- **Response Status:** 200 OK

#### 4. Get Products by Brand
- **Method:** GET
- **URL:** `http://localhost:8080/api/products/brand/{brand}`
- **Example:** `http://localhost:8080/api/products/brand/Apple`
- **Response Status:** 200 OK

#### 5. Search Products by Keyword
- **Method:** GET
- **URL:** `http://localhost:8080/api/products/search?keyword={keyword}`
- **Example:** `http://localhost:8080/api/products/search?keyword=phone`
- **Response Status:** 200 OK

#### 6. Get Products by Price Range
- **Method:** GET
- **URL:** `http://localhost:8080/api/products/price-range?min={min}&max={max}`
- **Example:** `http://localhost:8080/api/products/price-range?min=100&max=500`
- **Response Status:** 200 OK

#### 7. Get In-Stock Products
- **Method:** GET
- **URL:** `http://localhost:8080/api/products/in-stock`
- **Response Status:** 200 OK

#### 8. Add New Product
- **Method:** POST
- **URL:** `http://localhost:8080/api/products`
- **Request Body:**
```json
{
  "name": "iPad Pro",
  "description": "Apple tablet",
  "price": 799.99,
  "category": "Electronics",
  "stockQuantity": 25,
  "brand": "Apple"
}
```
- **Response Status:** 201 Created

#### 9. Update Product
- **Method:** PUT
- **URL:** `http://localhost:8080/api/products/{productId}`
- **Response Status:** 200 OK or 404 Not Found

#### 10. Update Stock Quantity
- **Method:** PATCH
- **URL:** `http://localhost:8080/api/products/{productId}/stock?quantity={quantity}`
- **Example:** `http://localhost:8080/api/products/1/stock?quantity=100`
- **Response Status:** 200 OK or 404 Not Found

#### 11. Delete Product
- **Method:** DELETE
- **URL:** `http://localhost:8080/api/products/{productId}`
- **Response Status:** 204 No Content or 404 Not Found

---

## Question 5: Task Management API

**Base URL:** `/api/tasks`

### Endpoints

#### 1. Get All Tasks
- **Method:** GET
- **URL:** `http://localhost:8080/api/tasks`
- **Response Status:** 200 OK

#### 2. Get Task by ID
- **Method:** GET
- **URL:** `http://localhost:8080/api/tasks/{taskId}`
- **Response Status:** 200 OK or 404 Not Found

#### 3. Get Tasks by Completion Status
- **Method:** GET
- **URL:** `http://localhost:8080/api/tasks/status?completed={true/false}`
- **Example:** `http://localhost:8080/api/tasks/status?completed=false`
- **Response Status:** 200 OK

#### 4. Get Tasks by Priority
- **Method:** GET
- **URL:** `http://localhost:8080/api/tasks/priority/{priority}`
- **Example:** `http://localhost:8080/api/tasks/priority/HIGH`
- **Response Status:** 200 OK

#### 5. Create New Task
- **Method:** POST
- **URL:** `http://localhost:8080/api/tasks`
- **Request Body:**
```json
{
  "title": "Learn React",
  "description": "Complete React tutorial",
  "completed": false,
  "priority": "MEDIUM",
  "dueDate": "2026-02-20"
}
```
- **Response Status:** 201 Created

#### 6. Update Task
- **Method:** PUT
- **URL:** `http://localhost:8080/api/tasks/{taskId}`
- **Response Status:** 200 OK or 404 Not Found

#### 7. Mark Task as Completed
- **Method:** PATCH
- **URL:** `http://localhost:8080/api/tasks/{taskId}/complete`
- **Response Status:** 200 OK or 404 Not Found

#### 8. Delete Task
- **Method:** DELETE
- **URL:** `http://localhost:8080/api/tasks/{taskId}`
- **Response Status:** 204 No Content or 404 Not Found

---

## Bonus Question: User Profile API

**Base URL:** `/api/users`

**Note:** All responses are wrapped in ApiResponse object with structure:
```json
{
  "success": true/false,
  "message": "Description message",
  "data": { actual data object }
}
```

### Endpoints

#### 1. Get All Users
- **Method:** GET
- **URL:** `http://localhost:8080/api/users`
- **Response Status:** 200 OK

#### 2. Get User by ID
- **Method:** GET
- **URL:** `http://localhost:8080/api/users/{userId}`
- **Response Status:** 200 OK or 404 Not Found

#### 3. Get User by Username
- **Method:** GET
- **URL:** `http://localhost:8080/api/users/username/{username}`
- **Example:** `http://localhost:8080/api/users/username/john_doe`
- **Response Status:** 200 OK or 404 Not Found

#### 4. Get Users by Country
- **Method:** GET
- **URL:** `http://localhost:8080/api/users/country/{country}`
- **Example:** `http://localhost:8080/api/users/country/USA`
- **Response Status:** 200 OK

#### 5. Get Users by Age Range
- **Method:** GET
- **URL:** `http://localhost:8080/api/users/age-range?min={min}&max={max}`
- **Example:** `http://localhost:8080/api/users/age-range?min=20&max=30`
- **Response Status:** 200 OK

#### 6. Get Active Users
- **Method:** GET
- **URL:** `http://localhost:8080/api/users/active`
- **Response Status:** 200 OK

#### 7. Create User Profile
- **Method:** POST
- **URL:** `http://localhost:8080/api/users`
- **Request Body:**
```json
{
  "username": "new_user",
  "email": "newuser@example.com",
  "fullName": "New User",
  "age": 26,
  "country": "Canada",
  "bio": "Software engineer"
}
```
- **Response Status:** 201 Created or 409 Conflict (username exists)
- **Sample Response:**
```json
{
  "success": true,
  "message": "User profile created successfully",
  "data": {
    "userId": 6,
    "username": "new_user",
    "email": "newuser@example.com",
    "fullName": "New User",
    "age": 26,
    "country": "Canada",
    "bio": "Software engineer",
    "active": true
  }
}
```

#### 8. Update User Profile
- **Method:** PUT
- **URL:** `http://localhost:8080/api/users/{userId}`
- **Response Status:** 200 OK or 404 Not Found

#### 9. Activate User
- **Method:** PATCH
- **URL:** `http://localhost:8080/api/users/{userId}/activate`
- **Response Status:** 200 OK or 404 Not Found

#### 10. Deactivate User
- **Method:** PATCH
- **URL:** `http://localhost:8080/api/users/{userId}/deactivate`
- **Response Status:** 200 OK or 404 Not Found

#### 11. Delete User Profile
- **Method:** DELETE
- **URL:** `http://localhost:8080/api/users/{userId}`
- **Response Status:** 200 OK or 404 Not Found

---

## Testing with Postman

### Import Testing Collection

1. Open Postman
2. Create a new collection named "Spring Boot REST API"
3. For each endpoint above, create a new request
4. Set the appropriate HTTP method (GET, POST, PUT, PATCH, DELETE)
5. Enter the URL
6. For POST/PUT requests, add request body in JSON format
7. Click "Send" to test

### Example Test Scenarios

**Test Scenario 1: Create and Retrieve a Book**
1. POST new book → Should return 201 with the created book
2. GET all books → Should include the newly created book
3. GET book by ID → Should return the specific book

**Test Scenario 2: Filter Students**
1. GET all students → Note the different majors and GPAs
2. GET students by major "Computer Science"
3. GET students with GPA >= 3.5

**Test Scenario 3: Menu Management**
1. GET all menu items
2. POST new menu item
3. Toggle availability
4. Search by name

---

## HTTP Status Codes Used

- **200 OK** - Successful GET, PUT, PATCH requests
- **201 Created** - Successful POST requests
- **204 No Content** - Successful DELETE requests
- **404 Not Found** - Resource not found
- **409 Conflict** - Resource already exists (Bonus question)

---

## Key Annotations Used

- `@RestController` - Marks the class as a REST controller
- `@RequestMapping` - Maps base URL path for the controller
- `@GetMapping` - Handles HTTP GET requests
- `@PostMapping` - Handles HTTP POST requests
- `@PutMapping` - Handles HTTP PUT requests
- `@PatchMapping` - Handles HTTP PATCH requests
- `@DeleteMapping` - Handles HTTP DELETE requests
- `@PathVariable` - Extracts values from URL path
- `@RequestParam` - Extracts query parameters from URL
- `@RequestBody` - Binds request body to method parameter

---

## Project Features

✅ All 5 questions + Bonus question implemented  
✅ Proper package structure (controller and model packages)  
✅ Appropriate HTTP methods and status codes  
✅ In-memory data storage using ArrayList  
✅ Sample data initialized in constructors  
✅ Custom ApiResponse wrapper for Bonus question  
✅ Comprehensive error handling  
✅ Clear comments and documentation  

---

## Submission Instructions

1. Create a branch named: `restFull_api_YourStudentId`
2. Push your code to that branch
3. Submit before the deadline: **Next class at 17:59**

---

## Author Notes

This project demonstrates:
- RESTful API design principles
- Spring Boot annotations and conventions
- Proper HTTP methods and status codes
- Clean code structure and organization
- CRUD operations implementation
- Search and filter functionality
- Pagination implementation

All code is beginner-friendly with detailed comments explaining each part.

---

## Support

If you have questions about the code:
1. Read the comments in each controller
2. Check the sample requests/responses
3. Test endpoints in Postman
4. Review the Spring Boot documentation

Good luck with your assignment! 🚀
