package tree;

import data.TreeNode;

import java.util.*;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 */
public class Problem107_BinaryTreeLevelOrderTraversal_II {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        bfs(root, res);
        // dfs(root, res, 0);
        // dfs_iterative(root, res);
        return res;
    }

    private void bfs(TreeNode root, List<List<Integer>> res) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(0, list);
        }
    }

    private void dfs(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) return;
        if (level >= res.size()) res.add(0, new LinkedList<>());
        res.get(res.size()-level-1).add(root.val);
        dfs(root.left, res, level+1);
        dfs(root.right, res, level+1);
    }

    private void dfs_iterative(TreeNode root, List<List<Integer>> res) {
        if (root == null) return;
        Stack<TreeNode> st = new Stack<>();
        Stack<Integer> levels = new Stack<>();
        st.push(root);
        levels.push(0);
        while (!st.isEmpty()) {
            root = st.pop();
            int level = levels.pop();
            if (level >= res.size()) res.add(0, new LinkedList<>());
            res.get(res.size()-level-1).add(root.val);
            if (root.right != null) {
                st.push(root.right);
                levels.push(level+1);
            }
            if (root.left != null) {
                st.push(root.left);
                levels.push(level+1);
            }
        }
    }
}
