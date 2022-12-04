package adavis100.day04;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day04Test {
    private List<String> example = List.of("2-4,6-8",
            "2-3,4-5",
            "5-7,7-9",
            "2-8,3-7",
            "6-6,4-6",
            "2-6,4-8");

    @Test
    void solvesPart1Example() {
        var day4 = new Day04();
        assertThat(day4.countFullyContains(example)).isEqualTo(2);
    }
}