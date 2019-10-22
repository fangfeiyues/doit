package com.fang.doit.algo;

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

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        // initial all=0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grids[i][j] = 0;
            }
        }


    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {


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

    // test client (optional)
    public static void main(String[] args) {


    }
}
