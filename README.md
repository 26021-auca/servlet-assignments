# Servlet Assignments - Login & Redirect

This project contains two servlet assignments demonstrating form handling and HTTP redirects.

## 📋 Project Structure

```
servlet-assignments/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── servlets/
│       │           ├── LoginServlet.java
│       │           └── RedirectServlet.java
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml
│           ├── login.html
│           └── redirect.html
├── pom.xml
└── README.md
```

## 🎯 Assignments Overview

### Assignment 1: Login Servlet
- **Page**: `login.html`
- **Servlet**: `LoginServlet.java`
- **Features**:
  - Login form with username and password fields
  - Password validation (minimum 8 characters)
  - Displays appropriate messages based on password strength
  - Professional styling with gradient backgrounds

### Assignment 2: Send Redirect
- **Page**: `redirect.html`
- **Servlet**: `RedirectServlet.java`
- **Features**:
  - Input field for search query
  - Fetch button to submit form
  - Uses `sendRedirect()` to redirect to Google search
  - URL encoding for safe query parameters

## 🛠️ Prerequisites

Before you start, make sure you have:

1. **JDK 8 or higher** installed
   - Check: `java -version`
   - Download from: https://www.oracle.com/java/technologies/downloads/

2. **Apache Tomcat** (version 9.x or 10.x recommended)
   - Download from: https://tomcat.apache.org/download-90.cgi
   - Extract to a location (e.g., `C:\apache-tomcat-9.0.x` or `/opt/tomcat`)

3. **Maven** (optional but recommended)
   - Check: `mvn -version`
   - Download from: https://maven.apache.org/download.cgi

4. **VS Code** with recommended extensions:
   - Extension Pack for Java
   - Tomcat for Java
   - Maven for Java

## 🚀 Setup Instructions

### Option 1: Using VS Code with Tomcat Extension

1. **Install VS Code Extensions**:
   - Open VS Code
   - Install "Extension Pack for Java"
   - Install "Tomcat for Java"

2. **Configure Tomcat in VS Code**:
   - Press `Ctrl+Shift+P` (or `Cmd+Shift+P` on Mac)
   - Type "Tomcat: Add Tomcat Server"
   - Select your Tomcat installation directory

3. **Open the Project**:
   - Open the `servlet-assignments` folder in VS Code

4. **Build the Project**:
   ```bash
   # If using Maven
   mvn clean package
   
   # This will create a WAR file in target/servlet-assignments.war
   ```

5. **Deploy to Tomcat**:
   - Right-click on the WAR file in VS Code
   - Select "Run on Tomcat Server"
   
   OR
   
   - Copy `target/servlet-assignments.war` to Tomcat's `webapps` folder
   - Start Tomcat server

6. **Access the Applications**:
   - Login Page: `http://localhost:8080/servlet-assignments/login.html`
   - Redirect Page: `http://localhost:8080/servlet-assignments/redirect.html`

### Option 2: Manual Deployment

1. **Compile Java Files**:
   ```bash
   # Navigate to project directory
   cd servlet-assignments
   
   # Create classes directory
   mkdir -p target/classes
   
   # Compile servlets (make sure servlet-api.jar is in classpath)
   javac -cp "/path/to/tomcat/lib/servlet-api.jar" \
         -d target/classes \
         src/main/java/com/servlets/*.java
   ```

2. **Create WAR File Structure**:
   ```bash
   mkdir -p target/servlet-assignments/WEB-INF/classes
   
   # Copy compiled classes
   cp -r target/classes/com target/servlet-assignments/WEB-INF/classes/
   
   # Copy web.xml
   cp src/main/webapp/WEB-INF/web.xml target/servlet-assignments/WEB-INF/
   
   # Copy HTML files
   cp src/main/webapp/*.html target/servlet-assignments/
   ```

3. **Deploy**:
   - Copy `target/servlet-assignments` folder to Tomcat's `webapps` directory
   - Start Tomcat

4. **Access**:
   - `http://localhost:8080/servlet-assignments/login.html`
   - `http://localhost:8080/servlet-assignments/redirect.html`

## 📝 How to Use

### Assignment 1 - Login Servlet

1. Open `http://localhost:8080/servlet-assignments/login.html`
2. Enter a username
3. Enter a password
4. Click "Login"
5. **Test Cases**:
   - Password < 8 characters: Shows "your password is weak" message
   - Password ≥ 8 characters: Shows "Welcome" message

### Assignment 2 - Redirect Servlet

1. Open `http://localhost:8080/servlet-assignments/redirect.html`
2. Enter a search query (e.g., "Java servlets tutorial")
3. Click "Fetch"
4. You will be redirected to Google search with your query

## 🔍 Code Explanation

### LoginServlet.java

```java
// Key features:
- @WebServlet annotation for URL mapping
- doPost() method handles form submission
- Retrieves username and password parameters
- Validates password length
- Generates HTML response with styling
- Includes XSS protection via escapeHtml()
```

### RedirectServlet.java

```java
// Key features:
- @WebServlet annotation for URL mapping
- doPost() method handles form submission
- Retrieves search query parameter
- URL encodes the query for safe redirection
- Uses response.sendRedirect() to redirect to Google
- Handles empty queries gracefully
```

## 🎨 Features

- **Professional UI**: Modern gradient designs with responsive layouts
- **Security**: XSS protection and input validation
- **Best Practices**: 
  - Servlet annotations (@WebServlet)
  - Proper HTTP method handling (doGet/doPost)
  - URL encoding for redirects
  - HTML escaping for user input

## 🐛 Troubleshooting

### Common Issues:

1. **404 Error - Servlet Not Found**:
   - Check that servlets are compiled correctly
   - Verify @WebServlet annotations or web.xml mappings
   - Ensure WAR is deployed in Tomcat's webapps

2. **ClassNotFoundException**:
   - Ensure servlet-api.jar is in Tomcat's lib folder
   - Check that compiled classes are in WEB-INF/classes

3. **Port 8080 Already in Use**:
   - Change Tomcat port in `conf/server.xml`
   - Or stop the process using port 8080

4. **Tomcat Not Starting**:
   - Check JAVA_HOME environment variable
   - Review Tomcat logs in `logs/catalina.out`

## 📚 Learning Resources

- [Java Servlet Tutorial](https://docs.oracle.com/javaee/7/tutorial/servlets.htm)
- [Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/)
- [HTTP Redirects](https://developer.mozilla.org/en-US/docs/Web/HTTP/Redirections)

## ✅ Testing Checklist

- [ ] Login with password < 8 characters → Shows weak password message
- [ ] Login with password ≥ 8 characters → Shows welcome message
- [ ] Username is displayed correctly in response
- [ ] Redirect servlet forwards to Google with correct search query
- [ ] Special characters in search query are handled properly
- [ ] Empty search query redirects back to form

## 📄 License

This project is for educational purposes.

## 👤 Author

Created for Servlet Assignments Practice

---

**Happy Coding! 🚀**
