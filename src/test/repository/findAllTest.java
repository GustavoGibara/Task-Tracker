package test.repository;

import repository.TaskRepository;
import repository.TaskRepositoryJson;

public class findAllTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepositoryJson();

        taskRepository.findAll().forEach(System.out::println);
    }
}
