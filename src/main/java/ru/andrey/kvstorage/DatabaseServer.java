package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.*;

import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null || commandText.isEmpty()) {
            return DatabaseCommandResult.error("Wrong command");
        }

        DatabaseCommand command = parseCommand(commandText);
        if (command == null) {
            return DatabaseCommandResult.error("No such command");
        }

        return tryExecute(command);
    }

    private DatabaseCommandResult tryExecute(DatabaseCommand command) {
        try {
            return command.execute();
        } catch (Exception e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }

    private DatabaseCommand parseCommand(String commandText) {
        String[] terms = commandText.split(" ");
        CommandArgs args = new CommandArgs(Arrays.copyOfRange(terms, 1, terms.length));

        return getCommand(terms[0], args);
    }

    private DatabaseCommand getCommand(String name, CommandArgs args) {
        DatabaseCommands databaseCommands;
        try {
            databaseCommands = DatabaseCommands.valueOf(name);
        } catch (IllegalArgumentException e) {
            return null;
        }

        return databaseCommands.getCommand(env, args);
    }
}
