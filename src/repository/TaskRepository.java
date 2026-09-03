package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import entities.Task;

public class TaskRepository {

    private final Path path = Paths.get("tasks.json");

    public TaskRepository() {
        if (Files.notExists(path)) {
            createJson();
        }
    }
    
    //public List<Task> findAllTasks() {}

    private void createJson() {
        try {
            Files.writeString(path, "[]");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String buildTaskToJson(Task task) { 

        String taskJson = "\t{\n" 
                            + "\t\t\"id\": " + task.getId() + ",\n"
                            + "\t\t\"description\": \"" + task.getDescription() + "\",\n"
                            + "\t\t\"status\": \"" + task.getStatus().name() + "\",\n"
                            + "\t\t\"createdAt\": \"" + task.getCreatedAt() + "\",\n"
                            + "\t\t\"updatedAt\": \"" + task.getUpdatedAt() + "\"\n"
                            + "\t}";

        return taskJson;
    }

    public Task buildJsonToTask(String taskJson) {
        
        String[] lines = taskJson.split("\n");

        Pattern pattern = Pattern.compile("(?<=\\:\\s)[\\s\\S]+(?=,|$)");
        
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            
            while(matcher.find()) {
                System.out.println(matcher.group());
            }
        }
        
        return null;
    }

    public void saveTasks(List<Task> tasks) {

        try(BufferedWriter bw = Files.newBufferedWriter(path)) {
            bw.write("[\n");
            
            List<String> jsonTasks = tasks.stream()
                .map(t -> buildTaskToJson(t))
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

    public List<Task> readTasks() {
        List<Task> tasks = new ArrayList<>();

        String regex = "(?<=\\{)[\\s\\S]*?(?=\\})";
        Pattern pattern = Pattern.compile(regex);

        try(BufferedReader br = Files.newBufferedReader(path)) {
            String line = br.readAllAsString();
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()) {
                String taskJson = matcher.group();
                Task task = buildJsonToTask(taskJson.trim());
                tasks.add(task);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }
}
