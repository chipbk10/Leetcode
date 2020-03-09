package bit;

import java.util.ArrayList;
import java.util.List;

public class Problem1239_MaximumLengthOfAConcatenatedStringWithUniqueCharacters {

    int max = 0;

    public int maxLength(List<String> arr) {
        backtrack(getListMasks(arr), 0, 0);
        return max;
    }

    private void backtrack(List<Integer> A, int index, int mask) {
        if (index == A.size()) return;
        for (int i = index; i < A.size(); i++) {
            if ((A.get(i) & mask) > 0) continue; // if duplicate
            int newMask = (mask | A.get(i));
            max = Math.max(max, Integer.bitCount(newMask));
            backtrack(A, i+1, newMask);
        }
    }

    private List<Integer> getListMasks(List<String> arr) {
        List<Integer> A = new ArrayList<>();
        for (String s : arr) {
            int m = getMask(s.toCharArray());
            if (m != 0) A.add(m);
        }
        return A;
    }

    private int getMask(char[] cs) {
        int res = 0;
        for (char c : cs) {
            int num = (1 << (c-'a'));
            if ((res & num) > 0) return 0;
            res |= num;
        }
        return res;
    }

}
