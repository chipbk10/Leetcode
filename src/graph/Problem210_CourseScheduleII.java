package graph;
import java.util.*;

public class Problem210_CourseScheduleII {

    public static void run() {
        Problem210_CourseScheduleII solution = new Problem210_CourseScheduleII();
//        int numCourses = 2, prerequisites[][] = {{1,0}};
        int numCourses = 3, prerequisites[][] = {{2,1},{1,0}};
        int[] res = solution.findOrder(numCourses, prerequisites);
        for (int i : res) {
            System.out.print(i+"-");
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // return bfs(numCourses, prerequisites);
        return dfs(numCourses, prerequisites);
    }

    private int index;

    private int[] dfs(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        List<Integer>[] G = new List[n];
        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<>();
        }

        for (int[] p : prerequisites) {
            int from = p[1], to = p[0];
            G[from].add(to);
        }

        int[] res = new int[n];
        index = n;
        boolean[] seen = new boolean[n], processed = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (isCyclic(G, i, seen, processed, res)) return new int[0];
        }

        return res;
    }

    private boolean isCyclic(List<Integer>[] G, int i, boolean[] seen, boolean[] processed, int[] res) {
        if (processed[i]) return false;
        if (seen[i]) return true;
        seen[i] = true;
        for (int j : G[i]) {
            if (isCyclic(G, j, seen, processed, res)) return true;
        }
        seen[i] = false;
        processed[i] = true;
        res[--index] = i;
        return false;
    }

    private int[] bfs(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        int[] dependencies = new int[n];
        List<Integer>[] G = new List[n];
        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<>();
        }
        for (int[] p : prerequisites) {
            int from = p[1], to = p[0];
            G[from].add(to);
            dependencies[to]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (dependencies[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0, res[] = new int[n];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int from = queue.poll();
                for (int to : G[from]) {
                    dependencies[to]--;
                    if (dependencies[to] == 0) {
                        queue.offer(to);
                    }
                }
                res[count] = from;
                count++;
            }
        }

        return (count == n) ? res : new int[0];
    }
}
