# VS Code Quick Start Guide

## 🎯 Setting Up Your Servlet Project in VS Code

### Step 1: Install Required Software

1. **Install JDK**:
   - Download JDK 8 or higher from Oracle or use OpenJDK
   - Set JAVA_HOME environment variable
   - Verify: `java -version`

2. **Install Apache Tomcat**:
   - Download from https://tomcat.apache.org/download-90.cgi
   - Extract to a folder (e.g., `C:\apache-tomcat-9.0.x`)

3. **Install Maven** (recommended):
   - Download from https://maven.apache.org/download.cgi
   - Add to PATH
   - Verify: `mvn -version`

### Step 2: Install VS Code Extensions

Open VS Code and install these extensions:

1. **Extension Pack for Java** (by Microsoft)
   - Press `Ctrl+Shift+X`
   - Search for "Extension Pack for Java"
   - Click Install

2. **Tomcat for Java** (by Wei Shen)
   - Search for "Tomcat for Java"
   - Click Install

3. **Maven for Java** (by Microsoft) - if using Maven
   - Should be included in Extension Pack for Java

### Step 3: Configure Tomcat in VS Code

1. Press `Ctrl+Shift+P` (Windows/Linux) or `Cmd+Shift+P` (Mac)
2. Type "Tomcat: Add Tomcat Server"
3. Browse to your Tomcat installation directory
4. The server will appear in the "TOMCAT SERVERS" section in the Explorer

### Step 4: Open the Project

1. In VS Code: `File` → `Open Folder`
2. Select the `servlet-assignments` folder
3. Wait for VS Code to load the Java project

### Step 5: Build the Project

**Option A: Using Maven (Recommended)**

Open terminal in VS Code (`Ctrl+` ` or View → Terminal):

```bash
mvn clean package
```

This creates: `target/servlet-assignments.war`

**Option B: Using VS Code Java Tools**

1. Open Command Palette: `Ctrl+Shift+P`
2. Type "Java: Export to JAR..."
3. Follow the prompts

### Step 6: Deploy to Tomcat

**Method 1: Using VS Code Tomcat Extension**

1. In Explorer, expand "TOMCAT SERVERS"
2. Right-click on your Tomcat server
3. Select "Add Web App to Server"
4. Choose the WAR file: `target/servlet-assignments.war`

**Method 2: Manual Deployment**

1. Copy `target/servlet-assignments.war` to Tomcat's `webapps` folder
2. Right-click Tomcat server in VS Code
3. Select "Start"

### Step 7: Run and Test

1. **Start Tomcat**:
   - In VS Code, right-click your Tomcat server
   - Click "Start"
   - Wait for startup to complete

2. **Access Your Application**:
   - Home: http://localhost:8080/servlet-assignments/   
   
     but it the same as login
   - Login: http://localhost:8080/servlet-assignments/login.html
   - Redirect: http://localhost:8080/servlet-assignments/redirect.html

### Step 8: Development Workflow

**Making Changes:**

1. Edit your Java/HTML files in VS Code
2. Save changes
3. Rebuild:
   ```bash
   mvn clean package
   ```
4. In VS Code Tomcat panel:
   - Right-click your app
   - Select "Reload"
   - Or stop and restart Tomcat

**Viewing Logs:**

1. Right-click Tomcat server in VS Code
2. Select "Open Server Log"
3. Or check: `[tomcat-dir]/logs/catalina.out`

## 🔧 Troubleshooting

### Problem: "Cannot find Java runtime"

**Solution:**
1. Install JDK 8 or higher
2. Set JAVA_HOME:
   - Windows: System Properties → Environment Variables
   - Linux/Mac: Add to `~/.bashrc`: `export JAVA_HOME=/path/to/jdk`

### Problem: "Port 8080 already in use"

**Solution:**
1. Find and stop the process using port 8080, OR
2. Change Tomcat port:
   - Edit `[tomcat-dir]/conf/server.xml`
   - Find `<Connector port="8080"...`
   - Change to `<Connector port="8081"...`

### Problem: "404 - Not Found"

**Solution:**
1. Check WAR file deployed correctly in `webapps`
2. Verify Tomcat started without errors
3. Check URL matches context path
4. Look at Tomcat logs for errors

### Problem: Servlets not compiling

**Solution:**
1. Ensure servlet-api.jar is in classpath
2. In VS Code, check Java project configuration
3. Run: `mvn clean install` to rebuild

### Problem: Changes not reflecting

**Solution:**
1. Rebuild the project: `mvn clean package`
2. Reload the web app in Tomcat
3. Clear browser cache
4. Try hard refresh: `Ctrl+F5`

## 🎨 VS Code Tips

### Useful Shortcuts:

- `Ctrl+Shift+P`: Command Palette
- `Ctrl+` `: Toggle Terminal
- `Ctrl+B`: Toggle Sidebar
- `Ctrl+Shift+F`: Search across files
- `F5`: Debug
- `Ctrl+Space`: Intellisense

### Recommended Settings:

1. **Auto-save**: File → Auto Save
2. **Format on Save**: 
   - Settings → Search "format on save"
   - Check the box

### File Watching:

VS Code automatically watches for file changes. To see live updates:
1. Install "Live Server" extension
2. Right-click HTML file → "Open with Live Server"

## 📝 Quick Reference

### Project Structure:
```
servlet-assignments/
├── src/main/
│   ├── java/com/servlets/     ← Your servlet code
│   └── webapp/                 ← HTML files, web.xml
├── target/                     ← Build output
└── pom.xml                     ← Maven config
```

### Maven Commands:
```bash
mvn clean          # Clean build directory
mvn compile        # Compile Java files
mvn package        # Create WAR file
mvn clean package  # Clean + compile + package
```

### Access URLs:
```
Home:     http://localhost:8080/servlet-assignments/
Login:    http://localhost:8080/servlet-assignments/login.html
Redirect: http://localhost:8080/servlet-assignments/redirect.html
```

## ✅ Success Checklist

- [ ] JDK installed and JAVA_HOME set
- [ ] Tomcat installed and configured in VS Code
- [ ] Maven installed (optional)
- [ ] VS Code extensions installed
- [ ] Project opens without errors
- [ ] Build completes successfully
- [ ] WAR file created in target/
- [ ] Tomcat starts without errors
- [ ] Application accessible in browser
- [ ] Both servlets working correctly

## 🆘 Need Help?

1. Check Tomcat logs in VS Code or in `logs/catalina.out`
2. Verify Java version compatibility
3. Ensure all files are saved before building
4. Try a clean build: `mvn clean package`
5. Restart VS Code and Tomcat

---

**Happy Coding! 🚀**
