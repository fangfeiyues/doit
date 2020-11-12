//package com.fang.doit.algo.sort.two;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
///**
// * @author by Feiyue on 2019/11/3 4:26 PM
// */
//public class FastCollinearPoints {
//
//
//    private ArrayList<LineSegment> lineSegmentList = new ArrayList<LineSegment>();
//
//    /**
//     * finds all line segments containing 4 or more points
//     *
//     * @param points
//     */
//    public FastCollinearPoints(Point[] points) {
//
//        if (points == null) {
//            throw new IllegalArgumentException();
//        }
//
//        Arrays.sort(points);
//
//        for (int i = 0; i < points.length; i++) {
//
//            if (points[i] == null) {
//                throw new IllegalArgumentException();
//            }
//
//            if (i < points.length - 1 && points[i].compareTo(points[i + 1]) == 0) {
//                throw new IllegalArgumentException();
//            }
//        }
//
//        // sort by n2logn
//        int length = points.length;
//        Point[] temps = Arrays.copyOf(points, length);
//        int current = 0;
//
//        while (current < length) {
//            Point base = temps[current++];
//            Point min = base;
//            Point max = base;
//            int count = 2;
//
//            Arrays.sort(points, base.slopeOrder());
//
//            for (int i = 0; i < length - 1; i++) {
//
//                double s1 = base.slopeTo(points[i]);
//                double s2 = base.slopeTo(points[i + 1]);
//                if (s1 == s2) {
//                    count++;
//                    if (max.compareTo(points[i + 1]) < 0) {
//                        max = points[i + 1];
//                    } else if (min.compareTo(points[i + 1]) > 0) {
//                        min = points[i + 1];
//                    }
//
//                    if (i == length - 2 && count >= 4 && base.compareTo(min) == 0) {
//                        LineSegment lineSegment = new LineSegment(min, max);
//                        lineSegmentList.add(lineSegment);
//                    }
//
//                } else {
//
//                    if (count >= 4 && base.compareTo(min) == 0) {
//                        LineSegment lineSegment = new LineSegment(min, max);
//                        lineSegmentList.add(lineSegment);
//                    }
//
//                    if (base.compareTo(points[i + 1]) > 0) {
//                        min = points[i + 1];
//                        max = base;
//                    } else {
//                        min = base;
//                        max = points[i + 1];
//                    }
//                    count = 2;
//
//                }
//            }
//
//        }
//
//    }
//
//    /**
//     * the number of line segments
//     *
//     * @return
//     */
//    public int numberOfSegments() {
//        return lineSegmentList.size();
//    }
//
//    /**
//     * the line segments
//     *
//     * @return
//     */
//    public LineSegment[] segments() {
//        LineSegment[] lineSegments = new LineSegment[numberOfSegments()];
//        for (int i = 0; i < lineSegmentList.size(); i++) {
//            lineSegments[i] = lineSegmentList.get(i);
//        }
//        return lineSegments;
//    }
//
////    //main
////    public static void main(String[] args) {
////        In in = new In("src/week3/input9.txt");
////        int n = in.readInt();
////        StdOut.println("total " + n + " points");
////        Point[] points = new Point[n];
////        for (int i = 0; i < n; i++) {
////            int x = in.readInt();
////            int y = in.readInt();
////            StdOut.println("(" + x + "," + y + ")");
////            points[i] = new Point(x, y);
////        }
////
////        //draw the points
////        StdDraw.enableDoubleBuffering();
////        StdDraw.setXscale(0, 32768);
////        StdDraw.setYscale(0, 32768);
////        StdDraw.setPenColor(StdDraw.RED);
////        StdDraw.setPenRadius(0.01);
////        for (Point p : points) {
////            p.draw();
////        }
////        StdDraw.show();
////
////        //print and draw the line segments
////        FastCollinearPoints collinear = new FastCollinearPoints(points);
////        StdOut.println(collinear.numberOfSegments());
////        for (LineSegment segment : collinear.segments()) {
////            StdOut.println(segment);
////            segment.draw();
////        }
////        StdDraw.show();
////    }
//}
