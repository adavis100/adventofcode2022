package adavis100.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


public class Day12 {
    record Position(int x, int y) {}

    final Position marker = new Position(-1, -1);
    public int getShortestPathLengthPart1(String in) {
        char[][] grid = loadGrid(in);
        Position start = findStart(grid);
        Position end = findEnd(grid);
        return findShortestPathFrom(grid, start, end);
    }

    public int getShortestPathLengthPart2(String in) {
        char[][] grid = loadGrid(in);
        List<Position> starts = findAllPossibleStarts(grid);
        Position end = findEnd(grid);
        return starts.stream().mapToInt(start -> findShortestPathFrom(grid, start, end)).min().orElseThrow();
    }

    private List<Position> findAllPossibleStarts(char[][] grid) {
        var list = new ArrayList<Position>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 'a' || grid[row][col] == 'S') {
                    grid[row][col] = 'a';
                    list.add(new Position(row, col));
                }
            }
        }
        return list;
    }

    private int findShortestPathFrom(char[][] grid, Position start, Position end) {
        Set<Position> visited = new HashSet<>();
        grid[start.x][start.y] = 'a';
        grid[end.x][end.y] = 'z';
        Deque<Position> queue = new ArrayDeque<>();
        queue.add(start);
        queue.add(marker);
        visited.add(start);
        int dist = 0;

        while (queue.size() > 0) {
            Position cur = queue.remove();
            if (cur.equals(marker)) {
                dist++;
                if (!queue.isEmpty()) {
                    queue.add(marker);
                }
                continue;
            } else if (cur.equals(end)) {
                return dist;
            }
            for (Position pos : getNeighbors(cur, grid)) {
                if (!visited.contains(pos)) {
                    queue.add(pos);
                    visited.add(pos);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private List<Position> getNeighbors(Position cur, char[][] grid) {
        var neighbors = List.of(new Position(cur.x - 1, cur.y), new Position(cur.x + 1, cur.y), new Position(cur.x, cur.y - 1), new Position(cur.x, cur.y + 1));
        var validNeighbors = new ArrayList<Position>();

        for (Position pos : neighbors) {
            if (isInBounds(pos, grid) && isLowerOrOneStepUp(pos, cur, grid)) {
                validNeighbors.add(pos);
            }
        }
        return validNeighbors;
    }

    private boolean isInBounds(Position pos, char[][] grid) {
        return pos.x >= 0 && pos.x < grid.length && pos.y >= 0 && pos.y < grid[0].length;
    }

    private boolean isLowerOrOneStepUp(Position step, Position cur, char[][] grid) {
        return grid[cur.x][cur.y] >= grid[step.x][step.y] ||
                grid[cur.x][cur.y] + 1 == grid[step.x][step.y];
    }

    private char[][] loadGrid(String in) {
        String[] lines = in.split("\n");
        char[][] grid = new char[lines.length][lines[0].length()];
        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[row].length(); col++) {
                grid[row][col] = lines[row].charAt(col);
            }
        }
        return grid;
    }

    private Position findStart(char[][] grid) {
        return find(grid, 'S');
    }

    private Position findEnd(char[][] grid) {
        return find(grid, 'E');
    }

    private static Position find(char[][] grid, char target) {
        int foundRow = 0;
        int foundCol = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == target) {
                    foundRow = row;
                    foundCol = col;
                }
            }
        }
        return new Position(foundRow, foundCol);
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day12.txt"));
        Day12 day12 = new Day12();
        System.out.println("part 1: " + day12.getShortestPathLengthPart1(inStr));
        System.out.println("part 2: " + day12.getShortestPathLengthPart2(inStr));
    }

}
