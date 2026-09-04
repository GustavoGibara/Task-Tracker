package repository;

import java.util.List;

import entities.Task;
import util.jsonInteraction;

public class TaskRepository {

    public TaskRepository() {
        jsonInteraction.createJson();
    }
    
    public List<Task> findAllTasks() {
        return jsonInteraction.readTasks();
    }

    public void addTask(Task task) {
        List<Task> tasks = jsonInteraction.readTasks();
        if (!tasks.isEmpty()) {
            task.setId(tasks.getLast().getId() + 1L);
        } else {
            task.setId(1L);
        }
        tasks.add(task);
        jsonInteraction.saveTasks(tasks);
    }

    public void removeTask(Task task) {
        List<Task> tasks = jsonInteraction.readTasks();
        
    }
}
