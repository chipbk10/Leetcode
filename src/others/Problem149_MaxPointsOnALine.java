package others;

import data.Numbers;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/max-points-on-a-line/discuss/566204/Java-Simple-Code-Math
public class Problem149_MaxPointsOnALine {

    public int maxPoints(int[][] A) {
        int n = A.length;
        if (n < 3) return n;

        int res = 0;
        for (int i = 0; i < n-1; i++) {
            Map<String, Integer> map = new HashMap<>();
            int duplicate = 0, max = 0;

            for (int j = i+1; j < n; j++) {

                int x1 = A[i][0], y1 = A[i][1], x2 = A[j][0], y2 = A[j][1];
                if (x1 == x2 && y1 == y2) {
                    duplicate++;
                    continue;
                }

                String key;
                if (x1 == x2)       key = 0+","+0+","+x1+","+x1;
                else if (y1 == y2)  key = y1+","+y1+","+0+","+0;
                else {
                    int g = Numbers.gcd(y1-y2, x1-x2);
                    int m = (y1-y2)/g, k = (x1-x2)/g;
                    g = Numbers.gcd(y1*k-m*x1, k);
                    int l = (y1*k-m*x1)/g, t = k/g;
                    key = m+","+k+","+l+","+t;
                }

                map.put(key, map.getOrDefault(key, 0)+1);
                max = Math.max(max, map.get(key));
            }

            res = Math.max(res, max + duplicate + 1);
        }

        return res;
    }
}
