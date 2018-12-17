package com.davemac.aoc.day6;

import com.davemac.aoc.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChronalCoordinates {
    private static final String PATTERN = "^(\\d+?),\\s(\\d+?)$";
    private int MIN_X = Integer.MAX_VALUE;
    private int MAX_X = Integer.MIN_VALUE;
    private int MIN_Y = Integer.MAX_VALUE;
    private int MAX_Y = Integer.MIN_VALUE;

    private List<Point> points;
    private Map<Integer, Map<Integer, Point>> graph;

    public ChronalCoordinates(File file) {
        graph = new HashMap<>();
        points = new ArrayList<>();
        Scanner scanner = FileUtils.getScanner(file);
        Pattern pattern = Pattern.compile(PATTERN);
        while (scanner.hasNext()) {
            //Plot the points at this stage on our graph.
            String e = scanner.nextLine();
            Matcher matcher = pattern.matcher(e);
            if (matcher.matches()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                updateXY(x, y);
                Point p = new Point(x, y);
                plotPoint(x, y, p);
                points.add(p);
            }
        }
        scanner.close();

        setupGraph();
    }

    private void plotPoint(int x, int y, Point p) {
        Map<Integer, Point> integerPointMap = graph.get(x);
        if (integerPointMap == null) {
            graph.put(x, new HashMap<>());
            integerPointMap = graph.get(x);
        }
        integerPointMap.put(y, p);
    }

    private void updateXY(int x, int y) {
        if (x < MIN_X) {
            MIN_X = x;
        }
        if (x > MAX_X) {
            MAX_X = x;
        }
        if (y < MIN_Y) {
            MIN_Y = y;
        }
        if (y > MAX_Y) {
            MAX_Y = y;
        }
    }

    public void setupGraph() {
        //Loop through all points. Calculate the closest point to this point.
        //When looping if a point has min_x, max_x, min_y or max_y points, then it's border is infinite. Mark so we can ignore it to find
        // the largest area later.
        for (int i = MIN_X; i <= MAX_X; i++) {
            for (int j = MIN_Y; j <= MAX_Y; j++) {
                Map<Integer, Point> x = graph.get(i);
                if (x == null) {
                    graph.put(i, new HashMap<>());
                    x = graph.get(i);
                }
                Point point = x.get(j);
                if (point == null) {
                    point = new Point(i, j);
                    x.put(j, point);
                }
                if (i == MIN_X || i == MAX_X || j == MIN_Y || j == MAX_Y) {
                    point.infiniteBorder = true;
                }
                for (Point p : points) {
                    point.addDistance(p, calculateDistance(p.x, p.y, point.x, point.y));
                }
            }
        }
    }

    public int solvePartTwo(){
        int size = 0;
        for (int i = MIN_Y; i <= MAX_Y; i++) {
            for (int j = MIN_X; j <= MAX_X; j++) {
                Point point = graph.get(j).get(i);
                if(point.getTotalDistances() < 10000) {
                    size++;
                }
            }
        }
        return size;
    }

    public int solvePartOne() {
        Map<Point, Integer> neighbours = new HashMap<>();
        for (int i = MIN_Y; i <= MAX_Y; i++) {
            for (int j = MIN_X; j <= MAX_X; j++) {
                Point point = graph.get(j).get(i);
                if (point.hasUniqueDistance()) {
                    Point closest = point.getClosest();
                    if(i == MIN_Y || i == MAX_Y || j == MIN_X || j == MAX_X){
                        closest.infiniteBorder = true;
                    }
                    Integer count = neighbours.get(closest);
                    if (count == null) {
                        neighbours.put(closest, 1);
                    } else {
                        neighbours.replace(closest, count + 1);
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Point, Integer> p : neighbours.entrySet()) {
            if (p.getValue() > max && !p.getKey().infiniteBorder) {
                max = p.getValue();
            }
        }

        return max;
    }

    private int calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private class Point {
        int x;
        int y;
        Map<Point, Integer> distances;
        int totalDistance;
        boolean infiniteBorder = false;

        public Point(int x, int y) {
            this.distances = new HashMap<>();
            this.totalDistance = 0;
            this.x = x;
            this.y = y;
        }

        public void addDistance(Point p, int distance) {
            distances.put(p, distance);
            totalDistance+=distance;
        }

        public boolean hasUniqueDistance() {
            int shortestDistance = Integer.MAX_VALUE;
            for (Map.Entry<Point, Integer> e : distances.entrySet()) {
                if (e.getValue() < shortestDistance) {
                    shortestDistance = e.getValue();
                }
            }
            int count = 0;
            for (Map.Entry<Point, Integer> e : distances.entrySet()) {
                if (e.getValue() == shortestDistance) {
                    count++;
                }
            }

            return count == 1;
        }

        public Point getClosest() {
            int shortestDistance = Integer.MAX_VALUE;
            Point p = null;
            for (Map.Entry<Point, Integer> e : distances.entrySet()) {
                if (e.getValue() < shortestDistance) {
                    shortestDistance = e.getValue();
                    p = e.getKey();
                }
            }
            return p;
        }

        public int getTotalDistances(){
            return totalDistance;
        }
    }
}
