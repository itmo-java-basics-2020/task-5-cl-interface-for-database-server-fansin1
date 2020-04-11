package ru.andrey.kvstorage.console.command;

import ru.andrey.kvstorage.console.CommandArgs;
import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public class CreateTableCommand implements DatabaseCommand {

    private static final int ARGS_COUNT = 2;

    private final ExecutionEnvironment environment;
    private final CommandArgs args;

    public CreateTableCommand(ExecutionEnvironment environment, CommandArgs args) throws DatabaseException {
        if (args.length() != ARGS_COUNT) {
            throw new DatabaseException("Wrong args count");
        }
        this.environment = environment;
        this.args = args;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        return environment
                .getDatabase(args.getDatabaseName())
                .map(this::tryCreateTable)
                .orElse(DatabaseCommandResult.unexpectedError());
    }

    private DatabaseCommandResult tryCreateTable(Database database) {
        try {
            database.createTableIfNotExists(args.getTableName());
            return DatabaseCommandResult.success("");
        } catch (DatabaseException databaseException) {
            return DatabaseCommandResult.error(databaseException.getMessage());
        }
    }
}
