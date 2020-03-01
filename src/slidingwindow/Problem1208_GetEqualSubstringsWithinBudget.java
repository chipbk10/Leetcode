package slidingwindow;

public class Problem1208_GetEqualSubstringsWithinBudget {

    public int equalSubstring(String s, String t, int maxCost) {

        return hieu_solution(s, t, maxCost);
    }

    public int hieu_solution(String s, String t, int maxCost) {
        int res = 0;
        for (int hi = 0, lo = 0; hi < s.length(); hi++) {

            maxCost -= Math.abs(s.charAt(hi) - t.charAt(hi));

            while (maxCost < 0) {
                maxCost += Math.abs(s.charAt(lo) - t.charAt(lo));
                lo++;
            }

            res = Math.max(res, hi-lo+1);
        }

        return res;
    }

    public int tricky_solution(String s, String t, int maxCost) {
        int hi = 0, lo = 0;
        for (; hi < s.length(); hi++) {
            maxCost -= Math.abs(s.charAt(hi) - t.charAt(hi));
            if (maxCost < 0) {
                maxCost += Math.abs(s.charAt(lo) - t.charAt(lo++));
            }
        }
        return hi-lo;
    }
}
