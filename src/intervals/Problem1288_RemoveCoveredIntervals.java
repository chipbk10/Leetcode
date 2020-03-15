package intervals;

import java.util.Arrays;

public class Problem1288_RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] A) {
        Arrays.sort(A, (a1, a2) -> a1[0] == a2[0] ? a1[1]-a2[1] : a1[0]-a2[0]);
        int right = -1, res = 0;
        for (int[] a : A) {
            if (a[1] > right) res++;
            right = Math.max(right, a[1]);
        }
        return res;
    }
}
