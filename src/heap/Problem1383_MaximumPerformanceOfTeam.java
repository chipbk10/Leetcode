package heap;

import java.util.PriorityQueue;

public class Problem1383_MaximumPerformanceOfTeam {

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        // sum(speed) * min(efficiency)
        // k engineers

        PriorityQueue<Integer> effQueue = new PriorityQueue<>((i,j) -> efficiency[j] - efficiency[i]);
        for (int i = 0; i < n; i++) effQueue.offer(i);

        long sum = 0, res = 0;
        PriorityQueue<Integer> spQueue = new PriorityQueue<>();
        while (!effQueue.isEmpty()) {
            int i = effQueue.poll();
            spQueue.offer(speed[i]);
            sum += speed[i];
            if (spQueue.size() > k) {
                sum -= spQueue.poll();
            }
            res = Math.max(res, sum * efficiency[i]);
        }
        return (int) (res % (long)(1e9 + 7));
    }

}
