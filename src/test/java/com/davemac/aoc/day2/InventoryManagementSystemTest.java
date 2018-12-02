package com.davemac.aoc.day2;

import com.davemac.aoc.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class InventoryManagementSystemTest {
    private static final File INPUT_FILE = new File("src/test/data/day2/input.txt");
    private InventoryManagementSystem inventoryManagementSystem;

    @Before
    public void setup() {
        inventoryManagementSystem = new InventoryManagementSystem();
    }

    @Test
    public void testSolvePartOne() {
        int checksum = inventoryManagementSystem.solvePartOne(INPUT_FILE);
        TestUtils.report("2", "1", Integer.toString(checksum));
    }

    @Test
    public void testSolvePartTwo() {
        String solution = inventoryManagementSystem.solvePartTwo(INPUT_FILE);
        TestUtils.report("2", "2", solution);
    }
}