package com.restapi.ecommerce.controller;

import com.restapi.ecommerce.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    // In-memory list to store products
    private List<Product> products = new ArrayList<>();

    // Constructor - Initialize with 10 sample products
    public ProductController() {
products.add(new Product(1L, "iPhone 14", "Latest Apple smartphone", 999.99, "Electronics", 50, "Apple"));
        products.add(new Product(2L, "Samsung Galaxy S23", "Android flagship phone", 899.99, "Electronics", 30, "Samsung"));
        products.add(new Product(3L, "MacBook Pro", "Professional laptop", 1999.99, "Electronics", 20, "Apple"));
        products.add(new Product(4L, "Dell XPS 15", "High-performance laptop", 1499.99, "Electronics", 15, "Dell"));
        products.add(new Product(5L, "Sony WH-1000XM5", "Noise-canceling headphones", 399.99, "Audio", 40, "Sony"));
        products.add(new Product(6L, "Nike Air Max", "Running shoes", 129.99, "Footwear", 100, "Nike"));
        products.add(new Product(7L, "Adidas Ultraboost", "Comfortable running shoes", 149.99, "Footwear", 80, "Adidas"));
        products.add(new Product(8L, "Levi's Jeans", "Classic denim jeans", 59.99, "Clothing", 200, "Levi's"));
        products.add(new Product(9L, "Canon EOS R5", "Mirrorless camera", 3899.99, "Electronics", 5, "Canon"));
        products.add(new Product(10L, "Coffee Maker", "Automatic coffee machine", 79.99, "Appliances", 0, "Breville"));
    }

    /**
     * GET /api/products - Get all products with optional pagination
     * @param page - Page number (optional, default 0)
     * @param limit - Items per page (optional, default 10)
     * Returns: List of products for the requested page
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int limit) {
        
        // Calculate pagination
        int startIndex = page * limit;
        int endIndex = Math.min(startIndex + limit, products.size());
        
        // Return empty list if page is out of bounds
        if (startIndex >= products.size()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        
        List<Product> paginatedProducts = products.subList(startIndex, endIndex);
        return new ResponseEntity<>(paginatedProducts, HttpStatus.OK);
    }

    /**
     * GET /api/products/{productId} - Get product details
     * @param productId - Product ID from URL path
     * Returns: Product object if found (200), or 404 if not found
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * GET /api/products/category/{category} - Get products by category
     * @param category - Category name from URL path
     * Returns: List of products in that category
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET /api/products/brand/{brand} - Get products by brand
     * @param brand - Brand name from URL path
     * Returns: List of products from that brand
     */
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                result.add(product);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET /api/products/search?keyword={keyword} - Search products by keyword
     * @param keyword - Search keyword (query parameter)
     * Returns: List of products matching keyword in name or description
     */
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                product.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(product);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET /api/products/price-range?min={min}&max={max} - Get products within price range
     * @param min - Minimum price (query parameter)
     * @param max - Maximum price (query parameter)
     * Returns: List of products within the price range
     */
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        List<Product> result = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                result.add(product);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET /api/products/in-stock - Get products with stock available
     * Returns: List of products with stockQuantity > 0
     */
    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        List<Product> result = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getStockQuantity() > 0) {
                result.add(product);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * POST /api/products - Add new product
     * @param product - Product object from request body
     * Returns: Created product with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        // Generate new ID
        Long maxId = 0L;
        for (Product p : products) {
            if (p.getProductId() > maxId) {
                maxId = p.getProductId();
            }
        }
        product.setProductId(maxId + 1);
        
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /**
     * PUT /api/products/{productId} - Update product details
     * @param productId - Product ID to update
     * @param updatedProduct - Updated product data from request body
     * Returns: Updated product (200) or 404 if not found
     */
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long productId,
            @RequestBody Product updatedProduct) {
        
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                updatedProduct.setProductId(productId);
                products.set(i, updatedProduct);
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * PATCH /api/products/{productId}/stock?quantity={quantity} - Update stock quantity
     * @param productId - Product ID to update
     * @param quantity - New stock quantity (query parameter)
     * Returns: Updated product (200) or 404 if not found
     */
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(
            @PathVariable Long productId,
            @RequestParam int quantity) {
        
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setStockQuantity(quantity);
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * DELETE /api/products/{productId} - Delete product
     * @param productId - Product ID to delete
     * Returns: HTTP 204 if deleted successfully, 404 if not found
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                products.remove(i);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
