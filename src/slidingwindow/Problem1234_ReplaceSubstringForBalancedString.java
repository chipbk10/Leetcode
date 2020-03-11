package slidingwindow;

public class Problem1234_ReplaceSubstringForBalancedString {

    public int balancedString(String s) {
        int n = s.length(), cs[] = new int[4], res = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) cal (cs, s.charAt(i), true);
        for (int i = 0; i < 4; i++) cs[i] -= n/4;

        if (cs[0] == 0 && cs[1] == 0 && cs[2] == 0 && cs[3] == 0) return 0;

        for (int hi = 0, lo = 0; hi < n; hi++) {
            cal(cs, s.charAt(hi), false);
            while (cs[0] <= 0 && cs[1] <= 0 && cs[2] <= 0 && cs[3] <= 0) {
                res = Math.min(res, hi-lo+1);
                cal(cs, s.charAt(lo++), true);
            }
        }
        return res;
    }

    private void cal(int[] cs, char c, boolean inc) {
        int x = (inc) ? 1 : -1;
        if (c == 'Q') cs[0] += x;
        else if (c == 'W') cs[1] += x;
        else if (c == 'E') cs[2] += x;
        else if (c == 'R') cs[3] += x;
    }
}
