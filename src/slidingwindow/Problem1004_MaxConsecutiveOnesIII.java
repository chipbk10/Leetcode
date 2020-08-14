package slidingwindow;

public class Problem1004_MaxConsecutiveOnesIII {

    public int longestOnes(int[] A, int K) {
        int res = 0, count = 0;
        // 111001101111
        for (int i = 0, j = 0; i < A.length; i++) {
            if (A[i] == 0) {
                K--;
                while (K < 0) {
                    if (A[j++] == 0) {
                        K++;
                    }
                    count--;
                }
            }
            count++;
            res = Math.max(res, count);
        }

        return res;
    }
}
