package graph.unionfind;

import java.util.HashMap;
import java.util.Map;

public class Problem947_MostStonesRemovedWithSameRowOrColumn {

    Map<Integer, Integer> uf = new HashMap<>();
    int size = 0;

    public int removeStones(int[][] A) {
        for (int[] a : A) union(a[0], ~a[1]);
        return A.length - size;
    }

    private int find(int i) {
        if (uf.putIfAbsent(i, i) == null) size++;
        return (i == uf.get(i)) ? i : find(uf.get(i));
    }

    private void union(int i, int j) {
        int fi = find(i), fj = find(j);
        if (fi == fj) return;
        uf.put(fi, fj);
        size--;
    }
}
