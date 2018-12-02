package com.davemac.aoc.day1;

import com.davemac.aoc.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class ChronalCalibrationTest {
    private ChronalCalibration chronalCalibration;

    @Before
    public void setup() {
        this.chronalCalibration = new ChronalCalibration();
    }

    @Test
    public void getSolutionPartOne() {
        int result = chronalCalibration.solvePartOne(new File("src/test/data/day1/input.txt"));
        TestUtils.report("1", "1", Integer.toString(result));
    }

    @Test
    public void getSolutionPartTwo() {
        int result = chronalCalibration.solvePartTwo(0, new ArrayList<>(), new File("src/test/data/day1/input.txt"));
        TestUtils.report("1", "2", Integer.toString(result));
    }
}