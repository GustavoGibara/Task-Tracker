import repository.TaskRepository;

public class readTasksTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();

        taskRepository.readTasks();
    }
}
