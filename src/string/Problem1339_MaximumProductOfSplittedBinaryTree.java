package string;

import data.TreeNode;

public class Problem1339_MaximumProductOfSplittedBinaryTree {

    long res = 0, total = 0, sub;

    public int maxProduct(TreeNode root) {
        total = cal(root); cal(root);
        return (int)(res % (int)(1e9 + 7));
    }

    private long cal(TreeNode root) {
        if (root == null) return 0;
        sub = root.val + cal(root.left) + cal(root.right);
        res = Math.max(res, sub * (total - sub));
        return sub;
    }
}
