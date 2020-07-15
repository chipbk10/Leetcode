package slidingwindow;

public class Problem424_LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        int n = s.length(), count[] = new int[26], max = 0, j = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            max = Math.max(max, ++count[c-'A']);
            while (i-j+1 - max > k) {
                c = s.charAt(j++);
                count[c-'A']--;
            }
        }
        return n-j;
    }
}
