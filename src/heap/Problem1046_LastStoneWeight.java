package heap;

import java.util.PriorityQueue;

public class Problem1046_LastStoneWeight {

    public int lastStoneWeight(int[] A) {
        int B[] = new int[1001], i = 1000;
        for (int a : A) B[a]++;

        int j = 0;
        while (i >= 0) {

            while (i >= 0 && B[i] == 0) i--;
            if (i >= 0 && B[i] != 0) {
                System.out.println(i);
                j = i;
                B[i]--;
            }

            while (i >= 0 && B[i] != 0) i--;
            if (i >= 0 && B[i] != 0) {
                System.out.println(i);
                j -= i;
                B[i]--;
                B[j]++;
                i = Math.max(i, j);
            }
        }

        return j;
    }

    public int lastStoneWeight_usingHeap(int[] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a);
        for (int a : A) pq.offer(a);
        while (!pq.isEmpty()) {
            int a = pq.poll();
            if (pq.isEmpty()) return a;
            int b = pq.poll();
            if (a > b) pq.offer(a-b);
        }
        return 0;
    }

}
