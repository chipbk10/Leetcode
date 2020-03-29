package graph.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Problem1293_ShortestPathInGridWithObstaclesElimination {

    public int shortestPath(int[][] A, int K) {
        int m = A.length, n = A[0].length;
        if (m == 1 && n == 1) return A[0][0];
        if (K > m-1+n-1) return m+n-2;

        int dirs[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int breaks[][] = new int[m][n];
        for (int[] row: breaks) Arrays.fill(row, m+n-2);
        breaks[0][0] = A[0][0];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});

        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int e[] = queue.poll(), i = e[0], j = e[1];
                for (int[] d : dirs) {
                    int x = i+d[0], y = j+d[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    int b = breaks[i][j] + A[x][y];
                    if (b > K || b >= breaks[x][y]) continue;
                    if (x == m-1 && y == n-1) return res+1;
                    queue.offer(new int[] {x, y});
                    breaks[x][y] = b;
                }
            }
            res++;
        }

        return -1;
    }
}
