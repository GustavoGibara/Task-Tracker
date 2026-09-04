package test.util;
import util.JsonInteraction;

public class readTasksTest {
    public static void main(String[] args) {
        JsonInteraction.readTasks().forEach(System.out::println);
    }
}
