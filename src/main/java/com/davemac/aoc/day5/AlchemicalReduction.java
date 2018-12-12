package com.davemac.aoc.day5;

import com.davemac.aoc.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class AlchemicalReduction {

    public int solvePartOne(File file) {
        Scanner scanner = FileUtils.getScanner(file);
        String input = scanner.nextLine();
        scanner.close();
        String[] split = input.split("");

        return process(split).size();
    }

    public int solvePartTwo(File file) {
        Scanner scanner = FileUtils.getScanner(file);
        String input = scanner.nextLine();

        scanner.close();
        String[] split = input.split("");

        return processPartTwo(split);
    }

    private int processPartTwo(String[] l) {
        String[] chars = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int shortestReduction = Integer.MAX_VALUE;
        for (String c : chars) {
            Stack<String> s = process(l, c);
            List<String> strings = new ArrayList<>(s);
            Stack<String> reducedStack = process(strings.toArray(new String[0]));
            int size = reducedStack.size();
            if (size < shortestReduction) {
                shortestReduction = size;
            }
        }
        return shortestReduction;
    }

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

    private boolean matches(String s1, String s2) {
        if (isUpperCase(s1)) {
            return (!isUpperCase(s2)) && s1.equalsIgnoreCase(s2);
        } else {
            return (isUpperCase(s2)) && s1.equalsIgnoreCase(s2);
        }
    }

    private boolean isUpperCase(String s) {
        return s.toUpperCase().equals(s);
    }

}
