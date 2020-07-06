package monotone;

import java.util.Deque;
import java.util.LinkedList;

public class Problem239_SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] A, int k) {
        int n = A.length, res[] = new int[n-k+1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0, j = 0; i < n; i++) {
            while (!deque.isEmpty() && A[deque.peekFirst()] < A[i]) {
                deque.pollFirst();
            }
            deque.offerFirst(i);
            if (i-deque.peekLast() >= k) {
                deque.pollLast();
            }
            if (i >= k-1) res[j++] = A[deque.peekLast()];
        }
        return res;
    }
}
