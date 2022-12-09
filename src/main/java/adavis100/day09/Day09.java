package adavis100.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day09 {
    record Coord(int x, int y) {}
    Set<Coord> visited = new HashSet<>();

    Day09() {
        visited.add(new Coord(0,0));
    }

    int simulateMovesPart1(List<String> moves) {
        return simulateMoves(moves, new Coord[] {new Coord(0, 0), new Coord(0,0)});
    }

    int simulateMovesPart2(List<String> moves) {
        var knots = new Coord[10];
        for (int i = 0; i < 10; i++) {
            knots[i] = new Coord(0, 0);
        }
        return simulateMoves(moves, knots);
    }

    int simulateMoves(List<String> moves, Coord[] knots) {
        for (String move : moves) {
            String dir = move.split(" ")[0];
            int spaces = Integer.parseInt(move.split(" ")[1]);
            move(dir, spaces, knots);
        }
        return visited.size();
    }

    void move(String dir, int spaces, Coord[] knots) {
        int xmod = 0, ymod = 0;
        switch (dir) {
            case "R" -> xmod = 1;
            case "L" -> xmod = -1;
            case "U" -> ymod = 1;
            case "D" -> ymod = -1;
        }
        for (int i = 0; i < spaces; i++) {
            knots[knots.length - 1] = new Coord(knots[knots.length - 1].x() + xmod, knots[knots.length - 1].y() + ymod);
            for (int knotIndex = knots.length - 1; knotIndex > 0; knotIndex--) {
                knots[knotIndex - 1] = catchUp(knots[knotIndex], knots[knotIndex - 1]);
            }
            visited.add(knots[0]);
        }
    }

    private Coord catchUp(Coord headPos, Coord tailPos) {
        if (touching(headPos, tailPos)) {
            return tailPos;
        } else if (headPos.x() == tailPos.x() + 2 && headPos.y() == tailPos.y()) { // directly right
            return new Coord(tailPos.x() + 1, tailPos.y());
        } else if (headPos.x() == tailPos.x() - 2 && headPos.y() == tailPos.y()) { // directly left
            return new Coord(tailPos.x() - 1, tailPos.y());
        } else if (headPos.x() == tailPos.x() && headPos.y() == tailPos.y() + 2) { // directly up
            return new Coord(tailPos.x(), tailPos.y() + 1);
        } else if (headPos.x() == tailPos.x() && headPos.y() == tailPos.y() - 2) { // directly down
            return new Coord(tailPos.x(), tailPos.y() - 1);
        } else if (headPos.x() > tailPos.x() && headPos.y() > tailPos.y()) { // up and to right
            return new Coord(tailPos.x() + 1, tailPos.y() + 1);
        } else if (headPos.x() > tailPos.x() && headPos.y() < tailPos.y()) { // down and to right
            return new Coord(tailPos.x() + 1, tailPos.y() - 1);
        } else if (headPos.x() < tailPos.x() && headPos.y() > tailPos.y()) { // up and to left
            return new Coord(tailPos.x() - 1, tailPos.y() + 1);
        } else if (headPos.x() < tailPos.x() && headPos.y() < tailPos.y()) { // down and to left
            return new Coord(tailPos.x() - 1, tailPos.y() - 1);
        }
        return tailPos;
    }

    private boolean touching(Coord headPos, Coord tailPos) {
        return Math.abs(headPos.x() - tailPos.x()) <= 1 && Math.abs(headPos.y() - tailPos.y()) <= 1;
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day09.txt"));
        var lines = Arrays.asList(inStr.split("\n"));
        Day09 day09 = new Day09();
        System.out.println("part 1: " + day09.simulateMovesPart1(lines));
        day09 = new Day09();
        System.out.println("part 2: " + day09.simulateMovesPart2(lines));
    }
}
