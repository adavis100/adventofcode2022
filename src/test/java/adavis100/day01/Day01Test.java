package adavis100.day01;

import adavis100.day01.Day01;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day01Test {
    private String exampleCalList = "1000\n" +
            "2000\n" +
            "3000\n" +
            "\n" +
            "4000\n" +
            "\n" +
            "5000\n" +
            "6000\n" +
            "\n" +
            "7000\n" +
            "8000\n" +
            "9000\n" +
            "\n" +
            "10000";

    @Test
    void loadsInputString() {
        var day1 = new Day01();
        List<List<Integer>> elfCalories = day1.loadInputString(exampleCalList);
        assertThat(elfCalories).hasSize(5);
        assertThat(elfCalories.get(0)).hasSize(3);
    }

    @Test
    void loadsInputFile() throws IOException {
        var day1 = new Day01();
        var cals = day1.loadInputFile();
        assertThat(cals).isNotEmpty();
    }

    @Test
    void getsMaxCalories() {
        var day1 = new Day01();
        List<List<Integer>> elfCalories = day1.loadInputString(exampleCalList);
        int max = day1.getMaxCals(elfCalories);
        assertThat(max).isEqualTo(24000);
    }

    @Test
    void getsTop3MaxCaloriesSum() {
        var day1 = new Day01();
        List<List<Integer>> elfCalories = day1.loadInputString(exampleCalList);
        int max = day1.getTop3MaxCals(elfCalories);
        assertThat(max).isEqualTo(45000);
    }
}