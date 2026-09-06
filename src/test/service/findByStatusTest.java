package test.service;

import repository.TaskRepositoryJson;
import service.TaskService;
import service.TaskServiceJson;

public class findByStatusTest {

    public static void main(String[] args) {
        TaskService taskservice = new TaskServiceJson(new TaskRepositoryJson());


        try {
            taskservice.findByStatus("in-progress").forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
