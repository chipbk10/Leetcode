package graph.dfs;

public class Problem1219_PathWithMaximumGold {

    int m, n, dirs[][] = {{-1,0}, {0,-1}, {1,0}, {0,1}};

    public int getMaximumGold(int[][] A) {
        m = A.length; n = A[0].length;
        int res = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                res = Math.max(res, dfs(A, i, j));
        return res;
    }

    private int dfs(int[][] A, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || A[i][j] <= 0) return 0;
        int max = 0;
        A[i][j] *= -1;

        for (int[] d : dirs)
            max = Math.max(max, dfs(A, i+d[0], j+d[1]));

        A[i][j] *= -1;
        return A[i][j] + max;
    }
}
