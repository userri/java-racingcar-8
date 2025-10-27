package racingcar.message;

public enum ErrorMessage {
    INVALID_TRY_NUMBER_FORMAT("시행횟수를 숫자로 입력해주세요."),
    INVALID_TRY_NUMBER_RANGE("시행횟수는 1 이상의 자연수여야 합니다."),
    CAR_NAME_EMPTY("빈 이름이 있습니다. 다시 입력해주세요."),
    CAR_NAME_LENGTH_EXCEEDED("자동차 이름은 5글자 이하여야합니다."),
    MINIMUM_PLAYERS_REQUIRED("경주 인원은 2명 이상이어야 합니다."),
    CAR_NAME_DUPLICATED("자동차 이름은 중복될 수 없습니다.");


    private final String error;

    ErrorMessage(String message) {
        this.error = message;
    }

    public String getError() {
        return error;
    }
}
