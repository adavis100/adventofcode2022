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
            int score = points.get(line.substring(2));
            score += switch (line) {
                case "A Y", "B Z", "C X" -> 6; // I win
                case "A X", "B Y", "C Z" -> 3; // draw
                default -> 0;
            };
            total += score;
        }
        return total;
    }

    public int score2(List<String> lines) {
        int total = 0;
        for (String line : lines) {
            int score = outcomeScore.get(line.substring(2));
            score += switch (line) {
                case "A Y", "B X", "C Z" -> points.get("X"); // rock
                case "A Z", "B Y", "C X" -> points.get("Y"); // paper
                case "A X", "B Z", "C Y" -> points.get("Z"); // scissors
                default -> 0;
            };
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
