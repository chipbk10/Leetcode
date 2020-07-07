package monotone;

import java.util.Deque;
import java.util.LinkedList;

public class Problem862_ShortestSubarrayWithSumAtLeastK {

    public int shortestSubarray(int[] A, int K) {
        // B[j] - B[i] >= K ---> find smallest (j-i);
        Deque<Integer> deque = new LinkedList<>();
        int n = A.length, res = n+1, B[] = new int[n+1];

        for (int i = 0; i < n; i++) B[i+1] += B[i] + A[i];

        for (int i = 0; i < n+1; i++) {
            while (!deque.isEmpty() && B[i] - B[deque.peekFirst()] >= K) {
                res = Math.min(res, i-deque.pollFirst());
            }
            while (!deque.isEmpty() && B[i] <= B[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }

        return res == n+1 ? -1 : res;
    }
}
