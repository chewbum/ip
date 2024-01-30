package duke;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasksOnDate(String dateString) throws DukeException {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use d/M/yyyy.");
        }

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDate deadlineDate = deadline.getBy().toLocalDate();
                if (deadlineDate.equals(date)) {
                    filteredTasks.add(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (!event.getFrom().isAfter(date) && !event.getTo().isBefore(date)) {
                    filteredTasks.add(event);
                }
            }
        }
        return filteredTasks;
    }
}