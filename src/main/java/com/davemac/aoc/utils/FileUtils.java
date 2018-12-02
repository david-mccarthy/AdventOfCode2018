package com.davemac.aoc.utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {

    /**
     * Get a scanner for a given file, or handle exception, print an error and return null
     * @param file Input file
     * @return Scanner
     */
    public static Scanner getScanner(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (IOException e) {
            System.err.println("input error");
        }
        return scanner;
    }
}
