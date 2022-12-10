package adavis100.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static adavis100.day10.Day10.*;
import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {
    List<String> cmds = List.of("addx 15", "addx -11", "addx 6", "addx -3", "addx 5", "addx -1", "addx -8",
            "addx 13", "addx 4", "noop", "addx -1", "addx 5", "addx -1", "addx 5", "addx -1", "addx 5", "addx -1",
            "addx 5", "addx -1", "addx -35", "addx 1", "addx 24", "addx -19", "addx 1", "addx 16", "addx -11", "noop",
            "noop", "addx 21", "addx -15", "noop", "noop", "addx -3", "addx 9", "addx 1", "addx -3", "addx 8", "addx 1",
            "addx 5", "noop", "noop", "noop", "noop", "noop", "addx -36", "noop", "addx 1", "addx 7", "noop", "noop",
            "noop", "addx 2", "addx 6", "noop", "noop", "noop", "noop", "noop", "addx 1", "noop", "noop", "addx 7", "addx 1",
            "noop", "addx -13", "addx 13", "addx 7", "noop", "addx 1", "addx -33", "noop", "noop", "noop", "addx 2", "noop",
            "noop", "noop", "addx 8", "noop", "addx -1", "addx 2", "addx 1", "noop", "addx 17", "addx -9", "addx 1",
            "addx 1", "addx -3", "addx 11", "noop", "noop", "addx 1", "noop", "addx 1", "noop", "noop", "addx -13",
            "addx -19", "addx 1", "addx 3", "addx 26", "addx -30", "addx 12", "addx -1", "addx 3", "addx 1", "noop",
            "noop", "noop", "addx -9", "addx 18", "addx 1", "addx 2", "noop", "noop", "addx 9", "noop", "noop", "noop",
            "addx -1", "addx 2", "addx -37", "addx 1", "addx 3", "noop", "addx 15", "addx -21", "addx 22", "addx -6",
            "addx 1", "noop", "addx 2", "addx 1", "noop", "addx -10", "noop", "noop", "addx 20", "addx 1", "addx 2",
            "addx 2", "addx -6", "addx -11", "noop", "noop", "noop");

    @Test
    void runs3CommandProgram() {
        var cmds = List.of("noop", "addx 3", "addx -5");
        int[] registerVals = runProgram(cmds);
        assertThat(registerVals[1]).isEqualTo(1);
        assertThat(registerVals[2]).isEqualTo(1);
        assertThat(registerVals[3]).isEqualTo(1);
        assertThat(registerVals[4]).isEqualTo(4);
        assertThat(registerVals[5]).isEqualTo(4);
        assertThat(registerVals[6]).isEqualTo(-1);
    }

    @Test
    void solvesExample1() {
        int[] registerVals = runProgram(cmds);

        assertThat(registerVals[20] * 20).isEqualTo(420);
        assertThat(registerVals[60] * 60).isEqualTo(1140);
        assertThat(registerVals[100] * 100).isEqualTo(1800);
        assertThat(registerVals[140] * 140).isEqualTo(2940);
        assertThat(registerVals[180] * 180).isEqualTo(2880);
        assertThat(registerVals[220] * 220).isEqualTo(3960);

        assertThat(getSumOfSignalStrengths(cmds)).isEqualTo(13140);
    }

    @Test
    void solvesExample2() {
        var image = getImage(cmds);

        assertThat(image.get(0)).isEqualTo("##..##..##..##..##..##..##..##..##..##..");
        assertThat(image.get(1)).isEqualTo("###...###...###...###...###...###...###.");
        assertThat(image.get(2)).isEqualTo("####....####....####....####....####....");
        assertThat(image.get(3)).isEqualTo("#####.....#####.....#####.....#####.....");
        assertThat(image.get(4)).isEqualTo("######......######......######......####");
        assertThat(image.get(5)).isEqualTo("#######.......#######.......#######.....");
    }
}