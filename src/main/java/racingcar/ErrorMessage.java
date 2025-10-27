package racingcar;

public enum Error {
    INVALID_NUMBER_FORMAT,
    INVALID_TRY_COUNT_RANGE,
    CAR_NAME_EMPTY,
    NAME_LENGTH_EXCEEDED,
    MINIMUM_PLAYERS_REQUIRED,
    CAR_NAME_DUPLICATED
    ;


    private final String error;

    Error(String message) {
        this.error = message;
    }
}
