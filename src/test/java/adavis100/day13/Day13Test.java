package adavis100.day13;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static adavis100.day13.Day13.Packet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day13Test {
    String in = """
            [1,1,3,1,1]
            [1,1,5,1,1]
                        
            [[1],[2,3,4]]
            [[1],4]
                        
            [9]
            [[8,7,6]]
                        
            [[4,4],4,4]
            [[4,4],4,4,4]
                        
            [7,7,7,7]
            [7,7,7]
                        
            []
            [3]
                        
            [[[]]]
            [[]]
                        
            [1,[2,[3,[4,[5,6,7]]]],8,9]
            [1,[2,[3,[4,[5,6,0]]]],8,9]
            """;

    @ParameterizedTest(name = "{index} => s={0}, expectedPacket={1}")
    @MethodSource
    void loadsPacket(String s, Packet expectedPacket) {
        Day13 day13 = new Day13();
        Packet packet = day13.parsePacket(s);
        assertThat(packet).isEqualTo(expectedPacket);
    }

    private static Stream<Arguments> loadsPacket() {
        return Stream.of(
                arguments("[1]", Packet.of(List.of(Packet.of(1)))),
                arguments("[2,[3,4]]", Packet.of(List.of(Packet.of(2), Packet.of(List.of(Packet.of(3), Packet.of(4)))))),
                arguments("[]", new Packet()),
                arguments("[[[]]]", Packet.of(List.of(Packet.of(List.of(new Packet()))))),
                arguments("[1,[2,[3,[4]]]]", Packet.of(List.of(Packet.of(1), Packet.of(List.of(Packet.of(2), Packet.of(List.of(Packet.of(3), Packet.of(List.of(Packet.of(4))))))))))
        );
    }

    @ParameterizedTest(name = "{index} => left={0}, right={1}, rightOrder={2}")
    @MethodSource
    void comparesPackets(String left, String right, boolean rightOrder) {
        Day13 day13 = new Day13();
        Packet l = day13.parsePacket(left);
        Packet r = day13.parsePacket(right);
        int order = day13.compare(l, r);
        if (rightOrder) {
            assertThat(order).isLessThan(0);
        } else {
            assertThat(order).isGreaterThan(0);
        }
    }

    private static Stream<Arguments> comparesPackets() {
        return Stream.of(
                arguments("[1,1,3,1,1]", "[1,1,5,1,1]", true),
                arguments("[[1],[2,3,4]]", "[[1],4]", true),
                arguments("[9]", "[[8,7,6]]", false),
                arguments("[[4,4],4,4]", "[[4,4],4,4,4]", true),
                arguments("[7,7,7,7]", "[7,7,7]", false),
                arguments("[]", "[3]", true),
                arguments("[[[]]]", "[[]]", false),
                arguments("[1,[2,[3,[4,[5,6,7]]]],8,9]", "[1,[2,[3,[4,[5,6,0]]]],8,9]", false),
                arguments("[[7,[[4,10],5],7,6,1],[[],10,6,[]],[[7,[3],[7,6,1,8,1],5,[]],[5,2,[7,2,8,3],7]]]",
                        "[[4,10,9]]", false),
                arguments("[[[],5,[[5,2],[2,3,2,8],[2,5,6,2],6],[2,[1,7,0],[8,9,8,8,7],1],4],[8,[2,1,[]],9]]",
                        "[[10]]", true)
        );
    }

    @Test
    void solvesFirstExample() {
        Day13 day13 = new Day13();
        assertThat(day13.solvePart1(in)).isEqualTo(13);
    }

    @Test
    void solvesSecondExample() {
        Day13 day13 = new Day13();
        assertThat(day13.solvePart2(in)).isEqualTo(140);
    }
}