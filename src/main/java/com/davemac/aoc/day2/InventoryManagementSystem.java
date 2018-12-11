package com.davemac.aoc.day2;

import com.davemac.aoc.utils.FileUtils;

import java.io.File;
import java.util.*;

public class InventoryManagementSystem {
    /**
     * Part one.
     *
     * @param file Your input file.
     * @return The number of 2 matches multiplied by the number of 3 matches.
     */
    public int solvePartOne(File file) {
        Scanner scanner = FileUtils.getScanner(file);
        int twos = 0;
        int threes = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Map<Character, Integer> counts = count(line);
            if (counts.containsValue(2)) {
                twos = twos + 1;
            }
            if (counts.containsValue(3)) {
                threes = threes + 1;
            }
        }
        scanner.close();
        return twos * threes;
    }

    private Map<Character, Integer> count(String line) {
        char[] chars = line.toCharArray();
        Map<Character, Integer> counts = new HashMap<>();
        for (Character c : chars) {
            if (counts.containsKey(c)) {
                Integer count = counts.get(c);
                count += 1;
                counts.replace(c, count);
            } else {
                counts.put(c, 1);
            }
        }

        return counts;
    }

    /**
     * Part two.
     *
     * @param file Your input file.
     * @return The matched string, with the difference removed.
     */
    public String solvePartTwo(File file) {
        Scanner scanner = FileUtils.getScanner(file);
        List<String> strings = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String next = scanner.next();
            String match = findMatch(strings, next);
            if (match != null) {
                return match;
            } else {
                strings.add(next);
            }
        }

        return "No match.";
    }

    private String findMatch(List<String> strings, String next) {
        for (String s : strings) {
            String match = match(s, next);

            if (match != null) {
                return match;
            }
        }

        return null;
    }

    private String match(String s1, String s2) {
        int diffs = 0;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) {
                diffs++;
            } else {
                s.append(c1[i]);
            }
            if (diffs > 1) {
                return null;
            }
        }
        //Should have already returned null if there's more than 1 diff.
        assert (diffs == 1);

        return s.toString();
    }
}
