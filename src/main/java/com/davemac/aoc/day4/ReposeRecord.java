package com.davemac.aoc.day4;

import com.davemac.aoc.utils.FileUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReposeRecord {

    public int solvePartOne(File file) {
        Scanner scanner = FileUtils.getScanner(file);
        Map<LocalDateTime, String> instructions = new HashMap<>();
        while(scanner.hasNext()) {
            String s = scanner.nextLine();
            Pattern pattern = Pattern.compile("^\\[(\\d+?\\-\\d+?\\-\\d+?\\s\\d+?\\:\\d+?)\\]\\s(.*)$");
            Matcher matcher = pattern.matcher(s);
            if(matcher.matches()){
                String dateTime = matcher.group(1);
                dateTime.replace(" ", "T");
                String message = matcher.group(2);
                LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                instructions.put(localDateTime, message);
            }
        }
        ArrayList<LocalDateTime> keyList = new ArrayList<>(instructions.keySet());
        Collections.sort(keyList);
        for(LocalDateTime key: keyList){
            String message = instructions.get(key);
            //process the message.
        }
        scanner.close();
        return 0;
    }

}
