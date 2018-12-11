package com.davemac.aoc.day3;

import com.davemac.aoc.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day 3.
 */
public class NoMatterHowYouSliceIt {

    private Map<Integer, Map<Integer, List>> fabric;

    /**
     * Constructor.
     * Build up the fabric based on the list of claims in your input file.
     * Doing this in the constructor to reuse for parts one and two.
     *
     * @param file Your input file.
     */
    public NoMatterHowYouSliceIt(File file) {
        this.fabric = new HashMap<>();
        Scanner scanner = FileUtils.getScanner(file);

        while (scanner.hasNext()) {
            Claim claim = new Claim(scanner.nextLine());
            for (int i = claim.top; i < claim.top + claim.height; i++) {
                for (int j = claim.left; j < claim.left + claim.width; j++) {
                    populateFabricPosition(claim, i, j);
                }
            }
        }
        scanner.close();
    }

    /**
     * Add a claims id to a specific inch of fabric.
     *
     * @param claim The claim information.
     * @param i     The horizontal position in the fabric of the claimed spot.
     * @param j     The vertical position in the fabric of the claimed spot.
     */
    private void populateFabricPosition(Claim claim, int i, int j) {
        Map<Integer, List> integerListMap = this.fabric.get(i);

        if (integerListMap == null) {
            this.fabric.put(i, new HashMap<>());
            integerListMap = this.fabric.get(i);
        }

        List list = integerListMap.get(j);

        if (list == null) {
            integerListMap.put(j, new ArrayList<>());
            list = integerListMap.get(j);
        }

        list.add(claim.id);
    }

    /**
     * Part one.
     *
     * @return The number of inches of fabric with two or more competing claims.
     */
    public int solvePartOne() {
        int inches = 0;
        for (Map.Entry<Integer, Map<Integer, List>> f1: fabric.entrySet()) {
            for (Map.Entry<Integer, List> f2: f1.getValue().entrySet()) {
                if (f2.getValue().size() >= 2) {
                    inches++;
                }
            }
        }

        return inches;
    }

    /**
     * Part two.
     *
     * @return The string id of the single claim not overlapping with any other.
     */
    public String solvePartTwo() {
        Set<String> singleClaims = new HashSet<>();
        Set<String> duplicateClaims = new HashSet<>();
        for (Integer k1 : fabric.keySet()) {
            Map<Integer, List> integerListMap = fabric.get(k1);
            for (Integer k2 : integerListMap.keySet()) {
                List<String> list = integerListMap.get(k2);
                if (list.size() == 1) {
                    singleClaims.add(list.get(0));
                } else {
                    for (String s : list) {
                        duplicateClaims.add(s);
                    }
                }
            }
        }
        singleClaims.removeAll(duplicateClaims);

        assert singleClaims.size() == 1;

        return new ArrayList<>(singleClaims).get(0);
    }

    /**
     * Private class to represent Claim information.
     */
    private class Claim {
        private static final String PATTERN = "^\\#(\\d+?)\\s\\@\\s(\\d+?),(\\d+?)\\:\\s(\\d+?)x(\\d+?)$";
        public String id;
        public int height;
        public int width;
        public int top;
        public int left;

        /**
         * Constructor taking in a claim line and parsing it's information locally.
         *
         * @param claim The claim string.
         */
        public Claim(String claim) {
            Pattern pattern = Pattern.compile(PATTERN);
            Matcher matcher = pattern.matcher(claim);
            if (matcher.matches()) {
                this.id = matcher.group(1);
                this.left = Integer.parseInt(matcher.group(2));
                this.top = Integer.parseInt(matcher.group(3));
                this.width = Integer.parseInt(matcher.group(4));
                this.height = Integer.parseInt(matcher.group(5));
            }
        }
    }
}
