package ru.andrey.kvstorage.console;

public class CommandArgs {

    private static final int DATABASE_NAME_INDEX = 0;
    private static final int TABLE_NAME_INDEX = 1;
    private static final int KEY_INDEX = 2;
    private static final int VALUE_INDEX = 3;

    private final String[] args;

    public CommandArgs(String... args) {
        this.args = args;
    }

    public String getDatabaseName() {
        return args[DATABASE_NAME_INDEX];
    }

    public String getTableName() {
        return args[TABLE_NAME_INDEX];
    }

    public String getKey() {
        return args[KEY_INDEX];
    }

    public String getValue() {
        return args[VALUE_INDEX];
    }

    public int length() {
        return args.length;
    }
}
