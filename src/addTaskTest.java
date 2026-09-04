import entities.Task;
import enums.Status;
import repository.TaskRepository;

public class addTaskTest {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();

        taskRepository.addTask(new Task(5L, "Olá", Status.IN_PROGRESS, null, null));
    }
}
