package adavis100.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day14 {
    record Coord(int x, int y) {}

    static int solvePart1(String in) {
        boolean [][]grid = new boolean[1000][1000];
        addPaths(grid, in);
        return simulateSand(grid);
    }

    private static void addPaths(boolean[][] grid, String in) {
        for (String line : in.split("\n")) {
            List<Coord> coords = parseCoords(line);
            for (int i = 1; i < coords.size(); i++) {
                fillLine(grid, coords.get(i - 1), coords.get(i));
            }
        }
    }

    private static List<Coord> parseCoords(String line) {
        var coords = new ArrayList<Coord>();
        for (String s : line.split(" -> ")) {
            coords.add(new Coord(Integer.valueOf(s.split(",")[0]), Integer.valueOf(s.split(",")[1])));
        }
        return coords;
    }

    private static void fillLine(boolean[][] grid, Coord from, Coord to) {
        int xStep = 0;
        int yStep = 0;
        if (from.x < to.x) {
            xStep = 1;
        } else if (from.x > to.x) {
            xStep = -1;
        }
        if (from.y < to.y) {
            yStep = 1;
        } else if (from.y > to.y) {
            yStep = -1;
        }

        int x = from.x;
        int y = from.y;
        while (x != to.x || y != to.y) {
            grid[x][y] = true;
            x += xStep;
            y += yStep;
        }
        grid[to.x][to.y] = true;
    }

    private static int simulateSand(boolean[][] grid) {
        int sandCount = 0;
        while (addSand(grid)) {
            sandCount++;
        }
        return sandCount;
    }

    private static boolean addSand(boolean[][] grid) {
        Coord sandPos = new Coord(500, 0);
        while (inBounds(sandPos) && !blocked(sandPos, grid)) {
            sandPos = move(sandPos, grid);
        }
        return inBounds(sandPos) && blocked(sandPos, grid);
    }

    private static boolean inBounds(Coord sandPos) {
        return sandPos.x > 0 && sandPos.x < 999 && sandPos.y < 999;
    }

    private static boolean blocked(Coord sandPos, boolean[][] grid) {
        return grid[sandPos.x][sandPos.y + 1] && grid[sandPos.x - 1][sandPos.y + 1] && grid[sandPos.x + 1][sandPos.y + 1];
    }

    private static Coord move(Coord sandPos, boolean[][] grid) {
        grid[sandPos.x][sandPos.y] = false;
        if (!grid[sandPos.x][sandPos.y + 1]) {
            grid[sandPos.x][sandPos.y + 1] = true;
            return new Coord(sandPos.x, sandPos.y + 1);
        } else if (!grid[sandPos.x - 1][sandPos.y + 1]) {
            grid[sandPos.x - 1][sandPos.y + 1] = true;
            return new Coord(sandPos.x - 1, sandPos.y + 1);
        } else {
            grid[sandPos.x + 1][sandPos.y + 1] = true;
            return new Coord(sandPos.x + 1, sandPos.y + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day14.txt"));
        System.out.println("part 1: " + solvePart1(inStr));
//        System.out.println("part 2: " + solvePart2(inStr));
    }
}
