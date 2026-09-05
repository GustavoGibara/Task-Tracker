package repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.Task;
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
        return JsonInteraction.readTasks();
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
        
        Iterator<Task> tasksIterator = tasks.iterator();

        while(tasksIterator.hasNext()) {
            if (tasksIterator.next().getId() == id) {
                tasksIterator.remove();
            } 
        }

        JsonInteraction.saveTasks(tasks);
    }

    public void updateTask(Long id, String description) {

        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(description);
                task.setUpdatedAt(LocalDateTime.now());
            }
        }

        JsonInteraction.saveTasks(tasks);
    }

}
