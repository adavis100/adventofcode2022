package adavis100.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day08Test {
    private List<String> lines = List.of("30373", "25512", "65332", "33549", "35390");
    @Test
    void buildsGrid() {
        Day08 day08 = new Day08(lines);
        assertThat(day08.grid).hasDimensions(5,5);
        assertThat(day08.grid[0][0]).isEqualTo(3);
        assertThat(day08.grid[4][4]).isEqualTo(0);
    }

    @Test
    void countsVisible() {
        Day08 day08 = new Day08(lines);
        assertThat(day08.countVisible()).isEqualTo(21);
    }

    @Test
    void getsScenicScore() {
        Day08 day08 = new Day08(lines);
        assertThat(day08.getScenicScore(1, 2)).isEqualTo(4);
    }

    @Test
    void getsMaxScenicScore() {
        Day08 day08 = new Day08(lines);
        assertThat(day08.getMaxScenicScore()).isEqualTo(8);
    }
}