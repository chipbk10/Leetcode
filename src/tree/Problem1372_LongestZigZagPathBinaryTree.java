package tree;

import data.TreeNode;

public class Problem1372_LongestZigZagPathBinaryTree {

    public int longestZigZag(TreeNode root) {
        return dfs(root)[2];
    }

    private int[] dfs(TreeNode root) {
        if (root == null) return new int[]{-1, -1, -1};
        int[] left = dfs(root.left), right = dfs(root.right);
        int res = Math.max(Math.max(left[1], right[0]) + 1, Math.max(left[2], right[2]));
        return new int[]{left[1] + 1, right[0] + 1, res};
    }
}
