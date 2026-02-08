package com.restapi.user.controller;

import com.restapi.user.model.ApiResponse;
import com.restapi.user.model.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller for User Profile Management
 * Handles all user profile-related API endpoints with custom response wrapper
 */
@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    // In-memory list to store user profiles
    private List<UserProfile> users = new ArrayList<>();

    // Constructor - Initialize with sample users
    public UserProfileController() {
        users.add(new UserProfile(1L, "john_doe", "john@example.com", "John Doe", 25, "USA", "Software developer", true));
        users.add(new UserProfile(2L, "jane_smith", "jane@example.com", "Jane Smith", 28, "UK", "Data scientist", true));
        users.add(new UserProfile(3L, "mike_wilson", "mike@example.com", "Mike Wilson", 32, "Canada", "Product manager", true));
        users.add(new UserProfile(4L, "sarah_jones", "sarah@example.com", "Sarah Jones", 22, "USA", "Student", false));
        users.add(new UserProfile(5L, "david_lee", "david@example.com", "David Lee", 35, "Australia", "Entrepreneur", true));
    }

    /**
     * GET /api/users - Get all user profiles
     * Returns: ApiResponse with list of all users
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(
            true,
            "Users retrieved successfully",
            users
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * GET /api/users/{userId} - Get user profile by ID
     * @param userId - User ID from URL path
     * Returns: ApiResponse with user profile if found, error message if not found
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                ApiResponse<UserProfile> response = new ApiResponse<>(
                    true,
                    "User found successfully",
                    user
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        
        ApiResponse<UserProfile> response = new ApiResponse<>(
            false,
            "User not found with ID: " + userId,
            null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * GET /api/users/username/{username} - Search user by username
     * @param username - Username from URL path
     * Returns: ApiResponse with user profile if found
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserByUsername(@PathVariable String username) {
        for (UserProfile user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                ApiResponse<UserProfile> response = new ApiResponse<>(
                    true,
                    "User found by username",
                    user
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        
        ApiResponse<UserProfile> response = new ApiResponse<>(
            false,
            "User not found with username: " + username,
            null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * GET /api/users/country/{country} - Search users by country
     * @param country - Country name from URL path
     * Returns: ApiResponse with list of users from that country
     */
    @GetMapping("/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getUsersByCountry(@PathVariable String country) {
        List<UserProfile> result = new ArrayList<>();
        
        for (UserProfile user : users) {
            if (user.getCountry().equalsIgnoreCase(country)) {
                result.add(user);
            }
        }
        
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(
            true,
            "Found " + result.size() + " users from " + country,
            result
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * GET /api/users/age-range?min={min}&max={max} - Search users by age range
     * @param min - Minimum age (query parameter)
     * @param max - Maximum age (query parameter)
     * Returns: ApiResponse with list of users within age range
     */
    @GetMapping("/age-range")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getUsersByAgeRange(
            @RequestParam int min,
            @RequestParam int max) {
        List<UserProfile> result = new ArrayList<>();
        
        for (UserProfile user : users) {
            if (user.getAge() >= min && user.getAge() <= max) {
                result.add(user);
            }
        }
        
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(
            true,
            "Found " + result.size() + " users between ages " + min + " and " + max,
            result
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * GET /api/users/active - Get only active user profiles
     * Returns: ApiResponse with list of active users
     */
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getActiveUsers() {
        List<UserProfile> result = new ArrayList<>();
        
        for (UserProfile user : users) {
            if (user.isActive()) {
                result.add(user);
            }
        }
        
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(
            true,
            "Found " + result.size() + " active users",
            result
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * POST /api/users - Create new user profile
     * @param userProfile - UserProfile object from request body
     * Returns: ApiResponse with created user profile
     */
    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUserProfile(@RequestBody UserProfile userProfile) {
        // Check if username already exists
        for (UserProfile user : users) {
            if (user.getUsername().equalsIgnoreCase(userProfile.getUsername())) {
                ApiResponse<UserProfile> response = new ApiResponse<>(
                    false,
                    "Username already exists",
                    null
                );
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
        }
        
        // Generate new ID
        Long maxId = 0L;
        for (UserProfile user : users) {
            if (user.getUserId() > maxId) {
                maxId = user.getUserId();
            }
        }
        userProfile.setUserId(maxId + 1);
        userProfile.setActive(true); // New users are active by default
        
        users.add(userProfile);
        
        ApiResponse<UserProfile> response = new ApiResponse<>(
            true,
            "User profile created successfully",
            userProfile
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * PUT /api/users/{userId} - Update user profile
     * @param userId - User ID to update
     * @param updatedProfile - Updated profile data from request body
     * Returns: ApiResponse with updated user profile
     */
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UserProfile updatedProfile) {
        
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                updatedProfile.setUserId(userId);
                users.set(i, updatedProfile);
                
                ApiResponse<UserProfile> response = new ApiResponse<>(
                    true,
                    "User profile updated successfully",
                    updatedProfile
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        
        ApiResponse<UserProfile> response = new ApiResponse<>(
            false,
            "User not found with ID: " + userId,
            null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * PATCH /api/users/{userId}/activate - Activate user profile
     * @param userId - User ID to activate
     * Returns: ApiResponse with activated user profile
     */
    @PatchMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(true);
                
                ApiResponse<UserProfile> response = new ApiResponse<>(
                    true,
                    "User profile activated successfully",
                    user
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        
        ApiResponse<UserProfile> response = new ApiResponse<>(
            false,
            "User not found with ID: " + userId,
            null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * PATCH /api/users/{userId}/deactivate - Deactivate user profile
     * @param userId - User ID to deactivate
     * Returns: ApiResponse with deactivated user profile
     */
    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(false);
                
                ApiResponse<UserProfile> response = new ApiResponse<>(
                    true,
                    "User profile deactivated successfully",
                    user
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        
        ApiResponse<UserProfile> response = new ApiResponse<>(
            false,
            "User not found with ID: " + userId,
            null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * DELETE /api/users/{userId} - Delete user profile
     * @param userId - User ID to delete
     * Returns: ApiResponse with success message
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUserProfile(@PathVariable Long userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                users.remove(i);
                
                ApiResponse<Void> response = new ApiResponse<>(
                    true,
                    "User profile deleted successfully",
                    null
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        
        ApiResponse<Void> response = new ApiResponse<>(
            false,
            "User not found with ID: " + userId,
            null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
