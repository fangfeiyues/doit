package com.fang.doit.algo.find_union;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 渗透
 *
 * @author by Feiyue on 2019/10/21 9:47 PM
 */
public class Percolation {

    // 0:blocked ; 1:
    private Integer[][] grids;

    // weighted quick union algorithm
    private WeightedQuickUnionUF weightedQuickUnionUF;

    private int n;

    private int number;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        // initial all=0
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                grids[i][j] = 0;
            }
        }

        
    }

    /**
     * open 如果周边site也打开则union
     * opens the site (row, col) if it is not open already
     *
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        checkIllegal(row, col);

        if (isOpen(row, col)) {
            return;
        }


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        return false;
    }

    // is the site (row, col) full? 是否能通过渗透和顶部相联
    public boolean isFull(int row, int col) {

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {

        return 0;
    }

    // does the system percolate?
    public boolean percolates() {

        return false;
    }

    private void checkIllegal(int row, int col) {
        if ((row > n || row < 0) || (col > n || col < 0)) {
            throw new IllegalArgumentException();
        }
    }

    // test client (optional)
    public static void main(String[] args) {


    }
}
