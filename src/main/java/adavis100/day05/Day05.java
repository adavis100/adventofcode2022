package adavis100.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day05 {
//[N]     [Q]         [N]
//[R]     [F] [Q]     [G] [M]
//[J]     [Z] [T]     [R] [H] [J]
//[T] [H] [G] [R]     [B] [N] [T]
//[Z] [J] [J] [G] [F] [Z] [S] [M]
//[B] [N] [N] [N] [Q] [W] [L] [Q] [S]
//[D] [S] [R] [V] [T] [C] [C] [N] [G]
//[F] [R] [C] [F] [L] [Q] [F] [D] [P]
// 1   2   3   4   5   6   7   8   9
    private List<Deque<Character>> crates = List.of(makeStack(),
            makeStack('N', 'R', 'J', 'T', 'Z', 'B', 'D', 'F'),
            makeStack('H', 'J', 'N', 'S', 'R'),
            makeStack('Q', 'F', 'Z', 'G', 'J', 'N', 'R', 'C'),
            makeStack('Q', 'T', 'R', 'G', 'N', 'V', 'F'),
            makeStack('F', 'Q', 'T', 'L'),
            makeStack('N', 'G', 'R', 'B', 'Z', 'W', 'C', 'Q'),
            makeStack('M', 'H', 'N', 'S', 'L', 'C', 'F'),
            makeStack('J', 'T', 'M', 'Q', 'N', 'D'),
            makeStack('S', 'G', 'P'));

    static Deque<Character> makeStack(char... crates) {
        var deque = new ArrayDeque<Character>();
        for (char c : crates) {
            deque.add(c);
        }
        return deque;
    }

    private class Move {
        int count;
        int from;
        int to;
    }

    private Move parseLine(String line) {
        var move = new Move();
        Pattern pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Bad input: " + line);
        }
        move.count = Integer.parseInt(matcher.group(1));
        move.from = Integer.parseInt(matcher.group(2));
        move.to = Integer.parseInt(matcher.group(3));
        return move;
    }

    public List<Deque<Character>> rearrange(List<Deque<Character>> crates, List<String> moves) {
        crates = copy(crates);
        for (String line : moves) {
            Move move = parseLine(line);
            for (int i = 0; i < move.count; i++) {
                char c = crates.get(move.from).pop();
                crates.get(move.to).push(c);
            }
        }
        return crates;
    }

    private List<Deque<Character>> copy(List<Deque<Character>> crates) {
        var newCrates = new ArrayList<Deque<Character>>();
        for (var stack: crates) {
            newCrates.add(new ArrayDeque<>(stack));
        }
        return newCrates;
    }

    public List<Deque<Character>> rearrangeInOrder(List<Deque<Character>> crates, List<String> moves) {
        crates = copy(crates);
        for (String line : moves) {
            Move move = parseLine(line);
            var list = new ArrayList<Character>();
            for (int i = 0; i < move.count; i++) {
                list.add(crates.get(move.from).pop());
            }
            for (int i = list.size() - 1; i >= 0; i--) {
                crates.get(move.to).push(list.get(i));
            }
        }
        return crates;
    }

    public String getTopCrates(List<Deque<Character>> crates) {
        return crates.stream()
                .map(x -> x.stream().findFirst().orElse(' '))
                .map(Objects::toString)
                .collect(Collectors.joining())
                .trim();
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day05.txt"));
        var lines = Arrays.asList(inStr.split("\n"));
        Day05 day05 = new Day05();
        System.out.println("part 1: " + day05.getTopCrates(day05.rearrange(day05.crates, lines)));
        System.out.println("part 2: " + day05.getTopCrates(day05.rearrangeInOrder(day05.crates, lines)));
    }

}
