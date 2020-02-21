package graph.topological;

import java.util.Arrays;

public class Problem1334_FindCityWithSmallestNumberOfNeighborsAtAThresholdDistance {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dis = new int[n][n];
        for (int[] row : dis) {
            Arrays.fill(row, 10001);
        }

        for (int i = 0; i < n; i++) dis[i][i] = 0;
        for (int[] e : edges) {
            dis[e[0]][e[1]] = dis[e[1]][e[0]] = e[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }

        int res = -1, smallest = n;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (dis[i][j] <= distanceThreshold) {
                    count++;
                }
            }

            if (count <= smallest) {
                smallest = count;
                res = i;
            }
        }

        return res;
    }
}
