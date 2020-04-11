package others.minimax;

public class Problem1406_StoneGameIII {

    public String stoneGameIII(int[] A) {
        Integer[][] dp = new Integer[A.length][2];
        int score = minimax(A, 0, 0, dp);
        if (score > 0) return "Alice";
        if (score < 0) return "Bob";
        return "Tie";
    }

    private int minimax(int[] A, int i, int player, Integer[][] dp) {
        if (i >= A.length) return 0;
        if (dp[i][player] != null) return dp[i][player];
        int score = 0, res = (player == 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int j = i; j < Math.min(A.length, i+3); j++) {
            if (player == 0) {
                score += A[j];
                res = Math.max(res, score + minimax(A, j+1, 1, dp));
            }
            else {
                score -= A[j];
                res = Math.min(res, score + minimax(A, j+1, 0, dp));
            }
        }
        dp[i][player] = res;
        return res;
    }
}
