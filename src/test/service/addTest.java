package test.service;

import repository.TaskRepositoryJson;
import service.TaskService;
import service.TaskServiceJson;

public class addTest {
    public static void main(String[] args) {
            TaskService taskService = new TaskServiceJson(new TaskRepositoryJson());

            taskService.add("Cuzinho de Macho");
            
            try {
                taskService.add("");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
    }
}
