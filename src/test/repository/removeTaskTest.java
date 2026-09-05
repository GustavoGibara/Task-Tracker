package test.repository;

import repository.TaskRepository;
import repository.TaskRepositoryJson;

public class removeTaskTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepositoryJson();

        taskRepository.remove(1L);
    }
}
