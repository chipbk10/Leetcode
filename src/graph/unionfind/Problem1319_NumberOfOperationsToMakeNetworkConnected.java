package graph.unionfind;

import data.UnionFind;

public class Problem1319_NumberOfOperationsToMakeNetworkConnected {

    public int makeConnected(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);
        int redundant = 0;
        for (int[] con: connections) {
            if (!uf.union(con[0], con[1])) redundant++;
        }
        if (redundant < uf.size()-1) return -1;
        return uf.size()-1;
    }
}
