package adavis100.day07;

import org.junit.jupiter.api.Test;

import java.util.List;
import static adavis100.day07.Day07.Dir;

import static org.assertj.core.api.Assertions.assertThat;

class Day07Test {
    List<String> lines = List.of("$ cd /",
            "$ ls",
            "dir a",
            "14848514 b.txt",
            "8504156 c.dat",
            "dir d",
            "$ cd a",
            "$ ls",
            "dir e",
            "29116 f",
            "2557 g",
            "62596 h.lst",
            "$ cd e",
            "$ ls",
            "584 i",
            "$ cd ..",
            "$ cd ..",
            "$ cd d",
            "$ ls",
            "4060174 j",
            "8033020 d.log",
            "5626152 d.ext",
            "7214296 k");

    @Test
    void buildsTree() {
        Day07 day07 = new Day07();
        Dir root = day07.buildTree(lines);
        assertThat(root.size).isEqualTo(48381165);
    }

    @Test
    void solvesExample1() {
        Day07 day07 = new Day07();
        assertThat(day07.getSumPart1(lines)).isEqualTo(95437);
    }

    @Test
    void solvesExample2() {
        Day07 day07 = new Day07();
        assertThat(day07.getSmallestDirToDelete(lines)).isEqualTo(24933642);
    }
}