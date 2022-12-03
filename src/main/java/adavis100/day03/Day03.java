package adavis100.day03;

import adavis100.day02.Day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day03 {
    public int getPriorities(String []lines) {
        int p = 0;
        for (String s : lines) {
            String a = s.substring(0, s.length()/2);
            String b = s.substring(s.length()/2);
            Set<Character> aItems = new HashSet<>();
            for (char c : a.toCharArray()) {
                aItems.add(c);
            }
            for (char c : b.toCharArray()) {
                if (aItems.contains(c)) {
                    p += getPriority(c);
                    break;
                }
            }
        }
        return p;
    }

    private int getPriority(char c) {
        if (Character.isLowerCase(c)) {
            return 1 + c - 'a';
        } else {
            return 27 + (c - 'A');
        }
    }

    public int getPrioritiesPart2(String[] lines) {
        int p = 0;
        for (int i = 0; i < lines.length; i+=3) {
            String a = lines[i], b = lines[i+1], c = lines[i + 2];
            Set<Character> aItems = new HashSet<>();
            for (char ch : a.toCharArray()) {
                aItems.add(ch);
            }
            Set<Character> bItems = new HashSet<>();
            for (char ch : b.toCharArray()) {
                bItems.add(ch);
            }
            for (char ch : c.toCharArray()) {
                if (aItems.contains(ch) && bItems.contains(ch)) {
                    p += getPriority(ch);
                    break;
                }
            }
        }
        return p;
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day03.txt"));
        var lines = inStr.split("\n");
        Day03 day03 = new Day03();
        System.out.println("part 1: " + day03.getPriorities(lines));
        System.out.println("part 2: " + day03.getPrioritiesPart2(lines));
    }

}