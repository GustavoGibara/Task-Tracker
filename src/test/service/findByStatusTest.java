package test.service;

import repository.TaskRepositoryJson;
import service.TaskService;
import service.TaskServiceJson;

public class findByStatusTest {

    public static void main(String[] args) {
        TaskService taskservice = new TaskServiceJson(new TaskRepositoryJson());

        taskservice.findByStatus("todo1").forEach(System.out::println);
    }
}
