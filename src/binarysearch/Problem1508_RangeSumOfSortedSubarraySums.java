package binarysearch;

import lib.Printer;

public class Problem1508_RangeSumOfSortedSubarraySums {

    int B[];
    public int rangeSum(int[] A, int n, int left, int right) {
        B = new int[n+1];
        for (int i = 0; i < n; i++) B[i+1] = B[i] + A[i];
        return sum_k_sums(right) - sum_k_sums(left-1);
    }

    private int sum_k_sums(int k) {
        int score = kth_score(k), res = 0, i;
//        System.out.println(score);
        for (int j = 1; j < B.length; j++) {
            i = j-1;
            while (i >= 0 && B[j]-B[i] <= score) {
//                System.out.print(B[j]-B[i] + "  ");
                res += B[j]-B[i];
                i--;
            }
            if (i == j-1) break;
        }
        return res;
    }

    private int kth_score(int k) {
        if (k == 0) return 0;
        int lo = 1, hi = B[B.length-1];
        while (lo < hi) {
            int mi = lo + (hi-lo)/2;
            if (count_sum_under(mi) < k) lo = mi+1;
            else hi = mi;
        }
        return lo;
    }

    private int count_sum_under(int score) {
        int res = 0;
        for (int j = 1, i = 0; j < B.length; j++) {
            while (B[j]-B[i] > score) i++;
            if (i == j) break;
            res += j-i;
        }
        return res;
    }

    public static void run() {
        Problem1508_RangeSumOfSortedSubarraySums solution = new Problem1508_RangeSumOfSortedSubarraySums();
        int A[] = {1,2,3,4}, n = 4, left = 1, right = 5;
        int res = solution.rangeSum(A, n, left, right);
        Printer.print(res);
    }
}
