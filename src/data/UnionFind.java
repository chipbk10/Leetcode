package data;

public class UnionFind {

    private int[] parents;
    private int[] rank;
    private int num;

    public UnionFind(int n) {
        num = n;
        parents = new int[n];
        for (int i = 1; i < n; i++) parents[i] = i;
        rank = new int[n];
    }

    public int size() {
        return num;
    }

    public int find(int i) {
        while (i != parents[i]) {
            i = parents[i];
        }
        return i;
    }

    public boolean union(int i, int j) {
        int pi = find(i), pj = find(j);
        if (pi == pj) return false;

        if (rank[pi] > rank[pj]) {
            parents[pj] = pi;
            rank[pi] += rank[pj];
        }
        else {
            parents[pi] = pj;
            rank[pj] += rank[pi];
        }
        num--;
        return true;
    }

}
