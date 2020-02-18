package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem207_CourseSchedule {

    public static void run() {
        Problem207_CourseSchedule solution = new Problem207_CourseSchedule();
        int numCourses = 2, prerequisites[][] = {{0,1}};
//        int numCourses = 2, prerequisites[][] = {{1,0},{0,1}};
        boolean res = solution.canFinish(numCourses, prerequisites);
        if (res) System.out.println(true);
        else System.out.println(false);
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
//        return dfs(numCourses, prerequisites);
        return bfs(numCourses, prerequisites);
    }

    private boolean bfs(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        ArrayList<Integer>[] G = new ArrayList[n];
        int[] degree = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<>();
        }
        for (int[] e : prerequisites) {
            G[e[1]].add(e[0]);
            degree[e[0]]++;
        }
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int c = queue.poll();
            count++;
            for (int j : G[c]) {
                if (--degree[j] == 0){
                    queue.add(j);
                }
            }
        }
        return count == n;
    }

    private boolean dfs(int numCourses, int[][] prerequisites) {
        List<Integer>[] G = new List[numCourses];

        for (int[] e : prerequisites) {
            if (G[e[1]] == null) G[e[1]] = new ArrayList();
            G[e[1]].add(e[0]);
        }
        boolean[] seen = new boolean[numCourses];
        boolean[] processed = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (dfs_isCyclic(G, i, seen, processed)) return false;
        }
        return true;
    }

    // DFS
    private boolean dfs_isCyclic(List<Integer>[] G, int i, boolean[] seen, boolean[] processed) {
        if (processed[i]) return false;
        if (seen[i]) return true;
        if (G[i] == null) {
            processed[i] = true;
            return false;
        }

        seen[i] = true;
        for (int j : G[i]) {
            if (dfs_isCyclic(G, j, seen, processed)) return true;
        }
        seen[i] = false;
        processed[i] = true;
        return false;
    }
}