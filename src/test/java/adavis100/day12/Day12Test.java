package adavis100.day12;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {
    String in = """
                Sabqponm
                abcryxxl
                accszExk
                acctuvwj
                abdefghi""";
    @Test
    void solvesExample1() {
        Day12 day12 = new Day12();
        int moves = day12.getShortestPathLengthPart1(in);
        assertThat(moves).isEqualTo(31);
    }

    @Test
    void solvesExample2() {
        Day12 day12 = new Day12();
        int moves = day12.getShortestPathLengthPart2(in);
        assertThat(moves).isEqualTo(29);
    }
}