package adavis100.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Day02 {
    Map<String, Integer> points = Map.of("X", 1, "Y", 2, "Z", 3);
    Map<String, Integer> outcomeScore = Map.of("X", 0, "Y", 3, "Z", 6);

    public int score(List<String> lines) {
        int total = 0;
        for (String line : lines) {
            var split = line.split(" ");
            String them = split[0];
            String me = split[1];
            int score = points.get(me);
            if (them.equals("A") && me.equals("Y") ||  // them: rock, me: paper
                    them.equals("B") && me.equals("Z") ||  // them: paper, me: scissors
                    them.equals("C") && me.equals("X")) {  // them: scissors, me: rock
                score += 6; // I win
            } else if ((them.equals("A") && me.equals("Z")) || // them: rock, me: scissors
                    (them.equals("B") && me.equals("X")) || // them: paper, me: rock
                    (them.equals("C") && me.equals("Y"))) { // them: scissors, me: paper
                score += 0; // I lose
            } else if ((them.equals("A") && me.equals("X")) || // them: rock, me: rock
                (them.equals("B") && me.equals("Y")) || // them: paper, me: paper
                (them.equals("C") && me.equals("Z"))) { // them: scissors, me: scissors
                score += 3; // draw
            }
            total += score;
        }
        return total;
    }

    public int score2(List<String> lines) {
        int total = 0;
        for (String line : lines) {
            var split = line.split(" ");
            String them = split[0];
            String outcome = split[1];
            int score = outcomeScore.get(outcome);
            if (them.equals("A") && outcome.equals("Y") ||  // them: rock, outcome: draw -> me: rock
                    them.equals("B") && outcome.equals("X") ||  // them: paper, outcome: lose -> me: rock
                    them.equals("C") && outcome.equals("Z")) {  // them: scissors, outcome: win -> me: rock
                score += points.get("X"); // rock
            } else if ((them.equals("A") && outcome.equals("Z")) || // them: rock, outcome: win -> me: paper
                    (them.equals("B") && outcome.equals("Y")) || // them: paper, outcome: draw -> me: paper
                    (them.equals("C") && outcome.equals("X"))) { // them: scissors, outcome: lose -> me: paper
                score += points.get("Y"); // paper
            } else if ((them.equals("A") && outcome.equals("X")) || // them: rock, outcome: lose -> me: scissors
                    (them.equals("B") && outcome.equals("Z")) || // them: paper, outcome: win -> me: scissors
                    (them.equals("C") && outcome.equals("Y"))) { // them: scissors, outcome: draw -> me: scissors
                score += points.get("Z"); // scissors
            }
            total += score;
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day02.txt"));
        var lines = Arrays.asList(inStr.split("\n"));
        Day02 day02 = new Day02();
        System.out.println("part 1: " + day02.score(lines));
        System.out.println("part 2: " + day02.score2(lines));
    }
}
