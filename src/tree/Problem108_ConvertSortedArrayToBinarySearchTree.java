package tree;

import data.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Problem108_ConvertSortedArrayToBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
//        int n = nums.length;
//        return divide_and_conquer(nums, 0, n-1);
//        return dfs_iterative(nums);
        return bfs_iterative(nums);
    }

    private TreeNode divide_and_conquer(int[] nums, int lo, int hi) {
        if (lo > hi) return null;
        int mi = lo + (hi-lo) / 2;
        TreeNode root = new TreeNode(nums[mi]);
        root.left = divide_and_conquer(nums, lo, mi-1);
        root.right = divide_and_conquer(nums, mi+1, hi);
        return root;
    }

    /**
     *  Interesting : Assume that we have a root, then root.val will be assign later.
     */
    private TreeNode dfs_iterative(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        Stack<TreeNode> st = new Stack<>();
        TreeNode root = new TreeNode(0), cur;
        st.push(root);

        Stack<Integer> inds = new Stack<>();
        int lo = 0, hi = nums.length-1, mid;
        inds.push(hi);
        inds.push(lo);

        while (!st.isEmpty()) {
            cur = st.pop();
            lo = inds.pop();
            hi = inds.pop();
            mid = lo + (hi-lo)/2;
            cur.val = nums[mid];

            if (lo < mid) {
                cur.left = new TreeNode(0);
                st.push(cur.left);
                inds.push(mid-1);
                inds.push(lo);
            }

            if (hi > mid) {
                cur.right = new TreeNode(0);
                st.push(cur.right);
                inds.push(hi);
                inds.push(mid+1);
            }
        }
        return root;
    }

    private TreeNode bfs_iterative(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(0), cur;
        queue.offer(root);

        Queue<Integer> inds = new LinkedList<>();
        int lo = 0, hi = nums.length-1, mid;
        inds.offer(lo);
        inds.offer(hi);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                lo = inds.poll();
                hi = inds.poll();
                mid = lo + (hi-lo)/2;
                cur.val = nums[mid];

                if (lo < mid) {
                    cur.left = new TreeNode(0);
                    queue.offer(cur.left);
                    inds.offer(lo);
                    inds.offer(mid-1);
                }

                if (hi > mid) {
                    cur.right = new TreeNode(0);
                    queue.offer(cur.right);
                    inds.offer(mid+1);
                    inds.offer(hi);
                }
            }
        }
        return root;
    }
}
