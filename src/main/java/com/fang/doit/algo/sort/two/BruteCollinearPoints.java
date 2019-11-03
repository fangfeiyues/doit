package com.fang.doit.algo.sort.two;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author by Feiyue on 2019/11/3 4:12 PM
 */
public class BruteCollinearPoints {

    // Map
    private ArrayList<LineSegment> lineSegmentArrayList = new ArrayList<LineSegment>();

    /**
     * finds all line segments containing 4 points
     *
     * @param points
     */
    public BruteCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException();
        }

        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {

            if (points[i] == null) {
                throw new IllegalArgumentException();
            }

            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }

            for (int j = i + 1; j < points.length; j++) {

                double slope = points[i].slopeTo(points[j]);

                for (int k = j + 1; k < points.length; k++) {

                    if (slope == (points[k].slopeTo(points[i]))) {

                        for (int l = k + 1; l < points.length; l++) {

                            if (slope == (points[l].slopeTo(points[i]))) {

                                lineSegmentArrayList.add(new LineSegment(points[i], points[l]));

                            }

                        }

                    }

                }
            }

        }

    }

    /**
     * the number of line segments
     *
     * @return
     */
    public int numberOfSegments() {
        return lineSegmentArrayList.size();
    }

    /**
     * the line segments
     *
     * @return
     */
    public LineSegment[] segment() {
        LineSegment[] lineSegments = new LineSegment[numberOfSegments()];
        for (int i = 0; i < lineSegmentArrayList.size(); i++) {
            lineSegments[i] = lineSegmentArrayList.get(i);
        }
        return lineSegments;
    }
}
