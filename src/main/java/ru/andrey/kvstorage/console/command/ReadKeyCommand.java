package ru.andrey.kvstorage.console.command;

import ru.andrey.kvstorage.console.CommandArgs;
import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public class ReadKeyCommand implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final CommandArgs args;

    public ReadKeyCommand(ExecutionEnvironment environment, CommandArgs args) {
        this.environment = environment;
        this.args = args;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        return environment
                .getDatabase(args.getDatabaseName())
                .map(this::tryReadKey)
                .orElse(DatabaseCommandResult.unexpectedError());
    }

    private DatabaseCommandResult tryReadKey(Database database) {
        try {
            String value = database.read(args.getTableName(), args.getKey());
            return DatabaseCommandResult.success(value);
        } catch (DatabaseException databaseException) {
            return DatabaseCommandResult.error(databaseException.getMessage());
        }
    }
}
