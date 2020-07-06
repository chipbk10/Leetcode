package monotone;

import java.util.Deque;
import java.util.LinkedList;

public class Problem1425_ConstrainedSubsequenceSum {

    public int constrainedSubsetSum(int[] A, int k) {
        int n = A.length, ans = Integer.MIN_VALUE;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int max = Math.max(0, deque.isEmpty() ? 0 : A[deque.peekFirst()]);
            A[i] += max;
            ans = Math.max(ans, A[i]);
            while (!deque.isEmpty() && A[i] >= A[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
            if (i - deque.peekFirst() + 1 > k) {
                deque.removeFirst();
            }
        }
        return ans;
    }
}
