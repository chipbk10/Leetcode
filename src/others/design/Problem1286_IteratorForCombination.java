package others.design;

public class Problem1286_IteratorForCombination {

    class CombinationIterator {
        String s, cur;
        int m, n;

        public CombinationIterator(String characters, int combinationLength) {
            s = characters;
            m = combinationLength;
            n = s.length();
        }

        public String next() {
            if (cur == null) cur = s.substring(0, m);
            else {
                int i = find();
                int j = binarySearch(0, n-1, cur.charAt(i));
                String left = (i == 0) ? "" : cur.substring(0, i);
                String right = s.substring(j+1, j+1+m-i);
                cur = left+right;
            }
            return cur;
        }

        public boolean hasNext() {
            return cur == null || find() >= 0;
        }

        private int find() {
            for (int i = m-1, j = n-1; i >= 0; i--, j--) {
                if (cur.charAt(i) != s.charAt(j)) return i;
            }
            return -1;
        }

        private int binarySearch(int lo, int hi, char c) {
            int mid = lo + (hi-lo)/2;
            if (s.charAt(mid) > c) return binarySearch(lo, hi-1, c);
            if (s.charAt(mid) < c) return binarySearch(lo+1, hi, c);
            return mid;
        }
    }
}
