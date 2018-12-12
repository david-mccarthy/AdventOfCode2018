package com.davemac.aoc.day5;

import com.davemac.aoc.TestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

public class AlchemicalReductionTest {

    private static final File INPUT_FILE = new File("src/test/data/day5/input.txt");
    private static final File SAMPLE_FILE = new File("src/test/data/day5/sample.txt");
    private static AlchemicalReduction alchemicalReduction;
    @BeforeClass
    public static void setupClass(){
           alchemicalReduction = new AlchemicalReduction();
    }

    @Test
    public void testPartOne(){
        int s = alchemicalReduction.solvePartOne(INPUT_FILE);
        TestUtils.report("5","1",Integer.toString(s));
    }

    @Test
    public void testPartTwo(){
        int s = alchemicalReduction.solvePartTwo(INPUT_FILE);
        TestUtils.report("5","2", Integer.toString(s));
    }

    @Test
    public void testPartOneWithSampleData() {
        int s = alchemicalReduction.solvePartOne(SAMPLE_FILE);
        System.out.println(s);
    }

}