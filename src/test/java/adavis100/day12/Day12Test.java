package adavis100.day12;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {
    @Test
    void solvesExample1() {
        String in = """
                Sabqponm
                abcryxxl
                accszExk
                acctuvwj
                abdefghi""";

        Day12 day12 = new Day12();
        int moves = day12.getShortestPathLength(in);
        assertThat(moves).isEqualTo(31);
    }
}