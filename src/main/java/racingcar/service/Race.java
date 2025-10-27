package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.message.Message;
import racingcar.validate.TryNumber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Race {
    TryNumber tryNumber;
    HashMap<String, Integer> scores;

    public Race(TryNumber tryNumber) {
        this.tryNumber = tryNumber;
        this.scores = new HashMap<>();
    }

    public Map<String, Integer> run(TryNumber tryNumber) {
        for (long i = 0; i < tryNumber.getTryNumber(); i++) {
            for (String name : this.scores.keySet()) {
                eachCarRandomAdvance(name);
            }
            printResult(scores);
        }
        return scores;
    }

    private void eachCarRandomAdvance(String name) {
        int advance = Randoms.pickNumberInRange(Message.ADVANCE_RANGE_START, Message.ADVANCE_RANGE_END);
        if (advance >= Message.ADVANCE_THRESHOLD) {
            this.scores.put(name, this.scores.get(name) + Message.ADVANCE_DISTANCE);
        }
    }

    private static void printResult(Map<String, Integer> scores) {
        System.out.println("실행 결과");
        for (String name : scores.keySet()) {
            System.out.print(name + " : ");
            System.out.println("-".repeat(scores.get(name)));
        }
        System.out.println();
    }

    public void initStartLine(List<String> nameList) {
        for (String name : nameList) {
            this.scores.put(name, Message.START_LINE);
        }
    }

}
