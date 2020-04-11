package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.*;
import ru.andrey.kvstorage.exception.DatabaseException;

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

        DatabaseCommand command;
        try {
            command = parseCommand(commandText);
        } catch (Exception e) {
            return DatabaseCommandResult.error(e.getMessage());
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

    private DatabaseCommand parseCommand(String commandText) throws DatabaseException {
        String[] terms = commandText.split(" ");
        CommandArgs args = new CommandArgs(Arrays.copyOfRange(terms, 1, terms.length));

        return getCommand(terms[0], args);
    }

    private DatabaseCommand getCommand(String name, CommandArgs args) throws DatabaseException {
        DatabaseCommands databaseCommands;
        try {
            databaseCommands = DatabaseCommands.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Wrong command name");
        }

        return databaseCommands.getCommand(env, args);
    }
}
