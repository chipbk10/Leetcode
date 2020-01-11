package tree;

import data.TreeNode;

import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 */
public class Problem98_ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        // return recursive(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return iterative(root);
    }

    private boolean recursive(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val >= max || root.val <= min) return false;
        return recursive(root.left, min, root.val) && recursive(root.right, root.val, max);
    }

    private boolean iterative(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        pushAllLeft(root, st);
        TreeNode prev = null;

        while (!st.isEmpty()) {
            TreeNode cur = st.pop();
            if (prev != null && prev.val >= cur.val) return false;
            prev = cur;
            pushAllLeft(cur.right, st);
        }

        return true;
    }

    private void pushAllLeft(TreeNode node, Stack<TreeNode> st) {
        while (node != null) {
            st.push(node);
            node = node.left;
        }
    }

}
