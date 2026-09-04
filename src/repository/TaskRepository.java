package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public void removeTask(Task tas) {  
        if (tasks.contains(task)) {
            tasks.remove(task);
        } else {
            throw new IllegalArgumentException("Tarefa não existe na lista.");
        }
        JsonInteraction.saveTasks(tasks);
    }

    public void updateTask(Task task) {

    }


}
