package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Problem1235_MaximumProfitInJobScheduling {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length, A[][] = new int[n][3];
        for (int i = 0; i < n; i++) A[i] = new int[] { startTime[i], endTime[i], profit[i] };
        Arrays.sort(A, (a1, a2) -> a1[1]-a2[1]);

        int max = 0;

        // dp[t][p] : maximum profit ending at t time.
        List<Integer> dpEndTime = new ArrayList<>();
        List<Integer> dpProfit = new ArrayList<>();
        dpEndTime.add(0);
        dpProfit.add(0);

        for (int[] a : A) {
            int s = a[0], e = a[1], p = a[2];
            int i = Collections.binarySearch(dpEndTime, s);
            if (i < 0) i = -i-2;
            int curProfit = dpProfit.get(i)+p, maxProfitSoFar = dpProfit.get(dpProfit.size()-1);
            if (curProfit > maxProfitSoFar) {
                dpProfit.add(curProfit);
                dpEndTime.add(e);
                max = curProfit;
            }
        }

        return max;
    }

}
