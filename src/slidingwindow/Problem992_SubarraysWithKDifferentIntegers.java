package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class Problem992_SubarraysWithKDifferentIntegers {

    public static void run() {
        Problem992_SubarraysWithKDifferentIntegers solution = new Problem992_SubarraysWithKDifferentIntegers();
        int A[] = {1,1,2,1,2}, k = 2; // max-hi+1,
//        int A[] = {1,1,2,3,2,1,4,5}, k = 3;
        int res = solution.subarraysWithKDistinct(A, k);
        System.out.println(res);
    }

    public int subarraysWithKDistinct(int[] A, int k) {
//        return atMost(A, k) - atMost(A, k-1);
        return hieu_solution(A, k);
    }

    // @lee solution - https://leetcode.com/problems/count-number-of-nice-subarrays/discuss/419378/JavaC%2B%2BPython-Sliding-Window-O(1)-Space
    private int atMost(int[] A, int k) {
        int n = A.length;
        if (n < k) return 0;

        int[] map = new int[n+1];
        int res = 0;
        for (int hi = 0, lo = 0; hi < A.length; hi++) {
            map[A[hi]]++;
            if (map[A[hi]] == 1) k--;

            while (k < 0) {
                map[A[lo]]--;
                if (map[A[lo++]] == 0) k++;
            }

            res += hi-lo+1;
        }

        return res;
    }

    // https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/360147/Share-my-solution
    public int hieu_solution(int[] A, int K) {
        int res = 0, count = 0, lo = 0, hi = 0, max = 0, n;
        Map<Integer, Integer> dp = new HashMap<>();

        while (hi < A.length) {

            // define how many ranges starting with lo, and has K distinct numbers
            while (count == K) {
                res += max - hi + 1;
                n = dp.get(A[lo]);
                if (n == 1) {
                    count--;
                    hi++;
                }
                dp.put(A[lo], n-1);
                lo++;
            }

            // find the first range starting with lo, and has K distinct numbers
            while (hi < A.length) {
                n = dp.getOrDefault(A[hi], 0);
                if (n == 0) count++;
                dp.put(A[hi], n+1);

                // if found hi, then define max
                if (count == K) {
                    max = hi;
                    while (max+1 < A.length) {
                        if (dp.getOrDefault(A[max+1], 0) == 0) break;
                        max++;
                    }
                    break;
                }
                hi++;
            }
        }

        return res;
    }
}
