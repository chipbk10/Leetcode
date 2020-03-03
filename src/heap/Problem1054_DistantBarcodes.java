package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Problem1054_DistantBarcodes {

    public int[] rearrangeBarcodes(int[] A) {
        int n = A.length, ind = 0, res[] = new int[n];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(A[i], map.getOrDefault(A[i], 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>((i, j) -> map.get(j) - map.get(i));
        for (int i : map.keySet()) pq.offer(i);

        while (!pq.isEmpty()) {
            int v = pq.poll();
            int m = map.get(v);
            for (int j = 0; j < m; j++) {
                if (ind >= n) ind = 1;
                res[ind] = v;
                ind += 2;
            }
        }

        return res;
    }
}
