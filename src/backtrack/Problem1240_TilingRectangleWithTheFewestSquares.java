package backtrack;

import java.util.Arrays;

public class Problem1240_TilingRectangleWithTheFewestSquares {

    int res = Integer.MAX_VALUE;
    int min;

    public static void run() {
        Problem1240_TilingRectangleWithTheFewestSquares solution = new Problem1240_TilingRectangleWithTheFewestSquares();
        int m = 3, n = 2;
        int res = solution.tilingRectangle(m, n);
        System.out.println(res);
    }

    public int tilingRectangle(int m, int n) {
//        return backtrack_solution_TLE(m, n);
        return skyline_backtrack_solution(m, n);
    }

    public int skyline_backtrack_solution(int n, int m) {
        if (n == m) return 1;
        if (n > m) {
            int temp = n;
            n = m;
            m = temp;
        }
        dfs(n, m, new int[n + 1], 0);
        return res;
    }

    private void dfs(int n, int m, int[] h, int cnt) {
        if (cnt >= res) return;
        boolean isFull = true;
        int pos = -1, minH = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (h[i] < m) isFull = false;
            if (h[i] < minH) {
                pos = i;
                minH = h[i];
            }
        }
        if (isFull) {
            res = Math.min(cnt, res);
            return;
        }

        int end = pos;
        while (end + 1 <= n && h[end + 1] == h[pos] && (end + 1 - pos + 1 + minH) <= m) end++;
        for (int j = end; j >= pos; j--) {
            int curH = j - pos + 1;

            int[] next  = Arrays.copyOf(h, n + 1);
            for (int k = pos; k <= j; k++) {
                next[k] += curH;
            }
            dfs(n, m, next, cnt + 1);
        }

    }









    private int backtrack_solution_TLE(int m, int n) {
        min = Math.min(m, n);
        backtrack(m, n, new boolean[m][n], 0, 0);
        return res;
    }

    private void backtrack(int m, int n, boolean[][] placed, int Covered, int count) {
        int S = m*n;
        if (S == Covered) {
            res = Math.min(res, count);
            return;
        }

        for (int size = min; size >= 1; size--) {
            if (S < Covered + size*size) continue;

            for (int i = 0; i < m; i++) {
                if (i+size > m) break;
                for (int j = 0; j < n; j++) {
                    if (j+size > n) break;
                    if (canPlace(i, j, placed, size)) {
                        fill(i, j, placed, size, true);
                        backtrack(m, n, placed, Covered+size*size, count+1);
                        fill(i, j, placed, size, false);
                    }
                }
            }
        }
    }

    private boolean canPlace(int x, int y, boolean[][] placed, int size) {
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                if (placed[i][j]) return false;
            }
        }
        return true;
    }

    private void fill(int x, int y, boolean[][] placed, int size, boolean value) {
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                placed[i][j] = value;
            }
        }
    }
}
