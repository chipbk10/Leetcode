package dp;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * Good summary: https://leetcode.com/problems/interleaving-string/discuss/31904/Summary-of-solutions-BFS-DFS-DP
 */
public class Problem97_InterleavingStrings {

    /**
     * Check some basic conditions
     */
    private boolean validCheck(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        if (n1 + n2 != n3) return false;
        if (n1 > 0 && s1.charAt(0) != s3.charAt(0) && n2 > 0 && s2.charAt(0) != s3.charAt(0)) return false;
        if (n1 > 0 && s1.charAt(n1-1) != s3.charAt(n3-1) && n2 > 0 && s2.charAt(n2-1) != s3.charAt(n3-1)) return false;
        return true;
    }

    /**
     * BruteForce (Recursion) -> Recursion with memorization (DP top down - DFS) -> 2D DP -> 1D DP
     *  https://leetcode.com/articles/interleaving-strings/
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (!validCheck(s1, s2, s3)) return false;
//        return dp_top_down(s1, s2, s3);
//        return brute_force(s1, s2, s3, 0, 0, 0);
//        return recursion(s1, s2, s3, 0, 0);
//        return recursion_memo(s1, s2, s3, 0, 0, new HashSet<>());
        return bfs(s1, s2, s3);
    }

    /**
     * Try to put s1[i] or s2[j] at position i+j of s3
     * we try all posibilities of arrangement, and then compare with s3.
     */
    private boolean brute_force(String s1, String s2, String s3, String s, int i, int j) {
        if (i == s1.length() && j == s2.length()) return s.equals(s3);
        if (i == s1.length()) return brute_force(s1, s2, s3, s + s2.charAt(j), i, j+1);
        if (j == s2.length()) return brute_force(s1, s2, s3, s + s1.charAt(i), i+1, j);
        return brute_force(s1, s2, s3, s + s2.charAt(j), i, j+1) || brute_force(s1, s2, s3, s + s1.charAt(i), i+1, j);
    }

    /**
     * Try to put s1[i] or s2[j] at position i+j of s3
     * At least one of 2 conditions must be satisfied: s1[i] = s3[i+j] or s2[i] = s3[i+j]
     */
    private boolean recursion(String s1, String s2, String s3, int i, int j) {
        if (i == s1.length() && j == s2.length()) return true;
        if (i == s1.length()) return (s2.charAt(j) == s3.charAt(i+j)) && recursion(s1, s2, s3, i, j+1);
        if (j == s2.length()) return (s1.charAt(i) == s3.charAt(i+j)) && recursion(s1, s2, s3, i+1, j);

        boolean match1 = (s1.charAt(i) == s3.charAt(i+j));
        boolean match2 = (s2.charAt(j) == s3.charAt(i+j));

        if (match1 && !match2) return recursion(s1, s2, s3, i+1, j);
        if (!match1 && match2) return recursion(s1, s2, s3, i, j+1);
        if (match1 && match2) return recursion(s1, s2, s3, i+1, j) || recursion(s1, s2, s3, i, j+1);

        return false;
    }

    /**
     * Improve using a cache.
     * cache can be represented as
     *
     * Set<Integer> value = i * n3 + j
     * no need to store actual result.
     * if we found the path, we have already terminated. (Good one)
     *
     */
    private boolean recursion_memo(String s1, String s2, String s3, int i, int j, Set<Integer> cache) {

        if (i == s1.length() && j == s2.length()) return true;

        int value = i * s3.length() + j; // might be used as a signature ???
        if (cache.contains(value)) return false;

        // no need to store actual result.
        // if we found the path, we have already terminated.
        cache.add(i * s3.length() + j);

        boolean match1 = i < s1.length() && s3.charAt(i+j) == s1.charAt(i);
        boolean match2 = j < s2.length() && s3.charAt(i+j) == s2.charAt(j);

        if (match1 && match2)
            return recursion_memo(s1, s2, s3, i + 1, j, cache) || recursion_memo(s1, s2, s3, i, j + 1, cache);
        if (match1) return recursion_memo(s1, s2, s3, i + 1, j, cache);
        if (match2) return recursion_memo(s1, s2, s3, i, j + 1, cache);

        return false;
    }

    /**
     * dp[i][j] represents s3[0,i+j] = s1[0,i] + s2[0,j] --- interleaving
     * In general, dp[i][j] will depend on dp[i-1][j] or dp[i][j-1]
     */
    private boolean dp_top_down(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        boolean[][] dp = new boolean[n1+1][n2+1];
        dp[0][0] = true;

        for (int i = 1; i <= n2; i++) {
            if (!dp[0][i-1]) dp[0][i] = false;
            else {
                dp[0][i] = (s2.charAt(i-1) == s3.charAt(i-1));
            }
        }

        for (int i = 1; i <= n1; i++) {
            if (!dp[i-1][0]) dp[i][0] = false;
            else {
                dp[i][0] = (s1.charAt(i-1) == s3.charAt(i-1));
            }
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                char c1 = s1.charAt(i-1), c2 = s2.charAt(j-1), c3 = s3.charAt(i+j-1);
                dp[i][j] = false;
                if (c1 == c3) dp[i][j] = dp[i-1][j];
                if (!dp[i][j] && c2 == c3) dp[i][j] = dp[i][j-1];
            }
        }

        return dp[n1][n2];
    }

    /**
     * Imagine a grid, which x-axis and y-axis are s1 and s2, matching s3 is the same as
     * finding a path from (0,0) to (len1, len2). It actually becomes a
     * BFS on grid. Since we don't need exact paths, a HashSet of
     * coordinates is used to eliminate duplicated paths.
     */
    public boolean bfs(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        if (n1 + n2 != n3) return false;
        Deque<Integer> queue = new LinkedList<>();
        int matched = 0;
        queue.offer(0);
        Set<Integer> set = new HashSet<>();
        while (queue.size() > 0 && matched < n3) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int p1 = queue.peek() / n3, p2 = queue.peek() % n3;
                queue.poll();

                if (p1 < n1 && s1.charAt(p1) == s3.charAt(matched)) {
                    int key = (p1 + 1) * n3 + p2;
                    if (!set.contains(key)) {
                        set.add(key);
                        queue.offer(key);
                    }
                }

                if (p2 < n2 && s2.charAt(p2) == s3.charAt(matched)) {
                    int key = p1 * n3 + (p2 + 1);
                    if (!set.contains(key)) {
                        set.add(key);
                        queue.offer(key);
                    }
                }
            }
            matched++;
        }
        return queue.size() > 0 && matched == n3;
    }
}