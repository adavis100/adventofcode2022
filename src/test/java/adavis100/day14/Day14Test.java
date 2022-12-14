package adavis100.day14;

import org.junit.jupiter.api.Test;

import static adavis100.day14.Day14.*;
import static org.assertj.core.api.Assertions.assertThat;

class Day14Test {
    @Test
    void solvesExample1() {
        String in = "498,4 -> 498,6 -> 496,6\n" +
                "503,4 -> 502,4 -> 502,9 -> 494,9";
        assertThat(solvePart1(in)).isEqualTo(24);
    }

}