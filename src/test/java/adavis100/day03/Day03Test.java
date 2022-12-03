package adavis100.day03;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day03Test {
    private String []lines = new String[] {"vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"};

    @Test
    void solvesExample() {
        var day03 = new Day03();
        assertThat(day03.getPriorities(lines)).isEqualTo(157);
    }

    @Test
    void solvesExample2() {
        var day03 = new Day03();
        assertThat(day03.getPrioritiesPart2(lines)).isEqualTo(70);
    }
}