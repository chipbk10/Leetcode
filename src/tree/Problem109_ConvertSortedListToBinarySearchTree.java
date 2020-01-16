package tree;

import data.ListNode;
import data.TreeNode;

import java.util.Stack;

public class Problem109_ConvertSortedListToBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        int size = findSize(head);
        curHead = head;
        return recursive_in_order(1, size);
        // return iterative_in_order(head, size);
    }

    private int findSize(ListNode head) {
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            cur = cur.next;
            size ++;
        }
        return size;
    }

    ListNode curHead;
    private TreeNode recursive_in_order(int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi-lo) / 2;
        TreeNode left = recursive_in_order(lo, mid-1);
        TreeNode root = new TreeNode(curHead.val);
        root.left = left;
        curHead = curHead.next;
        root.right = recursive_in_order(mid+1, hi);
        return root;
    }

    private TreeNode iterative_in_order(ListNode head, int size) {
        if (size == 0) return null;
        Stack<TreeNode> st = new Stack<>();
        Stack<Integer> inds = new Stack<>();
        TreeNode root = pushAllLeft(1, size, st, inds);
        int i = 0;
        while (!st.isEmpty()) {
            int lo = inds.pop(), hi = inds.pop();
            int mid = lo + (hi-lo) / 2;
            lo = mid+1;
            TreeNode node = st.pop();
            node.val = head.val;
            head = head.next;
            node.right = pushAllLeft(lo, hi, st, inds);
        }
        return root;
    }

    private TreeNode pushAllLeft(int lo, int hi, Stack<TreeNode> st, Stack<Integer> inds) {
        TreeNode root = null;
        while (lo <= hi) {
            TreeNode node = new TreeNode(0);
            if (root == null) root = node;
            else if (!st.isEmpty()) st.peek().left = node;
            st.push(node);
            inds.push(hi);
            inds.push(lo);
            int mid = lo + (hi-lo) / 2;
            hi = mid-1;
        }
        return root;
    }
}
