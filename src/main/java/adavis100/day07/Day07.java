package adavis100.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 {
    public Dir buildTree(List<String> lines) {
        Dir cwd = null, root = null;
        for (String line : lines) {
            if (line.equals("$ cd /")) {
                root = new Dir("/");
                cwd = root;
            } else if (line.equals("$ cd ..")) {
                cwd = cwd.parent;
            } else if (line.startsWith("$ cd")) {
                String dest = line.replace("$ cd ", "");
                cwd = cwd.children.stream().filter(dir -> dir.name.equals(dest)).findFirst().orElseThrow();
            } else if (line.startsWith("dir")) {
                String dirName = line.replace("dir ", "");
                cwd.children.add(new Dir(dirName, cwd));
            } else if (Character.isDigit(line.charAt(0))) {
                String[] parts = line.split(" ");
                String size = parts[0];
                cwd.size += Integer.parseInt(size);
            }
        }

        getTotalSizes(root);

        return root;
    }

    private int getTotalSizes(Dir dir) {
        for (Dir child : dir.children) {
            dir.size += getTotalSizes(child);
        }
        return dir.size;
    }

    public int getSumPart1(List<String> lines) {
        Dir root = buildTree(lines);
        return countDirsWithSizeMax(root, 100000);
    }

    private int countDirsWithSizeMax(Dir dir, int max) {
        int total = 0;
        if (dir.size <= max) {
            total += dir.size;
        }
        for (Dir child : dir.children) {
            total += countDirsWithSizeMax(child, max);
        }
        return total;
    }

    public int getSmallestDirToDelete(List<String> lines) {
        Dir root = buildTree(lines);
        int free = 70000000 - root.size;
        int needed = 30000000 - free;
        getSmallestDirBiggerThan(root, needed);
        return smallest;
    }

    private int smallest = Integer.MAX_VALUE;

    private void getSmallestDirBiggerThan(Dir dir, int needed) {
       if (dir.size >= needed && dir.size < smallest) {
           smallest = dir.size;
       }
       for (Dir child : dir.children) {
           getSmallestDirBiggerThan(child, needed);
       }
    }

    static class Dir {
        String name;
        int size;
        List<Dir> children;

        Dir parent;

        Dir(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        Dir(String name, Dir parent) {
            this(name);
            this.parent = parent;
        }
    }

    public static void main(String[] args) throws IOException {
        var inStr = Files.readString(Path.of("src/main/resources/day07.txt"));
        var lines = Arrays.asList(inStr.split("\n"));
        Day07 day07 = new Day07();
        System.out.println("part 1: " + day07.getSumPart1(lines));
        System.out.println("part 2: " + day07.getSmallestDirToDelete(lines));
    }
}
