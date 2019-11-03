package com.fang.doit.algo.sort.two;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author by Feiyue on 2019/11/3 4:26 PM
 */
public class FastCollinearPoints {


    private ArrayList<LineSegment> lineSegmentList = new ArrayList<LineSegment>();

    /**
     * finds all line segments containing 4 or more points
     *
     * @param points
     */
    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException();
        }

        Arrays.sort(points);

        ArrayList<Double> slopes = new ArrayList<Double>();

        Point min = points[0];

        for (int i = 1; i < points.length; i++) {

            if (points[i] == null) {
                throw new IllegalArgumentException();
            }

            if (points[i - 1].compareTo(points[i]) == 0) {
                throw new IllegalArgumentException();
            }

            slopes.add(min.slopeTo(points[i]));

        }

        // sort by n2logn (merge or quick)



    }

    /**
     * the number of line segments
     *
     * @return
     */
    public int numberOfSegments() {
        return lineSegmentList.size();
    }

    /**
     * the line segments
     *
     * @return
     */
    public LineSegment[] segment() {
        LineSegment[] lineSegments = new LineSegment[numberOfSegments()];
        for (int i = 0; i < lineSegmentList.size(); i++) {
            lineSegments[i] = lineSegmentList.get(i);
        }
        return lineSegments;
    }
}
