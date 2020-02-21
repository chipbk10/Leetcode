package graph;

import java.util.*;

/**
 * Interesting problem:
 *
 *  Given a graph, in which there is at least a valid way to go through all edges
 *  that each edge is gone through only one.
 *
 *  Now, find the way to print that way in a lexical order.
 *
 * Solution:
 *  Using DFS, each time go through an edge, we remove it.
 *  We will iterate by lexical order.
 *  When we get stuck, we know that this edge will be the last one in our journey.
 *
 */

public class Problem332_ReconstructItinerary {

    Map<String, PriorityQueue<String>> G;

    public List<String> findItinerary(List<List<String>> tickets) {
        G = new HashMap<>();
        for (List<String> pair : tickets) {
            String from = pair.get(0);
            String to = pair.get(1);
            G.putIfAbsent(from, new PriorityQueue<>());
            G.get(from).offer(to);
        }

        List<String> res = new LinkedList<>();
        dfs("JFK", res);
        return res;
    }

    private void dfs(String from, List<String> res) {
        PriorityQueue<String> queue = G.get(from);
        while (queue != null && !queue.isEmpty()) {
            String to = queue.poll();
            dfs(to, res);
        }
        res.add(0, from);
    }
}
