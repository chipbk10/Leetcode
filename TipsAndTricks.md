- when **going forward** doesn't work, then think the opposite : **going backward** [174]()
- denote `dp[i][distance]` as the minimum number of choices might lead to **`TLE`** or **`MLE`**, then think the opposite: denote `dp[i][j]` as choose `j` times in `i` first elements to reach the furthest distance [871]()
- using **skyline** technique in `matrix` to maintain the minimum point state [1240]()
- graph: Floyd
- **dp 2 times** [1162]() **dp many times until convergence** [1368](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/discuss/524845/C%2B%2B96ms-Forward-and-Backward-DP-Bounded-by-O((m%2Bn)mn))
- **dp bitMask** by row [1349](), given a string with unique characters [1239]()
- **overflow** might lead to a wrong calculation when returning the max (or min) value. `long` comes to rescue, and do `mod` at the end [1339]()
- exactly `K times` = **at most** `K times` - **at most** `K - 1 times`[1248]() [992]() [#](https://leetcode.com/problems/count-number-of-nice-subarrays/discuss/419378/JavaC%2B%2BPython-Sliding-Window-O(1)-Space)
- swap -> union-find [1202]()
- even & odd [1054](https://leetcode.com/problems/distant-barcodes/discuss/299225/Python-Set-Odd-Position-and-Even-Position), [932](https://leetcode.com/problems/beautiful-array/discuss/186679/Odd-%2B-Even-Pattern-O(N))
- [binary matrix](https://www.geeksforgeeks.org/0-1-bfs-shortest-path-binary-graph/) --> Dijkstra -> [bfs + dfs](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/discuss/524886/JavaC%2B%2BPython-BFS-and-DFS)
- find the next larger or next smaller --> using stack [1019]().
In general, using stack to find the closet solution.
Store currently unsolved elements, later if there is a bigger number, withdraw the unsolved elements and get the answer
- intervals: sort by left bound then right bound [1288]()
- `A[i][j]` - consider i & j as 2 separate points (running in 2 separate ranges) -> union find [947](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/discuss/197668/Count-the-Number-of-Islands-O(N)
- initiate UF when doing union (using Map) [947](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/discuss/197668/Count-the-Number-of-Islands-O(N)
- how to guess an element from a set? [843](https://leetcode.com/problems/guess-the-word/discuss/160945/Python-O(n)-with-maximum-overlap-heuristic)
- circular array. Avoid the circular point by working on 2 intervals `[0..n-1)` && `[1..n)` [213](), [1388]()

