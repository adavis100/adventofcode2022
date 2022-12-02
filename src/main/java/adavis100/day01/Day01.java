package adavis100.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day01 {
    public static void main(String[] args) throws IOException {
        Day01 day01 = new Day01();
        var list = day01.loadInputFile();
        System.out.println("Part 1: " + day01.getMaxCals(list));
        System.out.println("Part 2: " + day01.getTop3MaxCals(list));
    }

    List<List<Integer>> loadInputFile() throws IOException {
        List<List<Integer>> cals = new ArrayList<>();
        var inStr = Files.readString(Path.of("src/main/resources/day01.txt"));
        return loadInputString(inStr);
    }

    List<List<Integer>> loadInputString(String inStr) {
        int i = 0;
        List<List<Integer>> elfCalories = new ArrayList<>();
        elfCalories.add(new ArrayList<>());
        for (var line : inStr.split("\n")) {
            if (line.isEmpty()) {
                i++;
                elfCalories.add(new ArrayList<>());
            } else {
                var list = elfCalories.get(i);
                list.add(Integer.valueOf(line));
            }
        }
        return elfCalories;
    }

    public int getMaxCals(List<List<Integer>> elfCalories) {
        return elfCalories.
                stream().
                map(l -> l.stream().mapToInt(i -> i).sum()).
                max(Comparator.naturalOrder()).
                get();
    }

    public int getTop3MaxCals(List<List<Integer>> elfCalories) {
        var sorted = elfCalories.
                stream().
                map(l -> l.stream().mapToInt(i -> i).sum()).
                sorted(Comparator.reverseOrder()).
                collect(Collectors.toList());
        return sorted.get(0) + sorted.get(1) + sorted.get(2);
    }
}
