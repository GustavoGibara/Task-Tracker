package test.repository;

import enums.Status;
import repository.TaskRepository;
import repository.TaskRepositoryJson;

public class findByStatusTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepositoryJson();

        taskRepository.findByStatus(Status.TODO).forEach(System.out::println);
    }
}
