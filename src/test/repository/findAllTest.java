package test.repository;

import repository.TaskRepository;

public class findAllTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();

        taskRepository.findAllTasks().forEach(System.out::println);
    }
}
