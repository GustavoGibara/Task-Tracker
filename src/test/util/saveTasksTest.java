package test.util;
import java.util.ArrayList;
import java.util.List;

import entities.Task;
import enums.Status;
import util.JsonInteraction;

public class saveTasksTest {
    private static List<Task> tasks = new ArrayList<>(List.of(
        new Task(1L, "Correr", Status.DONE, null, null),
        new Task(2L, "Estudar", Status.TODO, null, null)
    ));
    public static void main(String[] args) {
        
        JsonInteraction.saveTasks(tasks);

    }
}
