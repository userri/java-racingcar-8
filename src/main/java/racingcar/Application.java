package racingcar;

import camp.nextstep.edu.missionutils.Console;
import racingcar.message.ErrorMessage;
import racingcar.message.Message;
import racingcar.service.Race;
import racingcar.validate.TryNumber;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        System.out.println(Message.INPUT_NAME_MESSAGE.getMessage());
        String names = Console.readLine();
        System.out.println(Message.INPUT_TRYNUMBER_MESSAGE.getMessage());
        String tryNumString = Console.readLine();
        System.out.println();

        TryNumber tryNumber = new TryNumber(tryNumString);

        List<String> nameListBeforeStrip = Arrays.asList(names.split(Message.INPUT_SPLIT_CHAR.getMessage()));
        List<String> nameList = nameListBeforeStrip.stream()
                .map(String::stripLeading)
                .map(String::stripTrailing)
                .distinct()
                .toList();

        validateNamesEndDelimiter(names);
        validateNameList(nameList, nameListBeforeStrip);

        Race race = new Race(tryNumber);
        race.initStartLine(nameList);
        Map<String, Integer> resultScores = race.run(tryNumber);

        Integer maxScore = Collections.max(resultScores.values());
        List<String> winners = resultScores.keySet()
                .stream()
                .filter(a -> resultScores.get(a).equals(maxScore))
                .toList();

        System.out.print("최종 우승자 : ");

        String winnersToString = winners.stream().collect(Collectors.joining(", "));
        System.out.println(winnersToString);
    }

    private static void validateNamesEndDelimiter(String names) {
        String strippedNames = names.stripTrailing();
        String endCharacter = strippedNames.substring(strippedNames.length() - 1);
        String delimiter = Message.INPUT_SPLIT_CHAR.getMessage();
        if (delimiter.equals(endCharacter)) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_EMPTY.getError());
        }
    }

    private static void validateNameList(List<String> nameList, List<String> nameListBeforeStrip) {
        for (String name : nameList) {
            if (name.isBlank()) {
                throw new IllegalArgumentException(ErrorMessage.CAR_NAME_EMPTY.getError());
            }
        }
        for (String name : nameList) {
            if (name.length() > Message.MAX_NAME_LENGTH) {
                throw new IllegalArgumentException(ErrorMessage.CAR_NAME_LENGTH_EXCEEDED.getError());
            }
        }
        if (nameList.size() < Message.MIN_PLAYERS) {
            throw new IllegalArgumentException(ErrorMessage.MINIMUM_PLAYERS_REQUIRED.getError());
        }

        if (nameListBeforeStrip.size() != nameList.size()) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_DUPLICATED.getError());
        }
    }
}
