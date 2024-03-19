package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "reminder";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Opening reminder window.");
    }
}
