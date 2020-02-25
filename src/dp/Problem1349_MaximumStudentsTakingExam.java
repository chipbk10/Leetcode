package dp;

public class Problem1349_MaximumStudentsTakingExam {

    public int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        int size = (1<<n);

        int[] original = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == '#') {
                    original[i] += (1<<j);
                }
            }
        }

        // dp[i][state]
        int dp[][] = new int[m][size], res = 0;
        for (int i = 0; i < m; i++) {
            for (int cur = 0; cur < size; cur++) {
                if ((cur & original[i]) != 0) continue; // seat is broken
                if ((cur & (cur << 1)) != 0) continue;  // left seat is occupied
                if ((cur & (cur >> 1)) != 0) continue;  // right seat is occupied

                int curOccupied = Integer.bitCount(cur);
                for (int prev = 0; prev < size; prev++) {

                    if ((prev & (cur << 1)) != 0) continue; // upper-left seat is occupied
                    if ((prev & (cur >> 1)) != 0) continue; // upper-right seat is occupied

                    int prevOccupied = (i >= 1) ? dp[i-1][prev] : 0;
                    dp[i][cur] = Math.max(dp[i][cur], prevOccupied + curOccupied);
                }
                res = Math.max(res, dp[i][cur]);
            }
        }

        return res;
    }
}
