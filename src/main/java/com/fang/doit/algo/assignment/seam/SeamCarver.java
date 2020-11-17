package com.fang.doit.algo.assignment.seam;

import edu.princeton.cs.algs4.Picture;

/**
 * @author created by fang on 2020/1/4/004 20:10
 */
public class SeamCarver {

    private double[][] energy;

    private int[][] picture;

    private int width;

    private int height;

    /**
     * create a seam carver object based on the given picture
     */
    public SeamCarver(Picture picture) {

        if (picture == null) {
            throw new IllegalArgumentException();
        }

        width = picture.width();
        height = picture.height();
        energy = new double[width][height];
        this.picture = new int[width][height];

        for (int i = 0; i < width(); i++) {

            for (int j = 0; j < height(); j++) {

                this.picture[i][j] = picture.getRGB(i, j);

                energy[i][j] = computeEnergy(i, j);

            }
        }
    }

    private double computeEnergy(int x, int y) {
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return 1000.0;
        }

        int rgbUp = picture[x][y - 1];
        int rgbDown = picture[x][y + 1];
        int rgbLeft = picture[x - 1][y];
        int rgbRight = picture[x + 1][y];
        double rx = Math.pow(((rgbLeft >> 16) & 0xFF) - ((rgbRight >> 16) & 0xFF), 2);
        double gx = Math.pow(((rgbLeft >> 8) & 0xFF) - ((rgbRight >> 8) & 0xFF), 2);
        double bx = Math.pow(((rgbLeft >> 0) & 0xFF) - ((rgbRight >> 0) & 0xFF), 2);

        double ry = Math.pow(((rgbUp >> 16) & 0xFF) - ((rgbDown >> 16) & 0xFF), 2);
        double gy = Math.pow(((rgbUp >> 8) & 0xFF) - ((rgbDown >> 8) & 0xFF), 2);
        double by = Math.pow(((rgbUp >> 0) & 0xFF) - ((rgbDown >> 0) & 0xFF), 2);

        return Math.sqrt(rx + gx + bx + ry + gy + by);
    }

    // current picture
    public Picture picture() {
        Picture picture = new Picture(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                picture.setRGB(i, j, this.picture[i][j]);
            }
        }
        return picture;
    }

    // width of current picture
    public int width() {

        return width;
    }

    // height of current picture
    public int height() {

        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x > width - 1 || y < 0 || y > height - 1) {
            throw new IllegalArgumentException();
        }

        return energy[x][y];
    }


    // Y sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {

        return null;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int[] seam = new int[height];
//        double[][]

        return null;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {


    }

    //  unit testing (optional)
    public static void main(String[] args) {


    }

}
