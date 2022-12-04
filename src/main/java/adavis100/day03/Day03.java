package adavis100.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day03 {
    public int getPriorities(List<String> lines) {
        return lines.stream()
                .map(line -> getCommonItems(line.substring(0, line.length() / 2), line.substring(line.length() / 2)))
                .mapToInt(this::getPriority)
                .sum();
    }

    private char getCommonItems(String... part) {
        var sets = Arrays.asList(part).stream()
                .map(s -> s.chars().mapToObj(c -> (char) c).collect(Collectors.toSet()))
                .collect(Collectors.toList());
        var common = sets.stream().skip(1)
                .collect(() -> new HashSet<>(sets.get(0)), Set::retainAll, Set::retainAll);
        return common.stream().findFirst().get();
    }

    private int getPriority(char c) {
        if (Character.isLowerCase(c)) {
            return 1 + c - 'a';
        } else {
            return 27 + c - 'A';
        }
    }

    public int getPrioritiesPart2(List<String> lines) {
        return IntStream.iterate(0, i -> i < lines.size(), i -> i += 3)
                .mapToObj(i -> getCommonItems(lines.get(i), lines.get(i + 1), lines.get(i + 2)))
                .mapToInt(this::getPriority)
                .sum();
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day03.txt"));
        var lines = Arrays.asList(inStr.split("\n"));
        Day03 day03 = new Day03();
        System.out.println("part 1: " + day03.getPriorities(lines));
        System.out.println("part 2: " + day03.getPrioritiesPart2(lines));
    }
}