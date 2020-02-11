package dp.min_max_path_to_reach_target;

import java.util.Arrays;
import java.util.List;

public class Problem120_Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size(), dp[] = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            List<Integer> list = triangle.get(i);
            int prev = Integer.MAX_VALUE;
            for (int j = 0; j < list.size(); j++) {
                int cur = dp[j];
                dp[j] = Math.min(prev, cur) + list.get(j);
                prev = cur;
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) res = Math.min(res, dp[i]);

        return res;
    }
}
