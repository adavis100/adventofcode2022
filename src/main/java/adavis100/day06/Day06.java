package adavis100.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day06 {
    static int getMarkerCount(String s, int sequenceLength) {
        return IntStream.range(sequenceLength, s.length())
                .filter(i -> allUnique(s.substring(i - sequenceLength, i)))
                .findFirst().orElse(0);
    }

    private static boolean allUnique(String s) {
        return s.chars().boxed().collect(Collectors.toSet()).size() == s.length();
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day06.txt"));
        System.out.println("part 1: " + getMarkerCount(inStr, 4));
        System.out.println("part 2: " + getMarkerCount(inStr, 14));
    }
}
