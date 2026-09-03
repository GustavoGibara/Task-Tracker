package repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TaskRepository {

    private final Path path = Paths.get("tasks.json");

    public TaskRepository() {
        if (Files.notExists(path)) {
            createJson();
        }
    }

    public void createJson() {
        try {
            Files.writeString(path, "[]");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
