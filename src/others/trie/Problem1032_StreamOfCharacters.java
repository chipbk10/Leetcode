package others.trie;

import data.Trie;

import java.util.ArrayList;
import java.util.List;

public class Problem1032_StreamOfCharacters {

    class StreamChecker {

        Trie root = new Trie();
        List<Character> list = new ArrayList<>();

        public StreamChecker(String[] words) {
            for (String s : words) {
                Trie node = root;
                for (int i = s.length()-1; i >= 0; i--) {
                    if (node.children[s.charAt(i)-'a'] == null) {
                        node.children[s.charAt(i)-'a'] = new Trie();
                    }
                    node = node.children[s.charAt(i)-'a'];
                }
                node.end = true;
            }
        }

        public boolean query(char letter) {
            list.add(letter);
            Trie node = root;
            for (int i = list.size()-1; i >= 0; i--) {
                Trie next = node.children[list.get(i)-'a'];
                if (next == null) return false;
                if (next.end) return true;
                node = next;
            }
            return false;
        }
    }
}
