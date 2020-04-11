package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.command.CreateDatabaseCommand;
import ru.andrey.kvstorage.console.command.CreateTableCommand;
import ru.andrey.kvstorage.console.command.ReadKeyCommand;
import ru.andrey.kvstorage.console.command.UpdateKeyCommand;
import ru.andrey.kvstorage.exception.DatabaseException;

public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandArgs args) throws DatabaseException {
            return new CreateDatabaseCommand(environment, args);
        }
    }, CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandArgs args) throws DatabaseException {
            return new CreateTableCommand(environment, args);
        }
    }, UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandArgs args) throws DatabaseException {
            return new UpdateKeyCommand(environment, args);
        }
    }, READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, CommandArgs args) throws DatabaseException {
            return new ReadKeyCommand(environment, args);
        }
    };

    public abstract DatabaseCommand getCommand(ExecutionEnvironment environment, CommandArgs args) throws DatabaseException;
}
