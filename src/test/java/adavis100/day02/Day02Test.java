package adavis100.day02;

import adavis100.day02.Day02;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day02Test {
    @Test
    void scoresLine() {
        var day2 = new Day02();
        var lines = List.of("A Y", "B X", "C Z");
        int score = day2.score(lines);
        assertThat(score).isEqualTo(15);
    }

    @Test
    void scoresLinesPart2() {
        var day2 = new Day02();
        var lines = List.of("A Y", "B X", "C Z");
        int score = day2.score2(lines);
        assertThat(score).isEqualTo(12);
    }
}