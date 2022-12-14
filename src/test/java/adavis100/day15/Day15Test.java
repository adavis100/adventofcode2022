package adavis100.day15;

import org.junit.jupiter.api.Test;

import static adavis100.day15.Day15.*;
import static org.assertj.core.api.Assertions.assertThat;

class Day15Test {
    String exampleIn = """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3
            """;
    @Test
    void solvesExample1() {
        assertThat(solvePart1(exampleIn, 10)).isEqualTo(26);
    }

    @Test
    void solvesExample2() {
        assertThat(solvePart2(exampleIn, 20)).isEqualTo(56000011);
    }

}