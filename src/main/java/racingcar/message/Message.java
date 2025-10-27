package racingcar.message;

public enum Message {
    INPUT_NAME_MESSAGE("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
    INPUT_TRYNUMBER_MESSAGE("시도할 횟수는 몇 회인가요?"),
    INPUT_SPLIT_CHAR(",")
    ;
    public static final int ADVANCE_RANGE_START = 0;
    public static final int ADVANCE_RANGE_END = 9;
    public static final int START_LINE = 0;
    public static final int ADVANCE_DISTANCE = 1;
    public static final int ADVANCE_Threshold = 4;
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_NAME_LENGTH = 5;


    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
