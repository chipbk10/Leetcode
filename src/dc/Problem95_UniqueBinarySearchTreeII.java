package dc;

import data.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *  Given an integer n,
 *
 *  Generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 */

public class Problem95_UniqueBinarySearchTreeII {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        return generateTrees(1, n);
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
    private List<TreeNode> generateTrees_dp(int start, int end, Map<String, List<TreeNode>> cache) {

        String key = start + "->" + end;
        if (cache.containsKey(key)) return cache.get(key);

        List<TreeNode> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
        }
        else {
            for (int i = start; i <= end; i++) {
                List<TreeNode> ls = generateTrees_dp(start, i-1, cache);
                List<TreeNode> rs = generateTrees_dp(i+1, end, cache);
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

    private TreeNode clone(TreeNode root) {
        if (root == null) return null;
        TreeNode res = new TreeNode(root.val);
        res.left = clone(root.left);
        res.right = clone(root.right);
        return res;
    }

    public static void run() {
    }
}
