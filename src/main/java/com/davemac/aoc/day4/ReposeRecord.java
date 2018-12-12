package com.davemac.aoc.day4;

import com.davemac.aoc.utils.FileUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReposeRecord {
    //Store the sorted instructions in a list.
    private List<Instruction> instructions;

    /**
     * Constructor.
     * Read in the instructions once and store them, sorted locally.
     *
     * @param file Your input file.
     */
    public ReposeRecord(File file) {
        instructions = new ArrayList<>();
        Scanner scanner = FileUtils.getScanner(file);

        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            instructions.add(new Instruction(s));
        }
        Collections.sort(instructions);

        scanner.close();
    }

    public int solvePartOne() {
        int j = 0;
        List<Guard> guards = new ArrayList<>();
        Guard guard = null;
        for (Instruction i : instructions) {
            int guardId = i.getGuardId();
            if (guardId > -1) {
                j++;
                guard = new Guard(guardId);
                guards.add(guard);
            } else {

                System.out.println("\t" + guard.id + ", " + i.instruction + " at " + i.dateTime);
            }
        }
        System.out.println(guards.size() + ": " + j);

        return 0;
    }

    private class Guard {
        int id;

        public Guard(int id) {
            this.id = id;
        }
    }

    private class Instruction implements Comparable<Instruction> {
        private static final String PATTERN = "^\\[(\\d+?\\-\\d+?\\-\\d+?\\s\\d+?\\:\\d+?)\\]\\s(.*)$";
        private static final String BEGIN_SHIFT_PATTERN = "^Guard\\s#(\\d+?) begins shift$";
        private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
        LocalDateTime dateTime;
        String instruction;
        String guardId;

        public Instruction(String s) {
            Pattern pattern = Pattern.compile(PATTERN);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                String time = matcher.group(1);
                String message = matcher.group(2);
                this.dateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
                this.instruction = message;
            }
        }

        @Override
        public int compareTo(Instruction compare) {
            return this.dateTime.compareTo(compare.dateTime);
        }

        public int getGuardId() {
            Pattern pattern = Pattern.compile(BEGIN_SHIFT_PATTERN);
            Matcher matcher = pattern.matcher(this.instruction);
            if (matcher.matches()) {
                return Integer.parseInt(matcher.group(1));
            }

            return -1;
        }
    }
}
