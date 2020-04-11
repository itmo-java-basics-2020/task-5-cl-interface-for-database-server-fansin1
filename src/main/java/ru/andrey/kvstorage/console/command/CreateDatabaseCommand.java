package ru.andrey.kvstorage.console.command;

import ru.andrey.kvstorage.console.CommandArgs;
import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateDatabaseCommand implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final CommandArgs args;

    public CreateDatabaseCommand(ExecutionEnvironment environment, CommandArgs args) {
        this.environment = environment;
        this.args = args;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        args.getDatabaseName(); // to check args
        environment.addDatabase(null);
        return DatabaseCommandResult.success("");
    }
}
