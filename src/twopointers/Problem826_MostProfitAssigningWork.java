package twopointers;

import java.util.Arrays;

public class Problem826_MostProfitAssigningWork {

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length, A[][] = new int[n][2];
        for (int i = 0; i < n; i++) A[i] = new int[] {difficulty[i], profit[i]};
        Arrays.sort(A, (a, b) -> a[0]-b[0]);
        Arrays.sort(worker);

        int res = 0, max = 0;
        for (int i = 0, j = 0; i < worker.length; i++) {
            while (j < n && A[j][0] <= worker[i]) {
                max = Math.max(max, A[j++][1]);
            }
            res += max;
        }
        return res;
    }
}
