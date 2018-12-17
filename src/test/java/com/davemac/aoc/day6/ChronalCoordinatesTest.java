package com.davemac.aoc.day6;

import com.davemac.aoc.TestUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class ChronalCoordinatesTest {
    private static final File INPUT = new File("src/test/data/day6/input.txt");
    private static final File SAMPLE = new File("src/test/data/day6/sample.txt");
    private static ChronalCoordinates chronalCoordinates;

    @BeforeClass
    public static void setupClass() {
        chronalCoordinates = new ChronalCoordinates(INPUT);
    }

    @Test
    public void testSolvePartOne() {
        int i = chronalCoordinates.solvePartOne();
        TestUtils.report("6", "1", Integer.toString(i));
    }
    @Test
    public void testSolvePartTwo() {
        int i = chronalCoordinates.solvePartTwo();
        TestUtils.report("6", "2", Integer.toString(i));
    }

    @Ignore
    @Test
    public void testSolveWithSampleInput() {
        int i = new ChronalCoordinates(SAMPLE).solvePartOne();
        TestUtils.report("6", "1", Integer.toString(i));
    }
}
