package test.service;

import repository.TaskRepositoryJson;
import service.TaskService;
import service.TaskServiceJson;

public class updateDescription {
    public static void main(String[] args) {
        TaskService taskservice = new TaskServiceJson(new TaskRepositoryJson());
        
        try {
            taskservice.updateDescription(3L, "Não sou perfeito.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
