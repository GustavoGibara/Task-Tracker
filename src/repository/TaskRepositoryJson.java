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

public class TaskRepositoryJson implements TaskRepository{

    private List<Task> tasks = new ArrayList<>(); 
    
    public TaskRepositoryJson() {
        JsonInteraction.createJson();
        tasks = JsonInteraction.readTasks();
    }
    
    @Override
    public Task findById(Long id) {
        Task task = tasks.stream()
            .filter(t -> t.getId().equals(id))
            .findFirst()
            .orElseThrow();

        return task;
    }
    
    @Override
    public List<Task> findAll() {
        return tasks;
    }
    
    @Override
    public void add(Task task) {
        if (!tasks.isEmpty()) {
            task.setId(tasks.getLast().getId() + 1L);
        } else {
            task.setId(1L);
        }
        tasks.add(task);
        JsonInteraction.saveTasks(tasks);
    }

    @Override
    public void remove(Long id) {  
        
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

    @Override
    public void update(Task task) {

        boolean found = false;

        for (Task t : tasks) {
            if (t.getId() == task.getId()) {
                if (!(task.getDescription() == null)) {
                    if (task.getDescription().isEmpty() || task.getDescription().isBlank()) {
                        throw new IllegalArgumentException();
                    } else {
                        t.setDescription(task.getDescription());
                    }
                }
                if (!(task.getStatus() == null)) {
                    t.setStatus(task.getStatus());
                }
                t.setUpdatedAt(LocalDateTime.now());
                found = true;
            }
        }

        if (!found) {
            throw new NoSuchElementException("ID não encontrado.");
        }

        JsonInteraction.saveTasks(tasks);
    }

    @Override
    public List<Task> findByStatus(Status status) {
        List<Task> tasksByStatus = tasks.stream()
                                .filter(t -> t.getStatus().equals(status))
                                .collect(Collectors.toList());

        return tasksByStatus;
    }
}
