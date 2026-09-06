package test.service;

import repository.TaskRepositoryJson;
import service.TaskService;
import service.TaskServiceJson;

public class findById {
    public static void main(String[] args) {
        TaskService taskService = new TaskServiceJson(new TaskRepositoryJson());

        System.out.println(taskService.findById(5L));


        try {
            System.out.println(taskService.findById(0L));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(taskService.findById(50L));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
