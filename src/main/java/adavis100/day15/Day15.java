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
        List<Sensor> sensors = loadSensors(in);
        long tuningFreq = 0;
        for (int y = 0; y < maxCoord; y++) {
            tuningFreq = findBeacon(sensors, y, maxCoord);
            if (tuningFreq != 0) {
                break;
            }
        }
        return tuningFreq;
    }

    private static long findBeacon(List<Sensor> sensors, int row, int maxCol) {
        for (Sensor sensor : sensors) {
            if (sensorHitsRow(sensor, row)) {
               int xmin = getSensorMinCol(sensor, row);
               int xmax = getSensorMaxCol(sensor, row);
               var minCoord = new Coord(xmin - 1, row);
                var maxCoord = new Coord(xmax + 1, row);
               if (xmin - 1 > 0 && !detected(sensors, minCoord)) {
                   return tuningFrequency(minCoord);
               }
               if (xmax + 1 <= maxCol && !detected(sensors, maxCoord)) {
                    return tuningFrequency(maxCoord);
               }
            }
        }
        return 0;
    }

    private static boolean detected(List<Sensor> sensors, Coord coord) {
        for (Sensor sensor : sensors) {
            if (dist(sensor.position, coord) <= dist(sensor.position, sensor.closestBeacon)) {
                return true;
            }
        }
        return false;
    }

    private static boolean sensorHitsRow(Sensor sensor, int row) {
        Coord coord = new Coord(sensor.position.x, row);
        return sensor.position.y == row || dist(sensor.position, coord) <= dist(sensor.position, sensor.closestBeacon);
    }

    private static int getSensorMinCol(Sensor sensor, int row) {
        int dist = dist(sensor.position, sensor.closestBeacon);
        dist -= Math.abs(sensor.position.y - row);
        return sensor.position.x - dist;
    }

    private static int getSensorMaxCol(Sensor sensor, int row) {
        int dist = dist(sensor.position, sensor.closestBeacon);
        dist -= Math.abs(sensor.position.y - row);
        return sensor.position.x + dist;
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
