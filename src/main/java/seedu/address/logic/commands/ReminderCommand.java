package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "reminder";
    public static final String SHOWING_REMINDER_MESSAGE = "Opened reminder window.";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(SHOWING_REMINDER_MESSAGE, false, true, false);
    }
}
