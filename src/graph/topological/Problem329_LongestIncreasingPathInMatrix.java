package graph.topological;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem329_LongestIncreasingPathInMatrix {

    int[][] dirs = {{-1,0}, {0,-1}, {1, 0}, {0, 1}};

    public int longestIncreasingPath(int[][] A) {
//        return dfs_solution(A);
        return bfs_solution(A);
    }

    private int bfs_solution(int[][] A) {
        int m = A.length;
        if (m == 0) return 0;

        int n = A[0].length, res = 0, degrees[] = new int[m*n];
        List<Integer>[] G = new List[m*n];
        for (int i = 0; i < m*n; i++) {
            G[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int k = i*n+j;
                for (int[] d : dirs) {
                    int x = i+d[0], y = j+d[1];
                    if (x < 0 || x >= m) continue;
                    if (y < 0 || y >= n) continue;
                    if (A[i][j] >= A[x][y]) continue;
                    G[k].add(x*n+y);
                    degrees[x*n+y]++;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m*n; i++) {
            if (degrees[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int e = queue.poll();
                for (int j : G[e]) {
                    degrees[j]--;
                    if (degrees[j] == 0) queue.offer(j);
                }
            }
            res++;
        }

        return res;
    }

    private int dfs_solution(int[][] A) {
        int m = A.length;
        if (m == 0) return 0;

        int n = A[0].length, res = 0, dp[][] = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(A, dp, i, j));
            }
        }

        return res;
    }

    private int dfs(int[][] A, int[][] dp, int i, int j) {
        if (dp[i][j] == 0) {
            for (int[] d : dirs) {
                int x = i+d[0], y = j+d[1];
                if (x >= 0 && x < A.length && y >= 0 && y < A[0].length && A[x][y] > A[i][j]) {
                    int adj = dfs(A, dp, x, y);
                    dp[i][j] = Math.max(dp[i][j], adj);
                }
            }
            dp[i][j]++;
        }

        return dp[i][j];
    }
}
