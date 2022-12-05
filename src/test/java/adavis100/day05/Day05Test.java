package adavis100.day05;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static adavis100.day05.Day05.makeStack;
import static org.assertj.core.api.Assertions.assertThat;

class Day05Test {
    //    [D]
    //[N] [C]
    //[Z] [M] [P]
    // 1   2   3
    private List<Deque<Character>> crates = List.of(makeStack(), makeStack('N', 'Z'), makeStack('D', 'C', 'M'), makeStack('P'));

    private List<String> moves = List.of("move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2");

    private Day05 day05 = new Day05();

    @Test
    void solvesExample1() {
        crates = day05.rearrange(crates, moves);
        String result = day05.getTopCrates(crates);
        assertThat(result).isEqualTo("CMZ");
    }

    @Test
    void solvesExample2() {
        crates = day05.rearrangeInOrder(crates, moves);
        String result = day05.getTopCrates(crates);
        assertThat(result).isEqualTo("MCD");
    }

}