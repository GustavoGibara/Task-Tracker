package test.repository;

import entities.Task;
import enums.Status;
import repository.TaskRepository;
import repository.TaskRepositoryJson;

public class updateTaskTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepositoryJson();

        taskRepository.update(new Task(4L, null, Status.DONE, null, null));
        taskRepository.update(new Task(4L, "teste", Status.DONE, null, null));
        taskRepository.update(new Task(10L, "", Status.DONE, null, null));

    }
}
