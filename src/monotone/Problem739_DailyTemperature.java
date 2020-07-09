package monotone;

import java.util.Stack;

public class Problem739_DailyTemperature {

    public int[] dailyTemperatures(int[] T) {
        // (73, 7), --> remove
        // (76, 6)
        // (72, 5)  --> remove
        // (69, 4)  --> remove
        // (71, 3)  --> remove
        // (75, 2)

        int n = T.length, res[] = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n-1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek()-i;
            stack.push(i);
        }
        return res;
    }
}
