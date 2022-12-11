package adavis100.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day11 {
    enum OperationType {MULTIPLY, ADD, SQUARE}

    record Operation(OperationType op, int value) {}
    record Test(int value, int trueTarget, int falseTarget) {}

    static class Monkey {
        List<Long> items;
        Operation operation;
        Test test;
        long inspections;

        public Monkey(List<Long> items, Operation operation, Test test) {
            this.items = items;
            this.operation = operation;
            this.test = test;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Monkey monkey = (Monkey) o;
            return inspections == monkey.inspections && Objects.equals(items, monkey.items) && Objects.equals(operation, monkey.operation) && Objects.equals(test, monkey.test);
        }

        @Override
        public int hashCode() {
            return Objects.hash(items, operation, test, inspections);
        }
    }

    List<Monkey> load(String in) {
        String[] lines = in.split("\n");
        List<Monkey> monkeys = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].startsWith("Monkey")) {
                List<Long> items = parseItems(lines[++i]);
                Operation operation = parseOperation(lines[++i]);
                Test test = parseTest(lines[++i], lines[++i], lines[++i]);
                monkeys.add(new Monkey(items, operation, test));
            }
        }
        return monkeys;
    }

    private List<Long> parseItems(String line) {
        return Arrays.stream(line.replaceAll(".*Starting items: ", "").split(", "))
                .mapToLong(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    private Operation parseOperation(String line) {
        line = line.replaceAll(".*Operation: new = old ", "");
        OperationType operationType;
        int value = 0;
        if (line.startsWith("+ ")) {
            line = line.replace("+ ", "");
            operationType = OperationType.ADD;
            value = Integer.parseInt(line);
        } else if (line.equals("* old")) {
            operationType = OperationType.SQUARE;
        } else {
            line = line.replace("* ", "");
            operationType = OperationType.MULTIPLY;
            value = Integer.parseInt(line);
        }

        return new Operation(operationType, value);
    }

    private Test parseTest(String testStr, String trueStr, String falseStr) {
        int testVal = Integer.parseInt(testStr.replaceAll(".*Test: divisible by ", ""));
        int trueTarget = Integer.parseInt(trueStr.replaceAll(".*If true: throw to monkey ", ""));
        int falseTarget = Integer.parseInt(falseStr.replaceAll(".*If false: throw to monkey ", ""));
        return new Test(testVal, trueTarget, falseTarget);
    }

    void runOneRound(List<Monkey> monkeys, boolean reduceWorry) {
        for (Monkey monkey : monkeys) {
            Operation operation = monkey.operation;
            for (long item : monkey.items) {
                monkey.inspections++;
                switch (operation.op()) {
                    case ADD -> item += operation.value();
                    case MULTIPLY -> item *= operation.value();
                    case SQUARE -> item *= item;
                }
                if (reduceWorry) {
                    item /= 3L;
                }
                if (item % monkey.test.value == 0) {
                    item = item % getDivisibleProduct(monkeys);
                    monkeys.get(monkey.test.trueTarget).items.add(item);
                } else {
                    item = item % getDivisibleProduct(monkeys);
                    monkeys.get(monkey.test.falseTarget).items.add(item);
                }
            }
            monkey.items = new ArrayList<>();
        }
    }

    private int getDivisibleProduct(List<Monkey> monkeys) {
        return monkeys.stream()
                .mapToInt(m -> m.test.value)
                .reduce(1, (a,b) -> a * b);
    }

    long runPart1(String monkeyConfig) {
        return  runRounds(monkeyConfig, 20, true);
    }

    private long runRounds(String monkeyConfig, int numRounds, boolean reduceWorry) {
        var monkeys = load(monkeyConfig);
        for (int i = 0; i < numRounds; i++) {
            runOneRound(monkeys, reduceWorry);
        }
        var counts = monkeys.stream()
                .mapToLong(monkey -> monkey.inspections)
                .sorted()
                .boxed()
                .toList();
        return counts.get(counts.size() - 1) * counts.get(counts.size() - 2);
    }

    public long runPart2(String monkeyConfig) {
        return runRounds(monkeyConfig, 10000, false);
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day11.txt"));
        Day11 day11 = new Day11();
        System.out.println("part 1: " + day11.runPart1(inStr));
        System.out.println("part 2: " + day11.runPart2(inStr));
    }


}
