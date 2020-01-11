package tree;

import data.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 */
public class Problem98_ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
//        return recursive(root, Long.MIN_VALUE, Long.MAX_VALUE);
//        return recursive_imporve(root, null, null);
//        return recursive_in_order(root);
//        return iterative_in_order(root);
//        return iterative_dfs(root);
        return iterative_bfs(root);
    }

    private boolean recursive(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val >= max || root.val <= min) return false;
        return recursive(root.left, min, root.val) && recursive(root.right, root.val, max);
    }

    private boolean recursive_imporve(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && min >= root.val) return false;
        if (max != null && max <= root.val) return false;
        return recursive_imporve(root.left, min, root.val) && recursive_imporve(root.right, root.val, max);
    }

    Stack<TreeNode> st = new Stack<>();
    Stack<Integer> mins = new Stack<>();
    Stack<Integer> maxs = new Stack<>();

    private void push(TreeNode node, Integer min, Integer max) {
        st.push(node);
        mins.push(min);
        maxs.push(max);
    }

    private boolean iterative_dfs(TreeNode root) {
        Integer min = null, max = null;
        push(root, min, max);
        while (!st.isEmpty()) {
            root = st.pop();
            min = mins.pop();
            max = maxs.pop();
            if (root == null) continue;
            if (min != null && min >= root.val) return false;
            if (max != null && max <= root.val) return false;
            push(root.left, min, root.val);
            push(root.right, root.val, max);
        }
        return true;
    }

    Queue<TreeNode> q = new LinkedList<>();
    Queue<Integer> qMins = new LinkedList<>();
    Queue<Integer> qMaxs = new LinkedList<>();

    private void offer(TreeNode node, Integer min, Integer max) {
        q.offer(node);
        qMins.offer(min);
        qMaxs.offer(max);
    }
    private boolean iterative_bfs(TreeNode root) {
        Integer min = null, max = null;
        if (root != null) offer(root, min, max);

        while (!st.isEmpty()) {
            int size = st.size();
            for (int i = 0; i < size; i++) {
                root = q.poll();
                min = qMins.poll();
                max = qMaxs.poll();
                if (min != null && min >= root.val) return false;
                if (max != null && max <= root.val) return false;
                if (root.left != null) offer(root.left, min, root.val);
                if (root.right != null) offer(root.right, root.val, max);
            }
        }
        return true;
    }

    Integer prevVal = null;

    private boolean recursive_in_order(TreeNode root) {
        if (root == null) return true;
        boolean l = recursive_in_order(root.left);
        if (!l) return false;

        if (prevVal != null && prevVal >= root.val) return false;
        prevVal = root.val;

        return recursive_in_order(root.right);
    }

    private boolean iterative_in_order(TreeNode root) {
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
