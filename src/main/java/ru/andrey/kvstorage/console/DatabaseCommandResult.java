package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.SUCCESS, result);
    }

    static DatabaseCommandResult error(String errorMessage) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.FAILED, errorMessage);
    }

    static DatabaseCommandResult unexpectedError() {
        return DatabaseCommandResultImpl.UNEXPECTED_ERROR;
    }

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    class DatabaseCommandResultImpl implements DatabaseCommandResult {

        private static final DatabaseCommandResult UNEXPECTED_ERROR =
                new DatabaseCommandResultImpl(DatabaseCommandStatus.FAILED, "Unexpected error");

        private final DatabaseCommandStatus commandStatus;
        private final String value;

        private DatabaseCommandResultImpl(DatabaseCommandStatus commandStatus, String value) {
            this.commandStatus = commandStatus;
            this.value = value;
        }

        @Override
        public Optional<String> getResult() {
            if (!isSuccess()) {
                return Optional.empty();
            }

            return Optional.of(value);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return commandStatus;
        }

        @Override
        public boolean isSuccess() {
            return commandStatus == DatabaseCommandStatus.SUCCESS;
        }

        @Override
        public String getErrorMessage() {
            if (isSuccess()) {
                return null;
            }

            return value;
        }
    }
}
