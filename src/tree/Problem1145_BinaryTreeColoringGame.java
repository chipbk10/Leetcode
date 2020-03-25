package tree;

import data.TreeNode;

public class Problem1145_BinaryTreeColoringGame {

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode node = find(root, x);
        int left = count(node.left), right = count(node.right), parent = n-left-right-1;
        int max = Math.max(Math.max(left, right), parent);
        return max > n/2;
    }

    private TreeNode find(TreeNode root, int x) {
        if (root == null) return null;
        if (root.val == x) return root;
        TreeNode left = find(root.left, x);
        return (left != null) ? left : find(root.right, x);
    }

    private int count(TreeNode root) {
        if (root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }
}
