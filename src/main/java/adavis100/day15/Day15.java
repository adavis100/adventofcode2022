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
            var coord = new Coord(x,y);
            for (Sensor sensor : sensors) {
                if (coord.equals(sensor.closestBeacon)) {
                    break;
                } else if (dist(sensor.position, coord) <= dist(sensor.position, sensor.closestBeacon)) {
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

    static long solvePart2(String in, int maxCoord) {
        var sensors = loadSensors(in);
        boolean found = false;
        int minx = 0;
        int maxx = maxCoord;
        int miny = 0;
        int maxy = maxCoord;
        boolean goingLeft = true, goingRight = false, goingUp = false, goingDown = false;
        int x = 0, y = 0;
        long freq = 0;
        long iterations = 0;
        while (!found) {
            Coord coord = new Coord(x, y);
            boolean couldHaveBeacon = true;
            for (Sensor sensor : sensors) {
                if (coord.equals(sensor.closestBeacon) ||
                        dist(sensor.position, coord) <= dist(sensor.position, sensor.closestBeacon)) {
                    couldHaveBeacon = false;
                    break;
                }
            }
            if (couldHaveBeacon) {
                found = true;
                freq = tuningFrequency(coord);
            }
            if (goingLeft && x < maxx) {
                x++;
            } else if (goingLeft) {
                goingLeft = false;
                goingDown = true;
                y++;
                miny++;
            } else if (goingDown && y < maxy) {
                y++;
            } else if (goingDown) {
                goingDown = false;
                goingRight = true;
                maxx--;
                x--;
            } else if (goingRight && x > minx) {
                x--;
            } else if (goingRight) {
                goingRight = false;
                goingUp = true;
                maxy--;
                y--;
            } else if (goingUp && y > miny) {
                y--;
            } else {
                goingUp = false;
                goingLeft = true;
                minx++;
                x++;
            }
            iterations++;
            if (iterations % 1000000000L == 0) {
                System.out.println(iterations + ": minx=" + minx + ", maxx=" + maxx + ", miny=" + miny + ", maxy=" + maxy);
            }
        }
        return freq;
    }

    private static long tuningFrequency(Coord coord) {
        return coord.x * 4000000L + coord.y;
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day15.txt"));
        System.out.println("part 1: " + solvePart1(inStr, 2000000));
        System.out.println("part 2: " + solvePart2(inStr, 4000000));
    }
}
