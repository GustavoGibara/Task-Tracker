package test.repository;
import java.time.LocalDateTime;

import entities.Task;
import enums.Status;
import repository.TaskRepository;

public class addTaskTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();

        taskRepository.addTask(new Task(null, "Olá", Status.IN_PROGRESS, LocalDateTime.now(), LocalDateTime.now()));
        taskRepository.addTask(new Task(null, "Olá mundo", Status.IN_PROGRESS, LocalDateTime.now(), LocalDateTime.now()));

    }
}
