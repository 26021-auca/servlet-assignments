@echo off
echo ================================
echo Building Servlet Assignments
echo ================================
echo.

REM Check if Maven is available
where mvn >nul 2>nul
if %ERRORLEVEL% EQU 0 (
    echo Maven found! Building with Maven...
    call mvn clean package
    if %ERRORLEVEL% EQU 0 (
        echo.
        echo ================================
        echo Build Successful!
        echo ================================
        echo WAR file created at: target\servlet-assignments.war
        echo.
        echo Next steps:
        echo 1. Deploy the WAR file to Tomcat's webapps folder
        echo 2. Start Tomcat server
        echo 3. Access: http://localhost:8080/servlet-assignments/
        echo.
    ) else (
        echo.
        echo ================================
        echo Build Failed!
        echo ================================
        echo Check the error messages above.
    )
) else (
    echo Maven not found in PATH.
    echo.
    echo Please install Maven or compile manually:
    echo 1. Ensure JDK is installed
    echo 2. Set JAVA_HOME environment variable
    echo 3. Run: javac -cp "path\to\servlet-api.jar" src\main\java\com\servlets\*.java
)

echo.
pause
