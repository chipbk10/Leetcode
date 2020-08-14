package backtrack;

import java.util.Arrays;

public class Problem1307_VerbalArithmeticPuzzle {


    public static void run() {
        Problem1307_VerbalArithmeticPuzzle solution = new Problem1307_VerbalArithmeticPuzzle();
        String words[] = {"SEND", "MORE"}, result = "MONEY";
        boolean res = solution.isSolvable(words, result);
        if (res) System.out.println("true");
        else System.out.println("false");
    }

    /*
    int[] memo = new int[26];
    boolean[] used = new boolean[10];
    int m, n;

    public boolean isSolvable(String[] words, String result) {
        m = words.length;
        n = result.length();
        if (m == 0 && n == 0) return true;
        if (m*n == 0) return false;

        Array.fill(memo, -1);
        return backtrack(words, result, 0, 0, n-1, 0);
    }

    private boolean backtrack(String[] W, String R, int cache, int sum, int i, int j) {
        if (i == -1) return (cache == 0);
        if (j >= m) {
            sum += cache;
            cache = sum/10;
            sum %= 10;
            int ind = R.charAt(i)-'A';
            if (memo[ind] == sum) {
                return backtrack(W, R, cache, 0, i-1, 0);
            }
            else {
                if (memo[ind] == -1) {
                    if (used[sum]) return false;
                    memo[ind] = sum;
                    used[sum] = true;
                    if (backtrack(W, R, cache, 0, i-1, 0)) return true;
                    memo[ind] = -1;
                    used[sum] = false;
                    return false;
                }
                else {
                    return false;
                }
            }
        }
        else {
            int len = W[j].length();
            if (i >= len) return backtrack(W, R, cache, sum, i, j+1);
            int ind = W[j].charAt(len-i-1)-'A';
            if (memo[ind] != -1) return backtrack(W, R, cache, sum+memo[ind], i, j+1);
            for (int k = 0; k < 10; k++) {
                if (used[k]) continue;
                used[k] = true;
                memo[ind] = k;
                if (backtrack(W, R, cache, sum+k, i, j+1)) return true;
                memo[ind] = -1;
                used[k] = false;
            }
            return false;
        }
    }
     */

    public boolean isSolvable(String[] words, String result) {
        int[] map = new int[128];
        Arrays.fill(map, -1);
        boolean ret = backtrack(words, result, 0, 0, 0, map, new boolean[10]);
        return ret;
    }

    /**
     * @param words
     * @param result
     * @param index words index
     * @param h     result index from right to left as layer index
     * @param sum   sum of current layer
     * @param map   char -> digit
     * @param used  digit used
     * @return
     */
    boolean backtrack(String[] words, String result, int index, int h, int sum, int[] map, boolean used[]) {
        if (index == words.length) {
            if (h == result.length()) return sum == 0; // return
            char c = result.charAt(result.length() - h - 1);
            int mod = sum % 10;
            if (h == result.length() - 1 && mod == 0) return false;
            if (map[c] < 0) { // hasn't mapping
                if (used[mod]) return false; // failed by wanted digit be used
                map[c] = mod;
                used[mod] = true;
                if (backtrack(words, result, 0, h + 1, sum / 10, map, used)) return true; //jump next layer
                used[mod] = false;
                map[c] = -1;
            } else {// has mapping
                if (map[c] != mod) return false;//check is ok
                if (backtrack(words, result, 0, h + 1, sum / 10, map, used)) return true; // jump next layer
            }
            return false;
        }

        if (index == words.length) return backtrack(words, result, index, h, sum, map, used);
        String word = words[index];
        if (word.length() <= h)
            return backtrack(words, result, index + 1, h, sum, map, used);
        char c;int v;
        if ((v = map[(c = word.charAt(word.length() - h - 1))]) >= 0) { // current char has mapping digit
            if (v == 0 && h == word.length() - 1) return false;
            return backtrack(words, result, index + 1, h, sum + v, map, used);
        }
        for (int j = 0; j < 10; j++) { // hasn't mapping digit
            if (used[j] || j == 0 && h == word.length() - 1) continue;
            map[c] = j;
            used[j] = true;
            if (backtrack(words, result, index + 1, h, sum + j, map, used)) {
                return true;
            }
            used[j] = false;
            map[c] = -1;
        }
        return false;
    }
}
