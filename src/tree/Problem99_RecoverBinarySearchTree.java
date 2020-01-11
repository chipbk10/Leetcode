package tree;

import data.TreeNode;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 */
public class Problem99_RecoverBinarySearchTree {

    TreeNode first = null, second = null, prev = null;

    public void findNodes(TreeNode cur) {
        if(cur == null) return;

        findNodes(cur.left);

        if(prev != null && cur.val < prev.val) {
            second = cur;

            if(first == null) first = prev;
            else return;
        }

        prev = cur;
        findNodes(cur.right);
    }

    public void recoverTree(TreeNode root) {
        findNodes(root);

        if(first != null) {
            int temp = second.val;
            second.val = first.val;
            first.val = temp;
        }
    }
}
