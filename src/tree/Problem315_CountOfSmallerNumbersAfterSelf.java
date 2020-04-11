package tree;

import java.util.LinkedList;
import java.util.List;

public class Problem315_CountOfSmallerNumbersAfterSelf {


    // TODO: still wrong
    public List<Integer> countSmaller(int[] A) {
        LinkedList<Integer> res = new LinkedList<>();
        int n = A.length;
        if (n == 0) return res;

        TreeNode root = new TreeNode(A[n-1], 0);
        for (int i = n-1; i >= 0; i--) {
            res.addFirst(insert(root, A[i], 0));
        }
        return res;
    }

    private int insert(TreeNode node, int val, int smallers) {
        if (node.val == val) return node.count + smallers;
        if (node.val < val) {
            if (node.right == null) {
                node.right = new TreeNode(val, 0);
                return node.count + smallers + 1;
            }
            return insert(node.right, val, node.count + smallers + 1);
        }

        node.count++;
        if (node.left == null) {
            node.left = new TreeNode(val, 0);
            return smallers;
        }

        return insert(node.left, val, smallers);
    }

    class TreeNode {
        int val, count;
        TreeNode left, right;
        public TreeNode(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
}
