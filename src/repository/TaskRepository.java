package repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entities.Task;
import enums.Status;

public class TaskRepository {

    private final Path path = Paths.get("tasks.json");

    private List<Task> tasks = new ArrayList<>(List.of(
        new Task(1L, "Correr", Status.DONE, null, null),
        new Task(2L, "Estudar", Status.TODO, null, null)
    ));

    public TaskRepository() {
        if (Files.notExists(path)) {
            createJson();
        }

        saveTasks(tasks);
    }



    private void createJson() {
        try {
            Files.writeString(path, "[]");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String buildTask(Task task) { 
        String taskJson = "\t{\n" 
                            + "\t\t\"id\": " + task.getId() + ",\n"
                            + "\t\t\"description\": \"" + task.getDescription() + "\",\n"
                            + "\t\t\"status\": \"" + task.getStatus().name() + "\",\n"
                            + "\t\t\"createdAt\": \"" + task.getCreatedAt() + "\",\n"
                            + "\t\t\"updatedAt\": \"" + task.getUpdatedAt() + "\"\n"
                            + "\t}";

        return taskJson;
    }

    private void saveTasks(List<Task> tasks) {

        try(BufferedWriter bw = Files.newBufferedWriter(path)) {
            bw.write("[\n");
            List<String> jsonTasks = tasks.stream()
                .map(t -> buildTask(t))
                .collect(Collectors.toList());

            for (int i = 0; i < jsonTasks.size(); i++) {
                bw.write(jsonTasks.get(i));
                if (!(i == jsonTasks.size() - 1)) {
                    bw.write(",\n");
                }
            }
            bw.write("\n]");


        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
