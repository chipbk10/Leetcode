package slidingwindow;

public class Problem1248_CountNumberOfNiceSubarrays {


    /**
     * |--l--o#--o#--window------o----r---o|
     *
     * number of valid window = l + r + lr + 1 = (l+1)(r+1)
     *
     * l+1          --> decrease the size of valid window until it's invalid
     *              --> it will take l+1 step from left
     * (r+1)(l+1)   --> increase the size of invalid window until it's valid
     *              --> it will take r+1 step to the right
     */
    public int numberOfSubarrays(int[] A, int k) {
        int n = A.length;
        if (n < k) return 0;

        int count = 0, res = 0;
        for (int lo = 0, hi = 0; hi < n; hi++) {

            // increase the size of window: (r+1)*(l+1)
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

    public int numberOfSubarrays_DP(int[] A, int k) {
        int n = A.length, map[] = new int[n+1];
        map[0] = 1;

        int sum = 0, res = 0;
        for (int i = 0; i < n; i++) {
            sum += (A[i] & 1);
            if (sum >= k) res += map[sum-k];
            map[sum]++;
        }
        return res;
    }
}
