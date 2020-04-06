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
        return getArg(DATABASE_NAME_INDEX);
    }

    public String getTableName() {
        return getArg(TABLE_NAME_INDEX);
    }

    public String getKey() {
        return getArg(KEY_INDEX);
    }

    public String getValue() {
        return getArg(VALUE_INDEX);
    }

    private String getArg(int index) {
        if (index >= args.length) {
            throw new IllegalStateException("No such arg");
        }

        return args[index];
    }
}
