package com.davemac.aoc.day4;

import com.davemac.aoc.utils.FileUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day 4.
 */
public class ReposeRecord {
    public static final String FALLS_ASLEEP_INSTRUCTION = "falls asleep";
    public static final String WAKES_UP_INSTRUCTION = "wakes up";
    private List<Instruction> instructions;
    private Map<Integer, Guard> guards;

    /**
     * Constructor.
     * Read in the instructions once and store them, sorted, locally.
     *
     * @param file Your input file.
     */
    public ReposeRecord(File file) {
        instructions = new ArrayList<>();
        Scanner scanner = FileUtils.getScanner(file);

        while (scanner.hasNext()) {
            instructions.add(new Instruction(scanner.nextLine()));
        }
        Collections.sort(instructions);

        scanner.close();
        logGuardSleepingTimes();
    }

    /**
     * Part 1.
     *
     * @return The guard id multiplied by the minute he is asleep the longest.
     */
    public int solvePartOne() {
        int longestSleep = -1;
        Guard sleepiestGuard = null;

        for (Map.Entry<Integer, Guard> e : guards.entrySet()) {
            Guard guard = e.getValue();
            if (guard.totalSleep > longestSleep) {
                longestSleep = guard.totalSleep;
                sleepiestGuard = guard;
            }
        }
        int largestSize = -1;
        int time = -1;

        for (Map.Entry<Integer, List> t : sleepiestGuard.sleepingTimes.entrySet()) {
            List listOfDays = t.getValue();
            int size = listOfDays.size();

            if (size > largestSize) {
                largestSize = size;
                time = t.getKey();
            }
        }

        return time * sleepiestGuard.id;
    }

    /**
     * Part 2.
     *
     * @return The guard id who is sleeps most in a particular minute multiplied by that minute.
     */
    public int solvePartTwo() {
        int longestGuardSleepingMinute = -1;
        Guard longestSleepingGuard = null;

        for (Map.Entry<Integer, Guard> e : guards.entrySet()) {
            Guard guard = e.getValue();
            int longestSleepingMinute = -1;
            int longestSleep = -1;

            for (Map.Entry<Integer, List> t : guard.sleepingTimes.entrySet()) {
                int size = t.getValue().size();

                if (size > longestSleep) {
                    longestSleep = size;
                    longestSleepingMinute = t.getKey();
                }
            }

            if (longestSleepingMinute > longestGuardSleepingMinute) {
                longestSleepingGuard = guard;
                longestGuardSleepingMinute = longestSleepingMinute;
            }
        }

        return longestSleepingGuard.id * longestGuardSleepingMinute;
    }

    /**
     * Log all guard sleeping times by looping through the instructions and processing each.
     */
    private void logGuardSleepingTimes() {
        guards = new HashMap<>();
        int lastGuardId = -1;
        LocalDateTime start = null;

        for (Instruction i : instructions) {
            int guardId = i.getGuardId();
            if (guardId > -1) {
                Guard guard = guards.get(guardId);
                if (guard == null) {
                    guard = new Guard(guardId);
                    guards.put(guardId, guard);
                }
                lastGuardId = guardId;
            } else {
                Guard guard = guards.get(lastGuardId);
                if (FALLS_ASLEEP_INSTRUCTION.equals(i.instruction)) {
                    start = i.dateTime;
                } else if (WAKES_UP_INSTRUCTION.equals(i.instruction)) {
                    guard.addSleepingTimes(start, i.dateTime);
                }
            }
        }
    }

    /**
     * Private class to keep track of Guard information.
     */
    private class Guard {
        int id;
        Map<Integer, List> sleepingTimes;
        int totalSleep;

        /**
         * Constructor.
         *
         * @param id The guard id.
         */
        public Guard(int id) {
            this.id = id;
            this.sleepingTimes = new HashMap<>();
            this.totalSleep = 0;
        }

        /**
         * Add the sleeping times to the guards information.
         *
         * @param start The time the guard falls asleep.
         * @param end   The time the guard wakes up.
         */
        public void addSleepingTimes(LocalDateTime start, LocalDateTime end) {
            int startMinute = start.getMinute();
            int endMinute = end.getMinute();
            int duration = end.getMinute() - start.getMinute();
            totalSleep += duration;

            for (int i = startMinute; i < endMinute; i++) {
                List list = sleepingTimes.get(i);

                if (list == null) {
                    sleepingTimes.put(i, new ArrayList<>());
                    list = sleepingTimes.get(i);
                }

                list.add(start.format(DateTimeFormatter.BASIC_ISO_DATE));
                sleepingTimes.put(i, list);
            }
        }
    }

    /**
     * Private class for instruction data.
     */
    private class Instruction implements Comparable<Instruction> {
        private static final String PATTERN = "^\\[(\\d+?-\\d+?-\\d+?\\s\\d+?:\\d+?)]\\s(.*)$";
        private static final String BEGIN_SHIFT_PATTERN = "^Guard\\s#(\\d+?) begins shift$";
        private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
        LocalDateTime dateTime;
        String instruction;

        /**
         * Constructor.
         * Parse the instrution string and store the time of the instruction and the text separately.
         *
         * @param s The instruction string.
         */
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

        /**
         * Get the guard id from an instruction message.
         * If there is no Id, then return -1.
         *
         * @return Guard id or -1 if no guard id exists.
         */
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
