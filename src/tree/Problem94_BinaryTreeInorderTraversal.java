package tree;

import data.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 */
public class Problem94_BinaryTreeInorderTraversal {

    /**
     * In a binary search tree, in-order traversal retrieves data in sorted order.
     *  in-order traversal from left to right visits nodes in ascending order.
     *  Right-to-left traversal visits nodes in descending order
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
//        recursion(root, res);
        iterative(root, res);
        return res;
    }

    /**
     * Check if the current node is empty or null.
     * Traverse the left subtree by recursively calling the in-order function.
     * Display the data part of the root (or current node).
     * Traverse the right subtree by recursively calling the in-order function.
     */
    private void recursion(TreeNode root, List<Integer> res) {
        if (root == null) return;
        recursion(root.left, res);
        res.add(root.val);
        recursion(root.right, res);
    }

    /**
     * Using a stack to process all nodes (all to the left, pop, to the right)
     */
    private void iterative(TreeNode root, List<Integer> res) {
        Stack<TreeNode> st = new Stack<>();
        pushAllLeft(root, st);

        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            res.add(node.val);
            pushAllLeft(node.right, st);
        }
    }

    private void pushAllLeft(TreeNode node, Stack<TreeNode> st) {
        while (node != null) {
            st.push(node);
            node = node.left;
        }
    }
}
