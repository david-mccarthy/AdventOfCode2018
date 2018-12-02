package com.davemac.aoc.day1;

import com.davemac.aoc.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * https://adventofcode.com/2018/day/1/
 */
public class ChronalCalibration {
    /**
     * Part 1
     *
     * @param file Your input file
     * @return The end frequency.
     */
    public int solvePartOne(File file) {
        Scanner scanner = FileUtils.getScanner(file);
        int total = 0;
        while (scanner.hasNextLine()) {
            total += (scanner.nextInt());
        }

        return total;
    }

    /**
     * Part two.
     * @param file Your input file.
     * @return The first duplicate frequency.
     */
    public Integer solvePartTwo(File file) {
        return solvePartTwo(0, new ArrayList<>(), file);
    }

    /**
     * Part 2.
     *
     * @param frequency   The initial frequency.
     * @param frequencies The list of frequencies found so far.
     * @param file        Your input list.
     * @return The first duplicate frequency.
     */
    private Integer solvePartTwo(Integer frequency, List<Integer> frequencies, File file) {
        Scanner scanner = FileUtils.getScanner(file);
        while (scanner.hasNextLine()) {
            frequency += (scanner.nextInt());
            if (frequencies.contains(frequency)) {
                return frequency;
            } else {
                frequencies.add(frequency);
            }
        }
        return solvePartTwo(frequency, frequencies, file);
    }
}
