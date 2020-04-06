package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.SUCCESS, result);
    }

    static DatabaseCommandResultImpl error(String errorMessage) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.FAILED, errorMessage);
    }

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    class DatabaseCommandResultImpl implements DatabaseCommandResult {

        private final DatabaseCommandStatus commandStatus;
        private final String value;

        private DatabaseCommandResultImpl(DatabaseCommandStatus commandStatus, String value) {
            this.commandStatus = commandStatus;
            this.value = value;
        }

        @Override
        public Optional<String> getResult() {
            if (isSuccess()) {
                return Optional.of(value);
            } else {
                return Optional.empty();
            }
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
            } else {
                return value;
            }
        }
    }
}