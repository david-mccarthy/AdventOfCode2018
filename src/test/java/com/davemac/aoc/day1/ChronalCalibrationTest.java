package com.davemac.aoc.day1;

import com.davemac.aoc.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class ChronalCalibrationTest {
    private static final File INPUT_FILE = new File("src/test/data/day1/input.txt");
    private ChronalCalibration chronalCalibration;

    @Before
    public void setup() {
        this.chronalCalibration = new ChronalCalibration();
    }

    @Test
    public void getSolutionPartOne() {
        int result = chronalCalibration.solvePartOne(INPUT_FILE);
        TestUtils.report("1", "1", Integer.toString(result));
    }

    @Test
    public void getSolutionPartTwo() {
        int result = chronalCalibration.solvePartTwo(INPUT_FILE);
        TestUtils.report("1", "2", Integer.toString(result));
    }
}