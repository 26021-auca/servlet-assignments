package com.restapi.task.controller;

import com.restapi.task.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller for Task Management (To-Do List)
 * Handles all task-related API endpoints
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    // In-memory list to store tasks
    private List<Task> tasks = new ArrayList<>();

    // Constructor - Initialize with sample tasks
    public TaskController() {
        tasks.add(new Task(1L, "Complete Spring Boot Assignment", "Finish all 5 questions", false, "HIGH", "2026-02-10"));
        tasks.add(new Task(2L, "Study for Java Exam", "Review chapters 1-5", false, "MEDIUM", "2026-02-15"));
        tasks.add(new Task(3L, "Buy groceries", "Weekly shopping", false, "LOW", "2026-02-05"));
        tasks.add(new Task(4L, "Update resume", "Add latest projects", true, "MEDIUM", "2026-02-01"));
        tasks.add(new Task(5L, "Gym workout", "Cardio and strength training", false, "LOW", "2026-02-04"));
    }

    /**
     * GET /api/tasks - Get all tasks
     * Returns: List of all tasks with HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    /**
     * GET /api/tasks/{taskId} - Get task by ID
     * @param taskId - Task ID from URL path
     * Returns: Task object if found (200), or 404 if not found
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                return new ResponseEntity<>(task, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * GET /api/tasks/status?completed={true/false} - Get tasks by completion status
     * @param completed - Completion status (query parameter)
     * Returns: List of tasks with matching completion status
     */
    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam boolean completed) {
        List<Task> result = new ArrayList<>();
        
        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                result.add(task);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET /api/tasks/priority/{priority} - Get tasks by priority
     * @param priority - Priority level (LOW, MEDIUM, HIGH) from URL path
     * Returns: List of tasks with matching priority
     */
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();
        
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                result.add(task);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * POST /api/tasks - Create new task
     * @param task - Task object from request body
     * Returns: Created task with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        // Generate new ID
        Long maxId = 0L;
        for (Task t : tasks) {
            if (t.getTaskId() > maxId) {
                maxId = t.getTaskId();
            }
        }
        task.setTaskId(maxId + 1);
        
        tasks.add(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    /**
     * PUT /api/tasks/{taskId} - Update task
     * @param taskId - Task ID to update
     * @param updatedTask - Updated task data from request body
     * Returns: Updated task (200) or 404 if not found
     */
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long taskId,
            @RequestBody Task updatedTask) {
        
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId().equals(taskId)) {
                updatedTask.setTaskId(taskId);
                tasks.set(i, updatedTask);
                return new ResponseEntity<>(updatedTask, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * PATCH /api/tasks/{taskId}/complete - Mark task as completed
     * @param taskId - Task ID to mark as completed
     * Returns: Updated task (200) or 404 if not found
     */
    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setCompleted(true);
                return new ResponseEntity<>(task, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * DELETE /api/tasks/{taskId} - Delete task
     * @param taskId - Task ID to delete
     * Returns: HTTP 204 if deleted successfully, 404 if not found
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId().equals(taskId)) {
                tasks.remove(i);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
