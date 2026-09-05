package repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import entities.Task;
import enums.Status;
import util.JsonInteraction;

public class TaskRepository {

    private List<Task> tasks = new ArrayList<>(); 

    public TaskRepository() {
        JsonInteraction.createJson();
        tasks = JsonInteraction.readTasks();
    }
    
    public Task findTask(Long id) {
        Task task = tasks.stream()
            .filter(t -> t.getId().equals(id))
            .findFirst()
            .orElseThrow();

        return task;
    }

    public List<Task> findAllTasks() {
        return tasks;
    }
    
    public void addTask(Task task) {
        if (!tasks.isEmpty()) {
            task.setId(tasks.getLast().getId() + 1L);
        } else {
            task.setId(1L);
        }
        tasks.add(task);
        JsonInteraction.saveTasks(tasks);
    }

    public void removeTask(Long id) {  
        
        boolean found = false;

        Iterator<Task> tasksIterator = tasks.iterator();

        while(tasksIterator.hasNext()) {
            if (tasksIterator.next().getId() == id) {
                tasksIterator.remove();
                found = true;
            } 
        }

        if (!found) {
            throw new NoSuchElementException("ID não encontrado.");
        }

        JsonInteraction.saveTasks(tasks);
    }

    public void updateTask(Long id, String description) {

        boolean found = false;

        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(description);
                task.setUpdatedAt(LocalDateTime.now());
                found = true;
            }
        }

        if (!found) {
            throw new NoSuchElementException("ID não encontrado.");
        }

        JsonInteraction.saveTasks(tasks);
    }

    public List<Task> findByStatus(Status status) {
        List<Task> tasksByStatus = tasks.stream()
                                .filter(t -> t.getStatus().equals(status))
                                .collect(Collectors.toList());

        return tasksByStatus;
    }

}
