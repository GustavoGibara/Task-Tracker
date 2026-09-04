package test.repository;

import repository.TaskRepository;

public class findTask {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();
        System.out.println(taskRepository.findTask(10L));
    }
}
