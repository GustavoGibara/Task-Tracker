package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import entities.Task;
import enums.Status;

public class JsonInteraction {
    
    private static final Path path = Paths.get("tasks.json");

    public static void createJson() {
        if (Files.notExists(path)) {
            try {
                Files.writeString(path, "[]");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }    
        }
    }

    private static Task buildJsonToTask(String taskJson) {
        Pattern pattern = Pattern.compile(":\\s*\"?([^\",\\n]+)\"?");
        Matcher matcher = pattern.matcher(taskJson);

        List<String> attributes = new ArrayList<>();

        while(matcher.find()) {
            String value = matcher.group()
                        .replaceFirst(":\\s*", "");

            if (value.charAt(0) == '\"' && value.charAt(value.length() - 1) == '\"') {
                value = value.substring(1, value.length() - 1);
            }

            attributes.add(value);
        }

        Long id = Long.parseLong(attributes.get(0)) ;
        String description = attributes.get(1);
        Status status = Status.valueOf(attributes.get(2));
        LocalDateTime createdAt = checkNullAttribute(attributes.get(3));
        LocalDateTime updateAt = checkNullAttribute(attributes.get(4));
        
        Task task = new Task(id, description, status, createdAt, updateAt);

        return task;
    }

    private static LocalDateTime checkNullAttribute(String possibleNull) {
        LocalDateTime date;
        
        if (possibleNull.equals("null")) {
            date = null;
        } else {
            date = LocalDateTime.parse(possibleNull);
        }
        
        return date;
    }

    public static void saveTasks(List<Task> tasks) {
        try(BufferedWriter bw = Files.newBufferedWriter(path)) {
            bw.write("[\n");
            
            List<String> jsonTasks = tasks.stream()
                .map(Task::toJson)
                .map(s -> s.replaceAll("\t", "\t\t"))
                .map(s -> s.replaceAll("\\{", "\t\\{"))
                .map(s -> s.replaceAll("\\}", "\t\\}"))
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

    public static List<Task> readTasks() {
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
