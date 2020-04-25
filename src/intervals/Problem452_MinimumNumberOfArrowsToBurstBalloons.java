package intervals;

import java.util.Arrays;

public class Problem452_MinimumNumberOfArrowsToBurstBalloons {

    public int findMinArrowShots(int[][] A) {
        Arrays.sort(A, (a, b) -> a[1]-b[1]);
        long burst = Long.MIN_VALUE;
        int res = 0;
        for (int[] a : A) {
            if (burst < a[0]) {
                burst = (long)a[1];
                res++;
            }
        }
        return res;
    }
}
