package com.fang.doit.algo.sort.three;

/**
 *  ±º‰∏¥‘”∂»£∫O(n^2)
 * A* search algorithm
 *
 * @author by Feiyue on 2019/11/10 1:39 PM
 */
public class Board {

    /**
     * create a board from an n-by-n array of tiles,
     * where tiles[row][col] = tile at (row, col)
     *
     * @param tiles
     */

    public Board(int[][] tiles) {

    }

    /**
     * string representation of this board
     *
     * @return
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     * board dimension n
     *
     * @return
     */
    public int dimension() {
        return 0;
    }

    /**
     * number of tiles out of place
     *
     * @return
     */
    public int hamming() {
        return 0;
    }

    /**
     * sum of Manhattan distances between tiles and goal
     *
     * @return
     */
    public int manhattan() {
        return 0;
    }

    /**
     * is this board the goal board?
     *
     * @return
     */
    public boolean isGoal() {
        return true;
    }

    /**
     * does this board equal y?
     *
     * @param y
     * @return
     */
    @Override
    public boolean equals(Object y) {
        return true;
    }

    /**
     * all neighboring boards
     *
     * @return
     */
    public Iterable<Board> neighbors() {

        return null;
    }

    /**
     * a board that is obtained by exchanging any pair of tiles
     *
     * @return
     */
    public Board twin() {

        return null;
    }

    /**
     * unit testing (not graded)
     *
     * @param args
     */
    public static void main(String[] args) {

    }


}
