package test.repository;

import repository.TaskRepository;

public class updateTaskTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();

        taskRepository.updateTask(3L, "COCO");
    }
}
