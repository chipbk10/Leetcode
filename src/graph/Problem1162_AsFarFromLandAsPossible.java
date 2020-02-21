package graph;

import java.util.LinkedList;
import java.util.Queue;

public class Problem1162_AsFarFromLandAsPossible {

    int[][] dirs = {{-1,0}, {0,-1}, {1,0}, {0,1}};
    public int maxDistance(int[][] A) {
        int m = A.length;
        if (m == 0) return 0;

        int n = A[0].length, level = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) queue.offer(new int[] {i, j});
            }
        }

        int res = -1;

        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] e = queue.poll();
                for (int[] d : dirs) {
                    int x = e[0]+d[0], y = e[1]+d[1];
                    if (x < 0 || x >= m) continue;
                    if (y < 0 || y >= n) continue;
                    if (A[x][y] != 0) continue;
                    A[x][y] = level;
                    queue.offer(new int[] {x, y});
                    res = Math.max(res, level);
                }
            }
        }

        return res;
    }
}
