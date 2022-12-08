package adavis100.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day08 {
    int[][] grid;

    Day08(List<String> lines) {
        this.grid = makeGrid(lines);
    }

    int[][] makeGrid(List<String> lines) {
        int[][] grid = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }
        return grid;
    }

    int countVisible() {
        boolean[][] visible = new boolean[grid.length][grid[0].length];
        // set outer trees as visible
        for (int i = 0; i < grid.length; i++) {
            visible[0][i] = true;
            visible[visible.length - 1][i] = true;
            visible[i][0] = true;
            visible[i][visible[0].length - 1] = true;
        }

        // traverse from left side
        for (int row = 1; row < grid.length - 1; row++) {
            int biggest = grid[row][0];
            for (int col = 1; col < grid[row].length - 1; col++) {
                if (grid[row][col] > biggest) {
                    biggest = grid[row][col];
                    visible[row][col] = true;
                }
            }
        }

        // traverse from right side
        for (int row = 1; row < grid.length - 1; row++) {
            int biggest = grid[row][grid[row].length - 1];
            for (int col = grid[row].length - 2; col >= 1; col--) {
                if (grid[row][col] > biggest) {
                    biggest = grid[row][col];
                    visible[row][col] = true;
                }
            }
        }

        // traverse from top
        for (int col = 1; col < grid[0].length - 1; col++) {
            int biggest = grid[0][col];
            for (int row = 1; row < grid.length - 1; row++) {
                if (grid[row][col] > biggest) {
                    biggest = grid[row][col];
                    visible[row][col] = true;
                }
            }
        }

        // traverse from bottom
        for (int col = 1; col < grid[0].length - 1; col++) {
            int biggest = grid[grid.length - 1][col];
            for (int row = grid.length - 2; row >= 1; row--) {
                if (grid[row][col] > biggest) {
                    biggest = grid[row][col];
                    visible[row][col] = true;
                }
            }
        }

        // sum up all visible trees
        int total = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (visible[row][col]) {
                    total++;
                }
            }
        }
        return total;
    }

    int getScenicScore(int row, int col) {
        // look up
        int up = 1;
        for (int i = row - 1; i > 0; i--) {
            if (grid[i][col] < grid[row][col]) {
                up++;
            } else {
                break;
            }
        }

        // look down
        int down = 1;
        for (int i = row + 1; i < grid.length - 1; i++) {
            if (grid[i][col] < grid[row][col]) {
                down++;
            } else {
                break;
            }
        }

        // look left
        int left = 1;
        for (int i = col - 1; i > 0; i--) {
            if (grid[row][i] < grid[row][col]) {
                left++;
            } else {
                break;
            }
        }

        // look right
        int right = 1;
        for (int i = col + 1; i < grid[row].length - 1; i++) {
            if (grid[row][i] < grid[row][col]) {
                right++;
            } else {
                break;
            }
        }

        return up * down * left * right;
    }

    public int getMaxScenicScore() {
        int max = 0;
        for (int row = 1; row < grid.length - 1; row++) {
            for (int col = 1; col < grid[row].length - 1; col++) {
                max = Integer.max(getScenicScore(row, col), max);
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day08.txt"));
        var lines = Arrays.asList(inStr.split("\n"));
        Day08 day08 = new Day08(lines);
        System.out.println("part 1: " + day08.countVisible());
        System.out.println("part 2: " + day08.getMaxScenicScore());
    }
}
