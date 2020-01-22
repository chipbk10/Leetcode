package string;

public class Problem5_LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        return expand_around_center(s);
    }

    private String expand_around_center(String s) {
        int n = s.length(), min = 0, max = 0, i = 0;
        if (n == 0) return "";

        while (i < n) {

            int l = i-1, r = i+1;
            while (r < n && s.charAt(r) == s.charAt(i)) r++;
            i = r;

            while (l >= 0 && r < n) {
                if (s.charAt(l) != s.charAt(r)) break;
                l--;
                r++;
            }

            if (r-l-2 > max-min) {
                max = r-1;
                min = l+1;
            }
        }

        return s.substring(min, max+1);
    }

    private String check_length(String s) {
        for (int n = s.length()-1; n >= 1; n--) {
            for (int i = 0; i <= s.length() - 1 - n; i++) {
                if (isPalindrome(s, i, i+n)) return s.substring(i, i+n+1);
            }
        }
        return "";
    }

    private boolean isPalindrome(String s, int lo, int hi) {
        while (lo <= hi && s.charAt(lo) == s.charAt(hi)) {
            lo++;
            hi--;
        }
        return (lo > hi);
    }
}
