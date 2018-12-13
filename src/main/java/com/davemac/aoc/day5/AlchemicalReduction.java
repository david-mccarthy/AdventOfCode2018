package com.davemac.aoc.day5;

import com.davemac.aoc.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class AlchemicalReduction {
    private String[] split;

    /**
     * Constructor.
     * Read in the input chars and store in an array.
     *
     * @param file Your input file.
     */
    public AlchemicalReduction(File file) {
        Scanner scanner = FileUtils.getScanner(file);
        String input = scanner.nextLine();
        scanner.close();
        split = input.split("");

    }

    /**
     * Part 1.
     *
     * @return The size of the processed input string.
     */
    public int solvePartOne() {
        return process(split).size();
    }

    /**
     * Part 2.
     *
     * @return The size of the shortest processed input string.
     */
    public int solvePartTwo() {
        String[] chars = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int shortestReduction = Integer.MAX_VALUE;
        for (String c : chars) {
            Stack<String> s = process(split, c);
            List<String> strings = new ArrayList<>(s);
            Stack<String> reducedStack = process(strings.toArray(new String[0]));
            int size = reducedStack.size();

            if (size < shortestReduction) {
                shortestReduction = size;
            }
        }
        
        return shortestReduction;
    }

    /**
     * Create a stack of the given input, where all characters matching {@code match}, ignoring case, are removed.
     *
     * @param list  Input list.
     * @param match The character to match.
     * @return Processed stack.
     */
    private Stack<String> process(String[] list, String match) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            stack.push(s);

            if (stack.size() >= 1) {
                String a = stack.pop();

                if (!(match.equalsIgnoreCase(a))) {
                    stack.push(a);
                }
            }
        }

        return stack;
    }

    /**
     * Create a stack from the given input, where there are no adjacent characters that match.
     * A match is in the form aA, where the character is the same, but the case is different.
     *
     * @param list The input list.
     * @return Processed stack.
     */
    private Stack<String> process(String[] list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            stack.push(s);
            if (stack.size() >= 2) {
                String a = stack.pop();
                String b = stack.pop();
                if (!matches(a, b)) {
                    stack.push(b);
                    stack.push(a);
                }
            }
        }
        return stack;
    }

    /**
     * Do the 2 provided inputs match.
     * Inputs match if they are the same character, in different case, i.e. Aa, bB but not AA or bb.
     *
     * @param s1 Input 1.
     * @param s2 Input 2.
     * @return True if matching inputs.
     */
    private boolean matches(String s1, String s2) {
        if (isUpperCase(s1)) {
            return (!isUpperCase(s2)) && s1.equalsIgnoreCase(s2);
        } else {
            return (isUpperCase(s2)) && s1.equalsIgnoreCase(s2);
        }
    }

    /**
     * Is the given input in upper case?
     *
     * @param s Input.
     * @return True if the input is in upper case.
     */
    private boolean isUpperCase(String s) {
        return s.toUpperCase().equals(s);
    }
}
