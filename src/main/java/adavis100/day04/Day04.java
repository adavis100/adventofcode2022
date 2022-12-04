package adavis100.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day04 {
    public long countFullyContains(List<String> lines) {
        return lines.stream()
                .map(this::parseSections)
                .filter(this::isFullyContained)
                .count();
    }

    private int[][] parseSections(String s) {
        var parts = s.split(",");
        var first = parts[0].split("-");
        var second = parts[1].split("-");
        return new int[][]{{Integer.parseInt(first[0]), Integer.parseInt(first[1])},
                {Integer.parseInt(second[0]), Integer.parseInt(second[1])}};
    }

    private boolean isFullyContained(int[][] sections) {
        return sections[0][0] <= sections[1][0] && sections[0][1] >= sections[1][1] ||
                sections[1][0] <= sections[0][0] && sections[1][1] >= sections[0][1];
    }

    public long countHasOverlap(List<String> lines) {
        return lines.stream()
                .map(this::parseSections)
                .filter(this::hasOverlap)
                .count();
    }

    private boolean hasOverlap(int[][] sections) {
        return sections[0][0] <= sections[1][0] && sections[0][1] >= sections[1][0] ||
                sections[1][0] <= sections[0][0] && sections[1][1] >= sections[0][0];
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day04.txt"));
        var lines = Arrays.asList(inStr.split("\n"));
        Day04 day04 = new Day04();
        System.out.println("part 1: " + day04.countFullyContains(lines));
        System.out.println("part 2: " + day04.countHasOverlap(lines));
    }

}
