package com.wrh.algorithum;

/**
 * @author wuruohong
 * @Classname MaxAreaIsland
 * @Description TODO
 * @Date 2021/3/15 20:28
 */
public class MaxAreaIsland {

    public static void main(String[] args) {
        int[][] grid = {{1,1,0},{0,1,1},{0,0,1},{0,0,0}};
        System.out.println("grid.length = " + grid.length);
        System.out.println("grid[0].length = " + grid[0].length);
        MaxAreaIsland island = new MaxAreaIsland();
        System.out.println("maxAreaOfIsland(grid) = " + island.maxAreaOfIsland(grid));
    }

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    int a = area(grid, r, c);
                    res = Math.max(res, a);
                }
            }
        }
        return res;
    }

    int area(int[][] grid, int r, int c) {
        if (!inArea(grid, r, c)) {
            return 0;
        }
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 2;

        return 1
                + area(grid, r - 1, c)
                + area(grid, r + 1, c)
                + area(grid, r, c - 1)
                + area(grid, r, c + 1);
    }

    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }
}
