package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            if (args.length != 1) {
                return () -> DatabaseCommandResult.error("Not right count of args, should be 1");
            }

            return () -> {
                environment.addDatabase(null);
                return DatabaseCommandResult.success("");
            };
        }
    }, CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            if (args.length != 2) {
                return () -> DatabaseCommandResult.error("Not right count of args, should be 2");
            }

            return () ->
                    environment
                            .getDatabase(args[DATABASE_NAME_INDEX])
                            .map(database -> tryCreateTable(database, args[TABLE_NAME_INDEX]))
                            .orElse(DatabaseCommandResult.error(ERROR));
        }

        private DatabaseCommandResult tryCreateTable(Database database, String tableName) {
            try {
                database.createTableIfNotExists(tableName);
                return DatabaseCommandResult.success("");
            } catch (DatabaseException databaseException) {
                return DatabaseCommandResult.error(ERROR);
            }
        }
    }, UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            if (args.length != 4) {
                return () -> DatabaseCommandResult.error("Not right count of args, should be 4");
            }

            return () ->
                    environment
                            .getDatabase(args[DATABASE_NAME_INDEX])
                            .map(database -> tryUpdateKey(database, args))
                            .orElse(DatabaseCommandResult.error(ERROR));
        }

        private DatabaseCommandResult tryUpdateKey(Database database, String... args) {
            try {
                database.write(
                        args[TABLE_NAME_INDEX],
                        args[KEY_INDEX],
                        args[VALUE_INDEX]);
                return DatabaseCommandResult.success("");
            } catch (DatabaseException databaseException) {
                return DatabaseCommandResult.error(ERROR);
            }
        }
    }, READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            if (args.length != 3) {
                return () -> DatabaseCommandResult.error("Not right count of args, should be 3");
            }

            return () ->
                    environment
                            .getDatabase(args[DATABASE_NAME_INDEX])
                            .map(database -> tryReadKey(database, args))
                            .orElse(DatabaseCommandResult.error(""));
        }

        private DatabaseCommandResult tryReadKey(Database database, String... args) {
            try {
                String value = database.read(args[TABLE_NAME_INDEX], args[KEY_INDEX]);
                return DatabaseCommandResult.success(value);
            } catch (DatabaseException databaseException) {
                return DatabaseCommandResult.error(ERROR);
            }
        }
    };

    private static final int DATABASE_NAME_INDEX = 0;
    private static final int TABLE_NAME_INDEX = 1;
    private static final int KEY_INDEX = 2;
    private static final int VALUE_INDEX = 3;
    private static final String ERROR = "Table already exists";

    public abstract DatabaseCommand getCommand(ExecutionEnvironment environment, String... args);
}
