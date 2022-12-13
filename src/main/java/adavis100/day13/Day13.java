package adavis100.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day13 {


    static class Packet {
        List<Packet> elements;
        int val;

        public Packet() {
            this.elements = new ArrayList<>();
            this.val = -1;
        }

        public static Packet of(int n) {
            var packet = new Packet();
            packet.val = n;
            return packet;
        }

        public static Packet of(List<Packet> elements) {
            var packet = new Packet();
            packet.elements = elements;
            return packet;
        }

        void add(Packet packet) {
            if (elements == null) {
                elements = new ArrayList<>();
            }
            elements.add(packet);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Packet packet = (Packet) o;
            return val == packet.val && Objects.equals(elements, packet.elements);
        }

        @Override
        public int hashCode() {
            return Objects.hash(elements, val);
        }
    }

    record ParseResponse(Packet packet, String remaining) {
    }

    public Packet parsePacket(String s) {
        var parsed = parsePacketHelper(s.substring(1), new Packet());
        return parsed.packet;
    }

    private ParseResponse parsePacketHelper(String s, Packet p) {
        char head = s.charAt(0);
        if (Character.isDigit(head) && Character.isDigit(s.charAt(1))) {
            int n = Integer.parseInt(s.substring(0, 2));
            p.add(Packet.of(n));
            return parsePacketHelper(s.substring(2), p);
        } else if (Character.isDigit(head)) {
            int n = Integer.parseInt(s.substring(0, 1));
            p.add(Packet.of(n));
            return parsePacketHelper(s.substring(1), p);
        } else if (head == '[') {
            var parsed = parsePacketHelper(s.substring(1), new Packet());
            p.elements.add(parsed.packet);
            return parsePacketHelper(parsed.remaining, p);
        } else if (head == ',') {
            return parsePacketHelper(s.substring(1), p);
        } else {
            return new ParseResponse(p, s.substring(1));
        }
    }

    public int compare(Packet l, Packet r) {
        if (l.val >= 0 && r.val >= 0) { // both nums
            return l.val - r.val;
        } else if (l.val < 0 && r.val < 0) {
            for (int i = 0; i < Math.min(l.elements.size(), r.elements.size()); i++) {
                int comp = compare(l.elements.get(i), r.elements.get(i));
                if (comp != 0) {
                    return comp;
                }
            }
            return l.elements.size() - r.elements.size();
        } else if (l.val >= 0) {
            return compare(Packet.of(List.of(Packet.of(l.val))), r);
        } else { // r.val >= 0
            return compare(l, Packet.of(List.of(Packet.of(r.val))));
        }
    }

    public int solvePart1(String in) {
        String[] lines = in.split("\n");
        List<Packet> left = new ArrayList<>();
        List<Packet> right = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (!lines[i].isEmpty()) {
                left.add(parsePacket(lines[i++]));
                right.add(parsePacket(lines[i]));
            }
        }

        int total = 0;
        for (int i = 0; i < left.size(); i++) {
            int cmp = compare(left.get(i), right.get(i));
            if (cmp < 0) {
                total += i + 1;
            }
        }
        return total;
    }

    public int solvePart2(String in) {
        String[] lines = in.split("\n");
        List<Packet> packets = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (!lines[i].isEmpty()) {
                packets.add(parsePacket(lines[i]));
            }
        }
        Packet divider1 = Packet.of(List.of(Packet.of(2)));
        Packet divider2 = Packet.of(List.of(Packet.of(6)));
        packets.add(divider1);
        packets.add(divider2);
        Collections.sort(packets, (p1, p2) -> compare(p1, p2));
        int index1 = packets.indexOf(divider1) + 1;
        int index2 = packets.indexOf(divider2) + 1;
        return index1 * index2;
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day13.txt"));
        Day13 day13 = new Day13();
        System.out.println("part 1: " + day13.solvePart1(inStr));
        System.out.println("part 2: " + day13.solvePart2(inStr));
    }
}
