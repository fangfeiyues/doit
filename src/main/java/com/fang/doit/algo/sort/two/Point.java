//package com.fang.doit.algo.sort.two;
//
//
//import java.util.Comparator;
//
///**
// * @author by Feiyue on 2019/11/3 3:41 PM
// */
//public class Point implements Comparable<Point> {
//
//    /**
//     * x-coordinate of this point
//     */
//    private final int x;
//
//    /**
//     * y-coordinate of this point
//     */
//    private final int y;
//
//    /**
//     * constructs the point (x, y)
//     *
//     * @param x
//     * @param y
//     */
//    public Point(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    /**
//     * draws this point
//     */
//    public void draw() {
//        StdDraw.point(this.x, this.y);
//    }
//
//    /**
//     * draws the line segment from this point to that point
//     *
//     * @param that
//     */
//    public void drawTo(Point that) {
//        StdDraw.line(this.x, this.y, that.x, that.y);
//    }
//
//    /**
//     * point (x0, y0) is less than the argument point (x1, y1)
//     * if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
//     *
//     * @param that
//     * @return
//     */
//    @Override
//    public int compareTo(Point that) {
//        if (y < that.y || (y == that.y && x < that.x)) {
//            return -1;
//
//        } else if (y == that.y && x == that.x) {
//            return 0;
//
//        }
//
//        return 1;
//    }
//
//    /**
//     * the slope between this point and that point
//     * given by the formula (y1 ? y0) / (x1 ? x0)
//     *
//     * @param that
//     * @return
//     */
//    public double slopeTo(Point that) {
//        if (x == that.x && y == that.y) {
//            return Double.NEGATIVE_INFINITY;
//        }
//
//        if (x == that.x) {
//            return Double.POSITIVE_INFINITY;
//        }
//        if (y == that.y) {
//            return 0.0;
//        }
//
//        return (double) (this.y - that.y) / (this.x - that.x);
//    }
//
//
//    /**
//     * compare two points by slopes they make with this point
//     * if and only if the slope (y1 - y0) / (x1 - x0) is less than the slope (y2 - y0) / (x2 - x0)
//     *
//     * @return
//     */
//    public Comparator<Point> slopeOrder() {
//
//        return new Comparator<Point>() {
//            @Override
//            public int compare(Point o1, Point o2) {
//
//                if (slopeTo(o1) < slopeTo(o2)) {
//                    return -1;
//                } else if (slopeTo(o1) > slopeTo(o2)) {
//                    return 1;
//                }
//
//                return 0;
//            }
//        };
//    }
//
//    @Override
//    public String toString() {
//        return "(" + x + ", " + y + ")";
//    }
//
//    public static void main(String[] args) {
//        Point p1 = new Point(0, 0);
//        Point p2 = new Point(1, 1);
//        Point p3 = new Point(2, 2);
//        Point p4 = new Point(2, 1);
//        Point p5 = new Point(4, 1);
//        System.out.println("p1.compareTo(p1) is " + p1.compareTo(p2));
//        System.out.println("p2.compareTo(p1) is " + p2.compareTo(p1));
//        System.out.println("p1.compareTo(p1) is " + p1.compareTo(p1) + "\n");
//
//        System.out.println("p1.slopeTo(p2) is " + p1.slopeTo(p2));
//        System.out.println("p1.slopeTo(p4) is " + p1.slopeTo(p4));
//        System.out.println("p1.slopeTo(p1) is " + p1.slopeTo(p1));
//        System.out.println("p3.slopeTo(p4) is " + p3.slopeTo(p4));
//        System.out.println("p2.slopeTo(p5) is " + p2.slopeTo(p5));
//    }
//}
