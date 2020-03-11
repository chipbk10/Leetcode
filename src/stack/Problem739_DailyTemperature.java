package stack;

import java.util.Stack;

public class Problem739_DailyTemperature {

    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int n = T.length, res[] = new int[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int j = stack.pop();
                res[j] = i-j;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            res[j] = 0;
        }

        return res;
    }
}
