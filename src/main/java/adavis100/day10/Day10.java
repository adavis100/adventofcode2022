package adavis100.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 {
    public static final int NUM_ROWS = 6;
    public static final int COLUMN_WIDTH = 40;

    static int getSumOfSignalStrengths(List<String> cmds) {
        int[] registerVals = runProgram(cmds);
        int total = 0;
        for (int cycle = 20; cycle <= 220; cycle += 40) {
            total += registerVals[cycle] * cycle;
        }
        return total;
    }

    static int[] runProgram(List<String> commands) {
        int cycles = commands.stream().mapToInt(it -> it.equals("noop") ? 1 : 2).sum() + 2;
        int[] registerVals = new int[cycles];
        registerVals[1] = 1;
        int cycle = 1;
        for (String command : commands) {
            if (command.equals("noop")) {
                cycle++;
                registerVals[cycle] = registerVals[cycle - 1];
            } else if (command.startsWith("addx")) {
                cycle += 2;
                registerVals[cycle - 1] = registerVals[cycle - 2];
                registerVals[cycle] = registerVals[cycle - 1] + Integer.parseInt(command.replace("addx ", ""));
            }
        }
        return registerVals;
    }

    static List<String> getImage(List<String> cmds) {
        var image = new ArrayList<String>();
        int[] registerVals = runProgram(cmds);

        for (int row = 0; row < NUM_ROWS; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < COLUMN_WIDTH; col++) {
                int cycle = (row * COLUMN_WIDTH) + col + 1;
                if (registerVals[cycle] == col || registerVals[cycle] - 1 == col || registerVals[cycle] + 1 == col) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
            image.add(sb.toString());
        }
        return image;
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day10.txt"));
        var lines = Arrays.asList(inStr.split("\n"));
        System.out.println("part 1: " + getSumOfSignalStrengths(lines));
        System.out.println("part 2:");
        var image = getImage(lines);
        for (String line : image) {
            System.out.println(line);
        }
    }
}
