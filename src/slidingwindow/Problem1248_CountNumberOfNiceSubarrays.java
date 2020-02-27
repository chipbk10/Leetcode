package slidingwindow;

public class Problem1248_CountNumberOfNiceSubarrays {


    /**
     * |--l--o----window------o----r---|
     *
     * number of valid window = l + r + lr + 1 = (l+1)(r+1)
     *
     * l+1      --> decrease the size of valid window until it's invalid
     *          --> it will take l+1 step from left
     * r(l+1)   --> increase the size of invalid window until it's valid
     *          --> it will take r+1 step to the right
     */
    public int numberOfSubarrays(int[] A, int k) {
        int n = A.length;
        if (n < k) return 0;

        int count = 0, res = 0;
        for (int lo = 0, hi = 0; hi < n; hi++) {

            // increase the size of window: (r+1)(l+1)
            if (k > 0) {
                if (A[hi] % 2 == 1) {
                    k--;
                    count = 0;
                }
            }

            // decrease the size of window: (l+1)
            while (k == 0) {
                if (A[lo++] % 2 == 1) {
                    k++;
                }
                count++;
            }

            res += count;
        }

        return res;
    }
}
