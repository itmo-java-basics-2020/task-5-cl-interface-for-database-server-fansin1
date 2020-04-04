package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    class DatabaseCommandResultImpl implements DatabaseCommandResult {

        private final DatabaseCommandStatus commandStatus;
        private final String message;

        public DatabaseCommandResultImpl(DatabaseCommandStatus commandStatus, String message) {
            this.commandStatus = commandStatus;
            this.message = message;
        }

        @Override
        public Optional<String> getResult() {
            if (isSuccess()) {
                return Optional.of(message);
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
                return message;
            }
        }
    }

    static DatabaseCommandResult success(String message) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.SUCCESS, message);
    }

    static DatabaseCommandResultImpl error(String message) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.FAILED, message);
    }

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }
}