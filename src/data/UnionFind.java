package data;

public class UnionFind {

    private int[] parents;
    private int[] rank;
    private int num;

    public UnionFind(int n) {
        num = n;
        parents = new int[n];
        rank = new int[n];
        for (int i = 1; i < n; i++) parents[i] = i;
        for (int i = 0; i < n; i++) rank[i] = 1;
    }

    public int size() {
        return num;
    }

    public int size(int i) { return rank[find(i)]; }

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

    public boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }
}
