package dp;
import java.util.Map;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 */
public class Problem96_UniqueBinarySearchTrees {

    public int numTrees(int n) {
//        return numTrees_dp(n);
//        return numTrees_dp_improve(n);
//        return numTrees_dc(1, n);
//        return numTrees_dc_memo(1, n, new HashMap<>());
        return numTrees_dc_memo_improve(1, n, new int[n+1]);
    }

    /**
     * Technique PIVOT element (devide and conquer)
     */
    private int numTrees_dc(int start, int end) {
        if (start >= end) return 1;
        int count = 0;
        for (int i = start; i <= end; i++) { // pivot element = i
            int left = numTrees_dc(start, i-1);
            int right = numTrees_dc(i+1, end);
            count += left * right;
        }
        return count;
    }

    /**
     * subproblems are evaluated many times, so we cache whenever the calculation is done
     */
    private int numTrees_dc_memo(int start, int end, Map<String, Integer> cache) {
        String key = start + "->" + end;
        if (cache.containsKey(key)) return cache.get(key);

        int count = 0;

        if (start >= end) {
            count = 1;
        }
        else {
            for (int i = start; i <= end; i++) { // pivot element = i
                int left = numTrees_dc(start, i-1);
                int right = numTrees_dc(i+1, end);
                count += left * right;
            }
        }

        cache.put(key, count);
        return count;
    }

    /**
     * subproblems doesn't depend on start and end values, but [end-start] value
     */
    private int numTrees_dc_memo_improve(int start, int end, int[]dp) {
        int key = end - start + 1;
        if (dp[key] != 0) return dp[key];

        int count = 0;

        if (start >= end) {
            count = 1;
        }
        else {
            for (int i = start; i <= end; i++) { // pivot element = i
                int left = numTrees_dc(start, i-1);
                int right = numTrees_dc(i+1, end);
                count += left * right;
            }
        }

        dp[key] = count;
        return count;
    }

    /**
     * The same as technique PIVOT element (divide and conquer)
     * DP solution:
     *  f(n+1) = f(n)   * PIVOT(n+1)    * f(0) +
     *           f(n-1) * PIVOT(n)      * f(1) +
     *           ...
     *           f(1)   * PIVOT(2)      * f(n-1) +
     *           f(0)   * PIVOT(n+1)    * f(n)
     *
     *  f(n+1) = f(n)*f(0) + f(n-1)*f(1) + f(n-2)*f(2) + ... + f(1)*f(n-1) + f(0)*f(n)
     */
    private int numTrees_dp(int n) {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }

    /**
     * Due to the symmetric equation, we can reduce the computation.
     */
    private int numTrees_dp_improve(int n) {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int m = (i >> 1);
            for (int j = 0; j < m; j++) {
                dp[i] += 2*dp[j]*dp[i-j-1];
            }
            if (i%2 == 1) dp[i] += dp[m]*dp[m];
        }
        return dp[n];
    }
}
