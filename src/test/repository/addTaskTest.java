package test.repository;
import java.time.LocalDateTime;

import entities.Task;
import enums.Status;
import repository.TaskRepository;
import repository.TaskRepositoryJson;

public class addTaskTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepositoryJson();

        taskRepository.add(new Task(null, "Olá", Status.IN_PROGRESS, LocalDateTime.now(), LocalDateTime.now()));
        taskRepository.add(new Task(null, "Olá mundo", Status.IN_PROGRESS, LocalDateTime.now(), LocalDateTime.now()));

    }
}
