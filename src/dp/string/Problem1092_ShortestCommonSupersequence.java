package dp.string;

public class Problem1092_ShortestCommonSupersequence {

    public String shortestCommonSupersequence(String str1, String str2) {
        return dp_iterative_improve_time(str1, str2);
    }

    public String dp_iterative(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        String[][] dp = new String[m+1][n+1];
        dp[0][0] = "";

        for (int i = 1; i <= m; i++) dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
        for (int j = 1; j <= n; j++) dp[0][j] = dp[0][j-1] + s2.charAt(j-1);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + s1.charAt(i-1);
                else {
                    if (dp[i-1][j].length() < dp[i][j-1].length())
                        dp[i][j] = dp[i-1][j] + s1.charAt(i-1);
                    else
                        dp[i][j] = dp[i][j-1] + s2.charAt(j-1);
                }
            }
        }

        return dp[m][n];
    }

    public String dp_iterative_improve_space(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m < n) return shortestCommonSupersequence(s2, s1);

        String[] dp = new String[n+1];
        dp[0] = "";
        for (int i = 1; i <= n; i++) dp[i] = dp[i-1] + s2.charAt(i-1);

        for (int i = 1; i <= m; i++) {
            String prev = dp[0];
            dp[0] += s1.charAt(i-1);

            for (int j = 1; j <= n; j++) {
                String tmp = dp[j];
                if (s1.charAt(i-1) == s2.charAt(j-1))
                    dp[j] = prev + s1.charAt(i-1);
                else {
                    if (dp[j].length() < dp[j-1].length())
                        dp[j] += s1.charAt(i-1);
                    else
                        dp[j] = dp[j-1] + s2.charAt(j-1);
                }
                prev = tmp;
            }
        }

        return dp[n];
    }

    public String dp_iterative_improve_time(String s1, String s2) {
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        int m = c1.length, n = c2.length;
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++) dp[i][0] = i;
        for(int i = 0; i <= n; i++) dp[0][i] = i;
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(c1[i - 1] == c2[j - 1]) dp[i][j] = 1 + dp[i - 1][j - 1];
                else{
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int l = dp[m][n];
        char[] res = new char[l];
        int i = m, j = n;
        while(i > 0 && j > 0) {
            if(c1[i - 1] == c2[j - 1]) {
                res[--l] = c1[i - 1];
                i--;
                j--;
            }else if(dp[i - 1][j] < dp[i][j - 1]) {
                res[--l] = c1[i - 1];
                i--;
            }else{
                res[--l] = c2[j - 1];
                j--;
            }
        }
        while(i > 0) {res[--l] = c1[i - 1]; i--;}
        while(j > 0) {res[--l] = c2[j - 1]; j--;}

        return new String(res);

    }
}
