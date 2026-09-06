package cli;

import java.util.ArrayList;
import java.util.List;

import entities.Task;
import repository.TaskRepositoryJson;
import service.TaskService;
import service.TaskServiceJson;

public class task_cli {
    public static void main(String[] args) {
        
        TaskService taskService = new TaskServiceJson(new TaskRepositoryJson());

        String comando = args[0].toLowerCase();

        switch (comando) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Erro: nenhuma tarefa informada!");
                    break;
                }

                try {
                    taskService.add(args[1]);
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;

            case "list":

                List<Task> tasks = new ArrayList<>();

                try {

                    if (args.length > 1) {
                        tasks =  taskService.findByStatus(args[1]);
                    } else {
                        tasks = taskService.findAll();
                    }

                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }

                if (tasks.isEmpty()) {
                    System.out.println("Nenhuma tarefa registrada!");
                    break;
                }

                for (Task task : tasks) {
                    System.out.println(task);
                    System.out.println("-".repeat(20));
                }

                break;

            case "remove":

                if (args.length < 2) {
                    System.out.println("Erro: nenhuma tarefa informada!");
                    break;
                }

                try {
                    taskService.remove(Long.parseLong(args[1]));
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                

                break;

            case "update":

                if (args.length < 2) {
                    System.out.println("Erro: nenhum id informado!");
                    break;
                } else if (args.length < 3) {
                    System.out.println("Erro: nenhuma tarefa informada!");
                    break;
                } 

                try {
                    taskService.updateDescription(Long.parseLong(args[1]), args[2]);
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }

                break;

            case "mark-in-progress":

                if (args.length < 2) {
                    System.out.println("Erro: nenhum id informado!");
                    break;
                }

                try {
                    taskService.updateStatus(Long.parseLong(args[1]), "in-progress");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }

                break;

            case "mark-done":

                if (args.length < 2) {
                    System.out.println("Erro: nenhum id informado!");
                    break;
                }

                try {
                    taskService.updateStatus(Long.parseLong(args[1]), "done");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }

                break;
        
            default:
                break;
        }

    }
}
