package binarysearch;

public class Problem410_SplitArrayLargestSum {

    public int splitArray(int[] A, int m) {
        int max = 0; long sum = 0;
        for (int a : A) {
            max = Math.max(max, a);
            sum += a;
        }

        if (m == 1) return (int)sum;

        long lo = max, hi = sum;
        while (lo <= hi) {
            long mid = (lo+hi)/2;
            if (isValid(A, m, mid)) hi = mid-1;
            else lo = mid+1;
        }

        return (int)lo;
    }

    private boolean isValid(int[] A, int m, long max) {
        int sum = 0, count = 1;
        for (int a : A) {
            sum += a;
            if (sum > max) {
                sum = a;
                count++;
                if (count > m) return false;
            }
        }
        return true;
    }
}
