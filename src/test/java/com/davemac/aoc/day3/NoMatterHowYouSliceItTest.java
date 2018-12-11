package com.davemac.aoc.day3;

import com.davemac.aoc.TestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

public class NoMatterHowYouSliceItTest {
    private static final File INPUT_FILE = new File("src/test/data/day3/input.txt");
    private static NoMatterHowYouSliceIt noMatterHowYouSliceIt;

    @BeforeClass
    public static void setupClass() {
        noMatterHowYouSliceIt = new NoMatterHowYouSliceIt(INPUT_FILE);
    }

    @Test
    public void testPartOne () {
        long result = noMatterHowYouSliceIt.solvePartOne();
        TestUtils.report("3", "1", Long.toString(result));
    }
    @Test
    public void testPartTwo () {
        String result = noMatterHowYouSliceIt.solvePartTwo();
        TestUtils.report("3", "2", result);
    }
}
