package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem1353_MaximumNumberOfEventsThatCanBeAttended {

    public int maxEvents(int[][] A) {
        // int[] calendar
        // sort events by startDay
        // select event --> select day

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(A, Comparator.comparingInt(a -> a[0]));
        int i = 0, res = 0, n = A.length;
        for (int d = 1; d <= 100000; ++d) {
            while (!pq.isEmpty() && pq.peek() < d)
                pq.poll();
            while (i < n && A[i][0] == d)
                pq.offer(A[i++][1]);
            if (!pq.isEmpty()) {
                pq.poll();
                ++res;
            }
        }
        return res;
    }
}
