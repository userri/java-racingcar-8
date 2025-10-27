package racingcar;

public class TryNumber {
    private String tryNumberString;
    private long tryNumber;

    public TryNumber(String tryNumberString) {
        this.tryNumber = validateNumber(tryNumberString);
    }

    public long validateNumber(String string) {
        try {
            tryNumber = Long.parseLong(string.stripTrailing().stripLeading());
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TRY_NUMBER_FORMAT.getError());
        }
        if(tryNumber <=0)
        {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TRY_NUMBER_RANGE.getError());
        }
        return tryNumber;
    }

}
