package slidingwindow;

import java.util.Arrays;

public class Problem1040_MovingStonesUntilConsecutiveII {

    public int[] numMovesStonesII(int[] A) {
        Arrays.sort(A);

        //1234___ 100 200 300
        //1234___ 208,209,300
        int n = A.length;
        int leftRotation = A[n-1] - n + 2 - A[1];
        int rightRotation = A[n-2]- A[0] - n + 2;
        int highest = Math.max(leftRotation, rightRotation);

        int missings, lowest = Integer.MAX_VALUE;
        for (int hi = 0, lo = 0; hi < n; hi++) {
            while (A[hi] - A[lo] >= n) lo++;
            missings = n - (hi-lo+1);
            if (missings == 1 && A[hi]-A[lo] == n-2) missings = 2;
            lowest = Math.min(lowest, missings);
        }

        return new int[] {lowest, highest};
    }
}
