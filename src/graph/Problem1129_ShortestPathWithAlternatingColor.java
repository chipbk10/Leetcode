package graph;

import java.util.*;

public class Problem1129_ShortestPathWithAlternatingColor {

    /**
     * https://leetcode.com/problems/shortest-path-with-alternating-colors/discuss/514787/It's-just-a-game-to-mix-up-2-graphs
     */
    public static void run() {
        Problem1129_ShortestPathWithAlternatingColor solution = new Problem1129_ShortestPathWithAlternatingColor();
//        int n = 3, red_edges[][] = {{0,1},{1,2}}, blue_edges[][] = {};
        int n = 5, red_edges[][] = {{0,1},{1,2},{2,3},{3,4}}, blue_edges[][] = {{1,2},{2,3},{3,1}};
        int[] res = solution.shortestAlternatingPaths(n, red_edges, blue_edges);
        for (int r : res) System.out.print(r + "-");
    }

    private class E {
        public List<Integer>[] graph;
        public int[] res;
        public boolean[] visisted;
        public E(int n, int[][] edges) {
            graph = new List[n];
            for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
            for (int[] e : edges) graph[e[0]].add(e[1]);
            visisted = new boolean[n];
            res = new int[n];
            Arrays.fill(res, -1);
            res[0] = 0;
        }
    }

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        E RED = new E(n, red_edges), BLUE = new E(n, blue_edges);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();

                // red node
                if (node >= 0) {
                    for (int adj : BLUE.graph[node]) {
                        if (!BLUE.visisted[adj]) {
                            if (BLUE.res[adj] == -1) BLUE.res[adj] = RED.res[node]+1;
                            else BLUE.res[adj] = Math.min(BLUE.res[adj], RED.res[node]+1);
                            queue.offer(-adj);
                            BLUE.visisted[adj] = true;
                        }
                    }
                }

                // blue node
                if (node <= 0) {
                    for (int adj : RED.graph[-node]) {
                        if (!RED.visisted[adj]) {
                            if (RED.res[adj] == -1) RED.res[adj] = BLUE.res[-node]+1;
                            else RED.res[adj] = Math.min(RED.res[adj], BLUE.res[-node]+1);
                            queue.offer(adj);
                            RED.visisted[adj] = true;
                        }
                    }
                }
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (RED.res[i] != -1 && BLUE.res[i] != -1)
                res[i] = Math.min(RED.res[i], BLUE.res[i]);
            else if (RED.res[i] == -1)
                res[i] = BLUE.res[i];
            else
                res[i] = RED.res[i];
        }

        return res;
    }
}
