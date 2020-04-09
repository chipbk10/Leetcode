package twopointers;

public class Problem844_BackspaceStringCompare {

    private int down(String s, int i) {
        int count = 0;
        while (i >= 0 && (s.charAt(i) == '#' || count > 0)) {
            count = (s.charAt(i--) == '#') ? count+1 : count-1;
        }
        return i;
    }

    public boolean backspaceCompare(String S, String T) {
        for (int i = S.length()-1, j = T.length()-1; i >= 0 || j >= 0; i--, j--) {
            i = down(S, i);
            char cs = (i >= 0) ? S.charAt(i) : '.';

            j = down(T, j);
            char ct = (j >= 0) ? T.charAt(j) : '.';

            if (cs != ct) return false;
        }
        return true;
    }
}
