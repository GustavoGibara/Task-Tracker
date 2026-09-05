package repository;

import java.util.List;

import entities.Task;
import enums.Status;

public interface TaskRepository {
    List<Task> findAll();
    List<Task> findByStatus(Status status);
    Task findById(Long id);
    void add(Task task);
    void remove(Long id);
    void update(Task task);
}
