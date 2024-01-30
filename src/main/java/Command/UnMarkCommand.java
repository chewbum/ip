package Command;

import duke.DukeException;
import duke.TaskList;
import duke.UI;
import duke.Task;

public class UnMarkCommand extends Command {
    private int taskIndex;

    public UnMarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public TaskList execute(TaskList tasks, UI ui) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("Invalid task number. Index out of bounds.");
        }

        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        ui.showTaskMarkedAsNotDone(task);
        return tasks;
    }
}