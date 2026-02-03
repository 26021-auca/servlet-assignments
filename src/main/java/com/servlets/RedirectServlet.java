package com.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RedirectServlet")
public class RedirectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Retrieve the search query from the request
        String searchQuery = request.getParameter("searchQuery");
        
        // Check if search query is not empty
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            // Encode the search query to make it URL-safe
            String encodedQuery = URLEncoder.encode(searchQuery, "UTF-8");
            
            // Create the Google search URL
            String googleSearchUrl = "https://www.google.com/search?q=" + encodedQuery;
            
            // Use sendRedirect to redirect the user to Google
            response.sendRedirect(googleSearchUrl);
        } else {
            // If search query is empty, redirect back to the form
            response.sendRedirect("redirect.html");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // If someone tries to access this servlet via GET, redirect to the form
        response.sendRedirect("redirect.html");
    }
}
