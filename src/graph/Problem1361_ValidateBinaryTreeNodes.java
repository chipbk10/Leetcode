package graph;

import java.util.LinkedList;
import java.util.Queue;

public class Problem1361_ValidateBinaryTreeNodes {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int count = 0, to[] = new int[n];

        // check dependencies
        for (int i = 0; i < n; i++) {
            int l = leftChild[i], r = rightChild[i];
            if (l != -1) {
                to[l]++;
                count++;
                if (to[l] > 1) return false;
            }
            if (r != -1) {
                to[r]++;
                count++;
                if (to[r] > 1) return false;
            }
        }

        if (count != n-1) return false;

        int root = 0;
        for (int i = 0; i < n; i++) {
            if (to[i] == 0) root = i;
        }

        return bfs(n, leftChild, rightChild, root);
    }

    private boolean bfs(int n, int[] leftChild, int[] rightChild, int start) {
        boolean[] seen = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        seen[start] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                int l = leftChild[node], r = rightChild[node];
                if (l != -1) {
                    if (seen[l]) return false;
                    queue.offer(l);
                    seen[l] = true;
                    count++;
                }

                if (r != -1) {
                    if (seen[r]) return false;
                    queue.offer(r);
                    seen[r] = true;
                    count++;
                }
            }
        }

        return count == n;
    }
}
