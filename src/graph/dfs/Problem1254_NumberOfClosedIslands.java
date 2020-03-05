package graph.dfs;

public class Problem1254_NumberOfClosedIslands {

    private int[][] ds = {{-1,0}, {0,1}, {1,0}, {0,-1}};

    public int closedIsland(int[][] A) {
        int m = A.length, n = A[0].length, res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) {
                    if (dfs(A, i, j)) res++;
                }
            }
        }
        return res;
    }

    private boolean dfs(int[][] A, int i, int j) {
        int m = A.length, n = A[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) return false;
        if (A[i][j] == 1) return true;
        A[i][j] = 1;

        boolean res = true;
        for (int[] d : ds) {
            int x = d[0] + i, y = d[1] + j;
            res &= dfs(A, x, y);
        }
        return res;
    }
}
