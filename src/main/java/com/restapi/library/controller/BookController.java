package com.restapi.library.controller;

import com.restapi.library.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller for Library Book Management
 * Handles all book-related API endpoints
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    // In-memory list to store books (simulating a database)
    private List<Book> books = new ArrayList<>();

    // Constructor - Initialize with 3 sample books
    public BookController() {
        books.add(new Book(1L, "Clean Code", "Robert Martin", "978-0132350884", 2008));
        books.add(new Book(2L, "Effective Java", "Joshua Bloch", "978-0134685991", 2017));
        books.add(new Book(3L, "Spring in Action", "Craig Walls", "978-1617294945", 2018));
    }

    /**
     * GET /api/books - Get all books
     * Returns: List of all books with HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * GET /api/books/{id} - Get a specific book by ID
     * @param id - Book ID from URL path
     * Returns: Book object if found (200), or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        // Search for book with matching ID
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }
        // Book not found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * GET /api/books/search?title={title} - Search books by title
     * @param title - Search keyword for book title (query parameter)
     * Returns: List of books matching the title
     */
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        List<Book> result = new ArrayList<>();
        
        // Search for books containing the title (case-insensitive)
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * POST /api/books - Add a new book
     * @param book - Book object from request body
     * Returns: Created book with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        // Generate new ID (max ID + 1)
        Long maxId = 0L;
        for (Book b : books) {
            if (b.getId() > maxId) {
                maxId = b.getId();
            }
        }
        book.setId(maxId + 1);
        
        // Add book to the list
        books.add(book);
        
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    /**
     * DELETE /api/books/{id} - Delete a book by ID
     * @param id - Book ID to delete
     * Returns: HTTP 204 if deleted successfully, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        // Find and remove the book
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                books.remove(i);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        
        // Book not found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
