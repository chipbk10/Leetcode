package graph.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Problem864_ShortestPathToGetAllKeys {


    // STILL WRONG
    public int shortestPathAllKeys(String[] A) {
        int m = A.length, n = A[0].length(), KEYS = 0;
        Set<Integer> seen = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = A[i].charAt(j);
                if (c == '@') {
                    queue.offer(new int[] {i, j, 0});
                    seen.add((i*m+j) * 127 + 0);
                }
                else if (c >= 'a' && c <= 'f') KEYS |= (1 << (c-'a'));
            }
        }

        int dirs[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}, res = -1;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int e[] = queue.poll(), i = e[0], j = e[1], keys = e[2];
                for (int[] d : dirs) {
                    int x = i + d[0], y = j + d[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;                   // out of grid
                    char c = A[x].charAt(y);
                    if (seen.contains((x*m+y)*127 + keys)) continue;                    // visited
                    if (c == '#') continue;                                             // wall
                    if (c >= 'A' && c <= 'F' && (keys & (1 << (c-'A'))) == 0) continue; // no key
                    if (c >= 'a' && c <= 'f') keys |= (1 << (c-'a'));                   // update key
                    if (keys == KEYS) return res;
                    queue.offer(new int[] {x, y, keys});
                    seen.add((x*m+y)*127 + keys);
                }
            }
        }

        return -1; // not found
    }
}
