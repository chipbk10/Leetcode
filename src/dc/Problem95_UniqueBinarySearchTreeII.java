package dc;

import data.TreeNode;

import java.util.*;

/**
 *  Given an integer n,
 *
 *  Generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 */

public class Problem95_UniqueBinarySearchTreeII {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
//        return generateTrees(1, n);
        return generateTrees_dp(n);
    }

    /**
     * The algorithm picks a pivot element, rearranges the array elements in such a way
     * that all elements smaller than the picked pivot element move to left side of pivot,
     * and all greater elements move to right side.
     *
     * Finally, the algorithm recursively sorts the subarrays on left and right of pivot element.
     */
    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
        }
        else {
            for (int i = start; i <= end; i++) {
                List<TreeNode> ls = generateTrees(start, i-1);
                List<TreeNode> rs = generateTrees(i+1, end);
                for (TreeNode left : ls) {
                    for (TreeNode right : rs) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }
        }
        return res;
    }

    /**
     * Because the subprobems are evaluated many times, the solution can be improved using memos
     * DC turns into DP
     * cache can be represented by using 2D matrix : TreeNode[n][n] -> cache[start][end]
     * to be sure we don't use the subtree is not shared, we might use deep copy
     */
    private List<TreeNode> generateTrees_memo(int start, int end, Map<String, List<TreeNode>> cache) {

        String key = start + "->" + end;
        if (cache.containsKey(key)) return cache.get(key);

        List<TreeNode> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
        }
        else {
            for (int i = start; i <= end; i++) {
                List<TreeNode> ls = generateTrees_memo(start, i-1, cache);
                List<TreeNode> rs = generateTrees_memo(i+1, end, cache);
                for (TreeNode left : ls) {
                    for (TreeNode right : rs) {
                        TreeNode root = new TreeNode(i);
                        root.left = left; // clone(left)
                        root.right = right; // clone(right)
                        res.add(root);
                    }
                }
            }
        }

        cache.put(key, res);
        return res;
    }

    /**
     * DP solution
     *  f(1,n) = f(1, i-1) * [i] * f(i+1, n)   when i = [1,n]
     *  f(i+1, n) ~ f(1, n-i) ---> the difference is the offset between 2 corresponding nodes' values
     */
    public List<TreeNode> generateTrees_dp(int n) {
        List<TreeNode>[] dp = new List[n+1];
        dp[0] = new ArrayList<>();
        dp[0].add(null);

        for (int i = 1; i <= n; i++) {
            dp[i] = new ArrayList<>();

            // PIVOT element
            for (int j = 1; j <= i; j++) {

                // f(i) = f(j-1) * [j] * f(i-j)  when i = [1,n], and j = [1,i]
                for (TreeNode left : dp[j-1]) {
                    for (TreeNode right : dp[i-j]) {
                        TreeNode root = new TreeNode(j);
                        root.left = clone(left);
                        root.right = clone(right, j);
                        dp[i].add(root);
                    }
                }
            }
        }

        return dp[n];
    }

    /**
     * Deep copy
     */
    private TreeNode clone(TreeNode root) {
        if (root == null) return null;
        TreeNode res = new TreeNode(root.val);
        res.left = clone(root.left);
        res.right = clone(root.right);
        return res;
    }

    /**
     * Deep copy with offset
     */
    private TreeNode clone(TreeNode root, int offset) {
        if (root == null) return null;
        TreeNode res = new TreeNode(root.val + offset);
        res.left = clone(root.left, offset);
        res.right = clone(root.right, offset);
        return res;
    }

    public static void run() {
        Problem95_UniqueBinarySearchTreeII solution = new Problem95_UniqueBinarySearchTreeII();
        List<TreeNode> res = solution.generateTrees(3);
        System.out.println(res.size());
    }
}
