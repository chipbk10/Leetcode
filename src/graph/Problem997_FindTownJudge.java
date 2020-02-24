package graph;

public class Problem997_FindTownJudge {

    public int findJudge(int N, int[][] trust) {
        int[] count = new int[N+1]; // in_degree - out_degree
        for (int[] t: trust) {
            count[t[0]]--; // out_degree
            count[t[1]]++; // in_degree
        }
        for (int i = 1; i <= N; ++i) {

            // must be: in_degree = N-1 && out_degree = 0
            // N-1 is the max possible value
            if (count[i] == N - 1) return i;
        }
        return -1;
    }
}
