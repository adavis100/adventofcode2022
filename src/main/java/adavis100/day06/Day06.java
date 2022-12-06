package adavis100.day06;

import adavis100.day05.Day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.HashMap;

public class Day06 {
    static int getMarkerCount(String s, int sequenceLength) {
        Map<Character,Integer> seen = new HashMap<>();
        int i;
        for (i = 0; i < sequenceLength; i++) {
            seen.put(s.charAt(i), seen.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (; !allUnique(seen) && i < s.length(); i++) {
            seen.put(s.charAt(i - sequenceLength), seen.get(s.charAt(i - sequenceLength)) - 1);
            seen.put(s.charAt(i), seen.getOrDefault(s.charAt(i), 0) + 1);
        }
        return i;
    }

    private static boolean allUnique(Map<Character, Integer> map) {
        return map.values().stream().noneMatch(it -> it > 1);
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day06.txt"));
        System.out.println("part 1: " + getMarkerCount(inStr, 4));
        System.out.println("part 2: " + getMarkerCount(inStr, 14));
    }
}
