package service;

import java.util.List;

import entities.Task;

public interface TaskService {

    List<Task> findAll();
    List<Task> findByStatus(String status);
    Task findById(Long id);
    void add(String taskDescription);
    void remove(Long id);
    void updateDescription(Long id, String description);
    void updateStatus(Long id, String status);
}
