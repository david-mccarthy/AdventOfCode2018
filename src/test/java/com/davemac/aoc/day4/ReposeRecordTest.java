package com.davemac.aoc.day4;

import com.davemac.aoc.TestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

public class ReposeRecordTest {
    private static final File INPUT_FILE = new File("src/test/data/day4/input.txt");
    private static ReposeRecord reposeRecord;
    @BeforeClass
    public static void setup(){
        reposeRecord = new ReposeRecord();
    }

    @Test
    public void testPartOne() {
        int result = reposeRecord.solvePartOne(INPUT_FILE);
        TestUtils.report("4", "1", Integer.toString(result));
    }
}
