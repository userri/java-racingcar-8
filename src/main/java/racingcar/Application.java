package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String [] args) {
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

        isNoBlankNameList(nameList, nameListBeforeStrip);

        Race race = new Race(tryNumber);
        race.initStartLine(nameList);
        Map<String, Integer> scores = race.run(tryNumber);

        Integer maxScore = Collections.max(scores.values());
        List<String> winners = scores.keySet()
                .stream()
                .filter(a -> scores.get(a).equals(maxScore))
                .toList();

        System.out.print("최종 우승자 : ");

        String winnersToString = winners.stream().collect(Collectors.joining(", "));
        System.out.println(winnersToString);

    }

    private static void isNoBlankNameList(List<String> nameList, List<String> nameListBeforeStrip) {
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
