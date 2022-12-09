package adavis100.day09;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static adavis100.day09.Day09.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day09Test {
    List<String> in = List.of("R 4", "U 4", "L 3", "D 1", "R 4", "D 1", "L 5", "R 2");

    @ParameterizedTest(name = "{index} => dir={0}, count={1}, startHead={2}, expectedPos={3}")
    @MethodSource
    void doesSingleMove(String dir, int count, Coord startHead, Coord expectedPos) {
        Day09 day09 = new Day09();
        day09.headPos = startHead;
        day09.move(dir, count);
        assertThat(day09.tailPos).isEqualTo(expectedPos);
    }

    private static Stream<Arguments> doesSingleMove() {
        return Stream.of(
                arguments("L", 1, new Coord(0, 0), new Coord(0, 0)),
                arguments("L", 2, new Coord(0, 0), new Coord(-1, 0)),
                arguments("R", 2, new Coord(0, 0), new Coord(1, 0)),
                arguments("U", 2, new Coord(0, 0), new Coord(0, 1)),
                arguments("D", 2, new Coord(0, 0), new Coord(0, -1)),
                arguments("L", 2, new Coord(1, 0), new Coord(0, 0)),
                arguments("R", 2, new Coord(1, 0), new Coord(2, 0)),
                arguments("U", 2, new Coord(1, 0), new Coord(1, 1)),
                arguments("D", 2, new Coord(0, 1), new Coord(0, 0)),
                arguments("R", 4, new Coord(0, 0), new Coord(3, 0))
        );
    }

    @Test
    void solvesFirstExample() {
        Day09 day09 = new Day09();
        assertThat(day09.simulateMoves(in)).isEqualTo(13);
    }
}