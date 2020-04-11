package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class CommandArgs {

    private static final int DATABASE_NAME_INDEX = 0;
    private static final int TABLE_NAME_INDEX = 1;
    private static final int KEY_INDEX = 2;
    private static final int VALUE_INDEX = 3;

    private final String[] args;

    public CommandArgs(String... args) {
        this.args = args;
    }

    public String getDatabaseName() throws DatabaseException {
        return getArg(DATABASE_NAME_INDEX);
    }

    public String getTableName() throws DatabaseException {
        return getArg(TABLE_NAME_INDEX);
    }

    public String getKey() throws DatabaseException {
        return getArg(KEY_INDEX);
    }

    public String getValue() throws DatabaseException {
        return getArg(VALUE_INDEX);
    }

    public int length() {
        return args.length;
    }

    private String getArg(int index) throws DatabaseException {
        if (index >= args.length) {
            throw new DatabaseException("No such arg");
        }

        return args[index];
    }
}
