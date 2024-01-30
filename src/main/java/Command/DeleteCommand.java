package Command;

import duke.DukeException;
import duke.TaskList;
import duke.UI;
import duke.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public TaskList execute(TaskList tasks, UI ui) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("Task number out of bounds.");
        }
        Task removedTask = tasks.removeTask(taskIndex);
        ui.showTaskRemoved(removedTask, tasks.size());
        return tasks;
    }
}