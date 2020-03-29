package dp.decision_making;

public class Problem213_HouseRobberII {

    public int rob(int[] A) {
        int n = A.length;
        if (n == 1) return A[0];
        return Math.max(findMax(A, 0, n-2), findMax(A, 1, n-1));
    }

    public int findMax(int[] A, int lo, int hi) {
        int max1 = 0, max2 = 0, max = 0;
        for (int i = lo; i <= hi; i++) {
            max = Math.max(max2 + A[i], max1);
            max2 = max1;
            max1 = max;
        }
        return max;
    }
}
