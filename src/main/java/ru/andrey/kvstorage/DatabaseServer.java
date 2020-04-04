package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {

    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("Null command");
        }

        String[] terms = commandText.split(" ");
        DatabaseCommands databaseCommands;
        try {
            databaseCommands = DatabaseCommands.valueOf(terms[0]);
        } catch (IllegalArgumentException e) {
            return DatabaseCommandResult.error("No such command");
        }

        return databaseCommands
                .getCommand(env, Arrays.copyOfRange(terms, 1, terms.length))
                .execute();
    }
}
