package graph.unionfind;

import data.UnionFind;

public class Problem1391_CheckIfValidPathInGrid {

    // l 0 r(l) 1 r
    // i = 2*i+1
    // j = 2*j+1
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int t = 2*n + 1;
        UnionFind uf = new UnionFind((2*m + 1)*(2*n + 1));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 || grid[i][j] == 3 || grid[i][j] == 5) {
                    uf.union(g(2*i + 1, 2*j + 1, t), g(2*i + 1, 2*j, t));
                }
                if (grid[i][j] == 1 || grid[i][j] == 4 || grid[i][j] == 6) {
                    uf.union(g(2*i + 1, 2*j + 1, t), g(2*i + 1, 2*j + 2, t));
                }
                if (grid[i][j] == 2 || grid[i][j] == 3 || grid[i][j] == 4) {
                    uf.union(g(2*i + 1, 2*j + 1, t), g(2*i + 2, 2*j + 1, t));
                }
                if (grid[i][j] == 2 || grid[i][j] == 5 || grid[i][j] == 6) {
                    uf.union(g(2*i + 1, 2*j + 1, t), g(2*i, 2*j + 1, t));
                }
            }
        }
        return uf.isConnected(g(1, 1, t), g(2*m - 1, 2*n - 1, t));
    }

    public int g(int r, int c, int n) {
        return r*n + c;
    }
}
