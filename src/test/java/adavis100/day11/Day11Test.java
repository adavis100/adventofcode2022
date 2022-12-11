package adavis100.day11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {
    String exampleInput = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3

            Monkey 1:
              Starting items: 54, 65, 75, 74
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 2
                If false: throw to monkey 0

            Monkey 2:
              Starting items: 79, 60, 97
              Operation: new = old * old
              Test: divisible by 13
                If true: throw to monkey 1
                If false: throw to monkey 3

            Monkey 3:
              Starting items: 74
              Operation: new = old + 3
              Test: divisible by 17
                If true: throw to monkey 0
                If false: throw to monkey 1""";

    @Test
    void loadsMonkeys() {
        Day11 day11 = new Day11();
        List<Day11.Monkey> monkeys = day11.load(exampleInput);
        assertThat(monkeys).hasSize(4);
        assertThat(monkeys.get(3)).isEqualTo(new Day11.Monkey(List.of(74L),
                new Day11.Operation(Day11.OperationType.ADD, 3),
                new Day11.Test(17, 0, 1)));
    }

    @Test
    void runsOneRound() {
        Day11 day11 = new Day11();
        List<Day11.Monkey> monkeys = day11.load(exampleInput);
        day11.runOneRound(monkeys, true);
        assertThat(monkeys.get(0).items).contains(20L, 23L, 27L, 26L);
        assertThat(monkeys.get(1).items).contains(2080L, 25L, 167L, 207L, 401L, 1046L);
    }

    @Test
    void runsExample1() {
        Day11 day11 = new Day11();
        assertThat(day11.runPart1(exampleInput)).isEqualTo(10605);
    }

    @Test
    void runsExampleTwo() {
        Day11 day11 = new Day11();
        assertThat(day11.runPart2(exampleInput)).isEqualTo(2713310158L);
    }
}