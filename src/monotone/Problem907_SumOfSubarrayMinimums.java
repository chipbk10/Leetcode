package monotone;

import java.util.Stack;

public class Problem907_SumOfSubarrayMinimums {

    public int sumSubarrayMins(int[] A) {
        // a0, a1, a2 --> dp[2]
        // a0, a1, a2, a3
        // a3 >= a2 --> dp[3] = dp[2] + a3
        // a3 < a2 --> compare a3 with a1 ...
        // dp[i] = dp[j] + (i-j)*A[i]
        // increasing stack

        int n = A.length, dp[] = new int[n], res = 0, mod = (int)1e9+7;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
                stack.pop();
            }

            int j = stack.isEmpty() ? -1 : stack.peek();
            dp[i] = ((j == -1) ? 0 : dp[j]) + (i-j)*A[i];
            res += dp[i];
            res %= mod;
            stack.push(i);
        }

        return res;
    }
}
