package test.repository;

import entities.Task;
import enums.Status;
import repository.TaskRepository;

public class removeTaskTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();

        taskRepository.removeTask(new Task(8L, "Estudar", Status.TODO, null, null));
    }
}
