package binarysearch;

public class Problem1351_CountNegativeNumbersInSortedMatrix {

    public int countNegatives(int[][] grid) {
        // 4    3   2   -1
        // 3    2   1   -1
        // 1    1  -1   -2
        //-1   -1  -2   -3

        int m = grid.length;
        if (m == 0) return 0;

        int n = grid[0].length, ind = n-1;
        int res = 0;

        for (int i = 0; i < m; i++) {
            int minPositive = findMinPositive(grid[i], ind);

            if (minPositive < 0) {
                res += (m-i) * n;
                return res;
            }

            ind = minPositive;
            res += n-minPositive-1;
        }

        return res;
    }

    private int findMinPositive(int[] A, int n) {
        int start = 0, end = n, mid;
        while (start <= end) {
            mid = start + (end-start) / 2;
            if (A[mid] < 0) end = mid-1;
            else start = mid+1;
        }
        return end;
    }

}
