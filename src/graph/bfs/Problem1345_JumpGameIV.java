package graph.bfs;

import java.util.*;

public class Problem1345_JumpGameIV {

    public int minJumps(int[] arr) {
        // return bfs_solution(arr);
        return dp_solution(arr);
    }

    // still wrong with the edge case
    private int dp_solution(int[] A) {
        int n = A.length;
        if (n == 1) return 0;

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(A[i], new HashSet<>());
            map.get(A[i]).add(i);
        }

        // dp[k][i] - the minimum number of steps to reach i using k first numbers
        // dp[k][0] = 0
        // dp[0][i] =
        int[][] dp = new int[n+1][n];

        for (int i = 1; i < n; i++) dp[0][i] = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= n; k++) {
                int min = Integer.MAX_VALUE;
                if (i >= 1) min = Math.min(min, dp[k-1][i-1]);
                if (i+1 < n) min = Math.min(min, dp[k-1][i+1]);
                for (int j : map.get(A[i])) {
                    if (j == i) continue;
                    min = Math.min(min, dp[k-1][j]);
                }
                dp[k][i] = min+1;
                System.out.println("dp["+k+"]["+i+"]=" + dp[k][i]);
            }
        }

        int res = Integer.MAX_VALUE;
        for (int k = 1; k <= n; k++) {
            res = Math.min(res, dp[k][n-1]);
        }
        return res;
    }

    private int bfs_solution(int[] A) {
        int n = A.length;
        if (n == 1) return 0;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = n-1; i >= 0; i--) {
            map.putIfAbsent(A[i], new LinkedList<>());
            map.get(A[i]).add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int j = queue.poll();

                if (j+1 < n && map.containsKey(A[j+1])) {
                    if (j+1 == n-1) return res;
                    queue.offer(j+1);
                }

                if (j-1 >= 0 && map.containsKey(A[j-1])) {
                    queue.offer(j-1);
                }

                if (map.containsKey(A[j])) {
                    for (int k : map.get(A[j])) {
                        if (k != j) {
                            if (k == n-1) return res;
                            queue.offer(k);
                        }
                    }
                    map.remove(A[j]);
                }
            }
        }

        return res;
    }
}
