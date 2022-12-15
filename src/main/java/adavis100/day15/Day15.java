package adavis100.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day15 {
    record Coord(int x, int y) {}
    record Sensor(Coord position, Coord closestBeacon) {}

    static int solvePart1(String in, int y) {
        var sensors = loadSensors(in);
        int minX = findMinX(sensors) - 20000000;
        int maxX = findMaxX(sensors) + 20000000;
        int count = 0;
        for (int x = minX; x <= maxX; x++) {
            var pos = new Coord(x,y);
            for (Sensor sensor : sensors) {
                if (pos.equals(sensor.closestBeacon)) {
                    break;
                } else if (dist(sensor.position, pos) <= dist(sensor.position, sensor.closestBeacon)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    private static int dist(Coord coord1, Coord coord2) {
        return Math.abs(coord1.x - coord2.x) + Math.abs((coord1.y - coord2.y));
    }

    static List<Sensor> loadSensors(String in) {
        var beacons = new ArrayList<Sensor>();
        for (String line : in.split("\n")) {
            // e.g.: Sensor at x=96708, y=1131866: closest beacon is at x=-657934, y=1258930
            line = line.replaceAll("Sensor at x=", "");
            line = line.replaceAll(", y=", " ");
            line = line.replaceAll(": closest beacon is at x=", " ");
            var parts = line.split(" ");
            Coord pos = new Coord(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            Coord closestBeacon = new Coord(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
            beacons.add(new Sensor(pos, closestBeacon));
        }
        return beacons;
    }

    private static int findMinX(List<Sensor> sensors) {
        return sensors.stream().mapToInt(i -> i.closestBeacon.x).min().orElseThrow();
    }

    private static int findMaxX(List<Sensor> sensors) {
        return sensors.stream().mapToInt(i -> i.closestBeacon.x).max().orElseThrow();
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day15.txt"));
        System.out.println("part 1: " + solvePart1(inStr, 2000000));
//        System.out.println("part 2: " + solvePart2(inStr));
    }
}
