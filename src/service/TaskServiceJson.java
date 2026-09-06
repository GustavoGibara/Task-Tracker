package service;

import java.time.LocalDateTime;
import java.util.List;

import entities.Task;
import enums.Status;
import repository.TaskRepository;

public class TaskServiceJson implements TaskService{

    TaskRepository taskRepository;

    public TaskServiceJson(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = taskRepository.findAll();

        return tasks;
    }

    @Override
    public List<Task> findByStatus(String status) {

        Status statusFound = null;

        try {
            statusFound = Status.valueOf(status
                                            .toUpperCase()
                                            .replace("-", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status não é válido. Status válidos: todo, in-progress e done.");
        }
        
        
        List<Task> tasks = taskRepository.findByStatus(statusFound);

        return tasks;
        
    }

    @Override
    public Task findById(Long id) {
        if (id <= 0L) {
            throw new IllegalArgumentException("Id não pode se menor que 0. Procurar por Ids acima de 0");
        }

        Task task = taskRepository.findById(id);

        return task;
    }

    @Override
    public void add(String taskDescription) {
        if (taskDescription.equals(null)) {
            throw new NullPointerException("A tarefa não pode ser de um valor nulo.");
        }

        if (taskDescription.isBlank() || taskDescription.isEmpty()) {
            throw new IllegalArgumentException("O valor não pode ser vazio.");
        }

        Task task = new Task(null, taskDescription, Status.TODO, LocalDateTime.now(), LocalDateTime.now());

        taskRepository.add(task);
    }

    @Override
    public void remove(Long id) {
        if (id <= 0L) {
            throw new IllegalArgumentException("Id não pode se menor que 0. Procurar por Ids acima de 0");
        }

        taskRepository.remove(id);
    }

    @Override
    public void updateDescription(String description) {
        if (description.equals(null)) {
            throw new NullPointerException("A tarefa não pode ser de um valor nulo.");
        }
        if (description.isBlank() || description.isEmpty()) {
            throw new IllegalArgumentException("O valor não pode ser vazio.");
        }

        Task task = new Task(null, description, null, null, null);

        taskRepository.update(task);
    }

    @Override
    public void updateStatus(String status) {
        Status statusFound = null;

        try {
            statusFound = Status.valueOf(status
                                            .toUpperCase()
                                            .replace("-", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status não é válido. Status válidos: todo, in-progress e done.");
        }

        Task task = new Task(null, null, statusFound, null, null);

        taskRepository.update(task);
    }    
    
}
