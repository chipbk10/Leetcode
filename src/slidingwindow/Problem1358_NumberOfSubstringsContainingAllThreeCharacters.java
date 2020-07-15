package slidingwindow;

public class Problem1358_NumberOfSubstringsContainingAllThreeCharacters {

    public int numberOfSubstrings(String s) {
        // abcabc
        // abc: 0-x = (0+1) = 1
        // abca: 1-x = (1+1) = 2
        // abcab: 2-x = (2+1) = 3
        // abcabc: 3-x = (3+1) = 4
        int na = 0, nb = 0, nc = 0, l = 0, res = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a') na++;
            else if (c == 'b') nb++;
            else nc++;

            // if (na > 0 && nb > 0 && nc > 0) l = 0;

            while (na > 0 && nb > 0 && nc > 0) {
                c = s.charAt(j++);
                if (c == 'a') na--;
                else if (c == 'b') nb--;
                else nc--;
                l++;
            }

            res += l;
        }

        return res;
    }
}
