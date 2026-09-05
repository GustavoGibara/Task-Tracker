package test.repository;

import repository.TaskRepository;
import repository.TaskRepositoryJson;

public class findTask {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepositoryJson();
        System.out.println(taskRepository.findById(11L));
    }
}
