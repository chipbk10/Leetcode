package graph.dfs;

import java.util.LinkedList;
import java.util.List;

public class Problem1377_FrogPositionAfterTSeconds {

    List<Integer>[] graph;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        graph = new LinkedList[n+1];
        for (int i = 1; i <= n; i++) graph[i] = new LinkedList<>();
        for (int[] e : edges) {
            int i = e[0], j = e[1];
            graph[i].add(j);
            graph[j].add(i);
        }

        // edge cases
        if (graph[1].size() == 0) return (target == 1) ? 1 : 0;
        if (target == 1) return (graph[1].size() > 0) ? 0 : 1;

        return dfs(0, 1, t, target);
    }

    private double dfs(int parent, int node, int t, int target) {

        // run out of time
        if (t == 0) return (node == target) ? 1 : 0;

        // reach the target
        if (node == target) return (graph[node].size() == 1) ? 1 : 0;

        for (int next : graph[node]) {
            if (next == parent) continue;
            double p = dfs(node, next, t-1, target);
            if (p != 0) {
                int childNodes = graph[node].size();
                if (node != 1) childNodes--;
                return p/childNodes;
            }
        }
        return 0;
    }
}
