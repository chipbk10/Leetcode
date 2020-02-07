package dp.distinct.way;

public class Problem790_DominoAndTrominoTiling {

    public int numTilings(int N) {

        if (N == 0) return 0;
        if (N == 1) return 1;
        if (N == 2) return 2;

        int[] g = new int[N+1], u = new int[N+1];
        int mod=(int)1e9 + 7;
        g[0]=0; g[1]=1; g[2]=2;
        u[0]=0; u[1]=1; u[2]=2;

        for(int i=3; i<=N; i++) {
            u[i] = (u[i-1] + g[i-1]) % mod;
            g[i] = ((g[i-1] + g[i-2]) % mod + (2*u[i-2] % mod)) % mod;
        }
        return g[N];
    }

    private int wrong_solution(int N) {
        int[][] dp = new int[N+1][N+1];
        dp[0][0] = 1;

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == j) {
                    if (i >= 1 && j >= 1) dp[i][j] += dp[i-1][j-1];
                    if (i >= 2 && j >= 1) dp[i][j] += dp[i-2][j-1];
                    if (i >= 1 && j >= 2) dp[i][j] += dp[i-1][j-2];
                    if (i >= 2 && j >= 2) dp[i][j] += dp[i-2][j-2];
                }
                if (i == j+1) {
                    if (i >= 2 && j >= 1) dp[i][j] += dp[i-2][j-1];
                }
                if (i+1 == j) {
                    if (i >= 1 && j >= 2) dp[i][j] += dp[i-1][j-2];
                }

                // System.out.println("dp["+i+"]["+j+"]="+dp[i][j]);
            }
        }

        return dp[N][N];
    }

}
