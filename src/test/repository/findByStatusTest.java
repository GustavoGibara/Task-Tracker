package test.repository;

import enums.Status;
import repository.TaskRepository;

public class findByStatusTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();

        taskRepository.findByStatus(Status.TODO).forEach(System.out::println);
    }
}
