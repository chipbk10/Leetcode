package slidingwindow;

public class Problem567_PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length(), n = s2.length(), count[] = new int[26];
        for (char c : s1.toCharArray()) count[c-'a']++;

        int invalids = 0;
        for (int i = 0, j = 0; i < n; i++) {
            int k = s2.charAt(i)-'a';
            if (count[k] > 0) m--;
            else invalids++;
            count[k]--;

            if (m == 0) return true;

            while (invalids > 0) {
                k = s2.charAt(j)-'a';
                if (count[k] < 0) invalids--;
                else m++;
                count[k]++;
                j++;
            }
        }
        return false;
    }

}
