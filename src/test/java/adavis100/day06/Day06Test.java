package adavis100.day06;

import org.junit.jupiter.api.Test;

import static adavis100.day06.Day06.*;
import static org.assertj.core.api.Assertions.assertThat;

class Day06Test {
    @Test
    void solvesExamplesPart1() {
        assertThat(getMarkerCount("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 4)).isEqualTo(7);
        assertThat(getMarkerCount("bvwbjplbgvbhsrlpgdmjqwftvncz", 4)).isEqualTo(5);
        assertThat(getMarkerCount("nppdvjthqldpwncqszvftbrmjlhg", 4)).isEqualTo(6);
        assertThat(getMarkerCount("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4)).isEqualTo(10);
        assertThat(getMarkerCount("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4)).isEqualTo(11);
    }

    @Test
    void solvesExamplesPart2() {
        assertThat(getMarkerCount("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14)).isEqualTo(19);
    }
}