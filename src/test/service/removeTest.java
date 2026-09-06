package test.service;

import repository.TaskRepositoryJson;
import service.TaskService;
import service.TaskServiceJson;

public class removeTest {
    public static void main(String[] args) {
        TaskService taskservice = new TaskServiceJson(new TaskRepositoryJson());
        
        try {
            taskservice.remove(0L);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
