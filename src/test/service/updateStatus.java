package test.service;

import repository.TaskRepositoryJson;
import service.TaskService;
import service.TaskServiceJson;

public class updateStatus {
    public static void main(String[] args) {
        TaskService taskservice = new TaskServiceJson(new TaskRepositoryJson());
        
        try {
            taskservice.updateStatus(8L, "done");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
