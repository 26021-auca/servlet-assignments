package com.restapi.restaurant.controller;

import com.restapi.restaurant.model.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller for Restaurant Menu Management
 * Handles all menu item-related API endpoints
 */
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    // In-memory list to store menu items
    private List<MenuItem> menuItems = new ArrayList<>();

    // Constructor - Initialize with 8 sample menu items
    public MenuController() {
        menuItems.add(new MenuItem(1L, "Spring Rolls", "Crispy vegetable rolls", 5.99, "Appetizer", true));
        menuItems.add(new MenuItem(2L, "Caesar Salad", "Fresh romaine with Caesar dressing", 7.99, "Appetizer", true));
        menuItems.add(new MenuItem(3L, "Grilled Chicken", "Herb-marinated chicken breast", 15.99, "Main Course", true));
        menuItems.add(new MenuItem(4L, "Beef Steak", "Premium ribeye steak", 24.99, "Main Course", true));
        menuItems.add(new MenuItem(5L, "Pasta Carbonara", "Creamy pasta with bacon", 12.99, "Main Course", false));
        menuItems.add(new MenuItem(6L, "Chocolate Cake", "Rich chocolate layer cake", 6.99, "Dessert", true));
        menuItems.add(new MenuItem(7L, "Ice Cream", "Vanilla bean ice cream", 4.99, "Dessert", true));
        menuItems.add(new MenuItem(8L, "Fresh Lemonade", "Homemade lemonade", 3.99, "Beverage", true));
    }

    /**
     * GET /api/menu - Get all menu items
     * Returns: List of all menu items with HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    /**
     * GET /api/menu/{id} - Get specific menu item by ID
     * @param id - Menu item ID from URL path
     * Returns: MenuItem if found (200), or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * GET /api/menu/category/{category} - Get items by category
     * @param category - Category name from URL path
     * Returns: List of menu items in that category
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> result = new ArrayList<>();
        
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET /api/menu/available - Get only available items
     * @param available - Availability filter (query parameter)
     * Returns: List of available menu items
     */
    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableMenuItems(@RequestParam boolean available) {
        List<MenuItem> result = new ArrayList<>();
        
        for (MenuItem item : menuItems) {
            if (item.isAvailable() == available) {
                result.add(item);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET /api/menu/search?name={name} - Search menu items by name
     * @param name - Search keyword for item name (query parameter)
     * Returns: List of menu items matching the name
     */
    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuItemsByName(@RequestParam String name) {
        List<MenuItem> result = new ArrayList<>();
        
        for (MenuItem item : menuItems) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * POST /api/menu - Add new menu item
     * @param menuItem - MenuItem object from request body
     * Returns: Created menu item with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        // Generate new ID
        Long maxId = 0L;
        for (MenuItem item : menuItems) {
            if (item.getId() > maxId) {
                maxId = item.getId();
            }
        }
        menuItem.setId(maxId + 1);
        
        menuItems.add(menuItem);
        return new ResponseEntity<>(menuItem, HttpStatus.CREATED);
    }

    /**
     * PUT /api/menu/{id}/availability - Toggle item availability
     * @param id - Menu item ID to update
     * Returns: Updated menu item (200) or 404 if not found
     */
    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                // Toggle availability
                item.setAvailable(!item.isAvailable());
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * DELETE /api/menu/{id} - Remove menu item
     * @param id - Menu item ID to delete
     * Returns: HTTP 204 if deleted successfully, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getId().equals(id)) {
                menuItems.remove(i);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
