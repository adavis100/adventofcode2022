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
    Coord headPos = new Coord(0, 0);
    Coord tailPos = new Coord(0, 0);

    Day09() {
        visited.add(new Coord(0,0));
    }

    int simulateMoves(List<String> moves) {
        for (String move : moves) {
            String dir = move.split(" ")[0];
            int spaces = Integer.parseInt(move.split(" ")[1]);
            move(dir, spaces);
        }
        return visited.size();
    }

    void move(String dir, int spaces) {
        int xmod = 0, ymod = 0;
        switch (dir) {
            case "R" -> xmod = 1;
            case "L" -> xmod = -1;
            case "U" -> ymod = 1;
            case "D" -> ymod = -1;
        }
        for (int i = 0; i < spaces; i++) {
            headPos = new Coord(headPos.x() + xmod, headPos.y() + ymod);
            tailPos = catchUp(headPos, tailPos);
            visited.add(tailPos);
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
        System.out.println("part 1: " + day09.simulateMoves(lines));
//        System.out.println("part 2: " + day09.getMaxScenicScore());
    }
}
