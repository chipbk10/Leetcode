package others;

import java.util.ArrayList;
import java.util.List;

public class Problem843_GuessWord {

    interface Master {
        int guess(String word);
    }

    public void findSecretWord(String[] wordList, Master master) {
        // most overlapped word
        // guess --> matches
        // find all with matches

        while (true) {
            String guess = mostOverlappedWord(wordList);
            int matches = master.guess(guess);
            if (matches == 6) return;
            List<String> wordList2 = find(wordList, guess, matches);
            wordList = wordList2.toArray(new String[0]);
        }
    }

    private String mostOverlappedWord(String[] wordList) {
        int count[][] = new int[6][26], max = 0;
        for (String w : wordList) {
            for (int i = 0; i < w.length(); i++) {
                count[i][w.charAt(i) - 'a']++;
            }
        }
        String res = "";
        for (String w : wordList) {
            int score = 0;
            for (int i = 0; i < w.length(); i++) {
                score += count[i][w.charAt(i)-'a'];
            }
            if (score > max) {
                max = score;
                res = w;
            }
        }
        return res;
    }

    private List<String> find(String[] wordList, String word, int k) {
        List<String> res = new ArrayList<>();
        for (String w : wordList) {
            if (match(word, w) == k) res.add(w);
        }
        return res;
    }

    private int match(String w1, String w2) {
        int res = 0;
        for (int i = 0; i < w1.length(); i++)
            if (w1.charAt(i) == w2.charAt(i)) res++;
        return res;
    }
}
