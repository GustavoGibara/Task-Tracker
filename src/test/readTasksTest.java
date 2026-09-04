package test;
import util.jsonInteraction;

public class readTasksTest {
    public static void main(String[] args) {
        jsonInteraction.readTasks().forEach(System.out::println);
    }
}
