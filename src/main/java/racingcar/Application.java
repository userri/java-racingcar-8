package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String [] args) {
        System.out.println(Message.INPUT_NAME_MESSAGE.getMessage());
        String names = Console.readLine();
        System.out.println(Message.INPUT_TRYNUMBER_MESSAGE.getMessage());
        String tryNumStr = Console.readLine();
        System.out.println();
        long tryNumber;
        try {
            tryNumber = Long.parseLong(tryNumStr.stripTrailing().stripLeading());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TRY_COUNT_FORMAT.getError());
        }
        if (tryNumber <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TRY_COUNT_FORMAT.getError());
        }

        List<String> nameListBeforeStrip = Arrays.asList(names.split(Message.INPUT_SPLIT_CHAR.getMessage()));
        List<String> nameList = nameListBeforeStrip.stream()
                .map(String::stripLeading)
                .map(String::stripTrailing)
                .distinct()
                .toList();

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

        Map<String, Integer> scores = new HashMap<>();

        for (String name : nameList) {
            scores.put(name, Message.START_LINE);
        }

        for (long i = 0; i < tryNumber; i++) {
            for (String name : scores.keySet()) {
                int advance = Randoms.pickNumberInRange(Message.ADVANCE_RANGE_START, Message.ADVANCE_RANGE_END);
                if (advance >= Message.ADVANCE_Threshold) {
                    scores.put(name, scores.get(name) + Message.ADVANCE_DISTANCE);
                }
            }
            printResult(scores);
        }

        Integer maxScore = Collections.max(scores.values());
        List<String> winners = scores.keySet()
                .stream()
                .filter(a -> scores.get(a).equals(maxScore))
                .toList();

        System.out.print("최종 우승자 : ");

        String winnersToString = winners.stream().collect(Collectors.joining(", "));
        System.out.println(winnersToString);

    }

    private static void printResult(Map<String, Integer> scores) {
        System.out.println("실행 결과");
        for (String name : scores.keySet()) {
            System.out.print(name + " : ");
            System.out.println("-".repeat(scores.get(name)));
        }
        System.out.println();
    }
}
