package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Retrieve parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Generate HTML response
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Login Result</title>");
        out.println("<style>");
        out.println("* { margin: 0; padding: 0; box-sizing: border-box; }");
        out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; "
                + "background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); "
                + "display: flex; justify-content: center; align-items: center; "
                + "min-height: 100vh; padding: 20px; }");
        out.println(".result-container { background: white; padding: 40px; "
                + "border-radius: 10px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2); "
                + "text-align: center; max-width: 500px; width: 100%; }");
        out.println("h2 { color: #333; margin-bottom: 20px; font-size: 24px; }");
        out.println(".message { font-size: 18px; color: #555; margin-bottom: 30px; line-height: 1.6; }");
        out.println(".weak { color: #e74c3c; font-weight: 600; }");
        out.println(".success { color: #27ae60; font-weight: 600; }");
        out.println("a { display: inline-block; padding: 12px 30px; "
                + "background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); "
                + "color: white; text-decoration: none; border-radius: 5px; "
                + "font-weight: 600; transition: transform 0.2s; }");
        out.println("a:hover { transform: translateY(-2px); }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='result-container'>");
        
        // Check password length and display appropriate message
        if (password != null && password.length() < 8) {
            out.println("<h2 class='weak'>Weak Password!</h2>");
            out.println("<p class='message'>Hello <strong>" + escapeHtml(username) 
                    + "</strong>, your password is weak. Try a strong one.</p>");
        } else {
            out.println("<h2 class='success'>Login Successful!</h2>");
            out.println("<p class='message'>Welcome <strong>" + escapeHtml(username) + "</strong></p>");
        }
        
        out.println("<a href='login.html'>Back to Login</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
    
    // Helper method to prevent XSS attacks
    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#x27;");
    }
}
