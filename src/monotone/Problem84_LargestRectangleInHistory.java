package monotone;

import java.util.Stack;

public class Problem84_LargestRectangleInHistory {

    public int largestRectangleArea(int[] A) {
        int n = A.length, i = 0, res = 0;
        Stack<Integer> stack = new Stack<>();

        while (i <= n) {
            int a = (i == n) ? 0 : A[i];
            if (stack.isEmpty() || A[stack.peek()] <= a) {
                stack.push(i++);
            }
            else {
                int h = A[stack.pop()];
                int j = stack.isEmpty() ? 0 : stack.peek()+1;
                res = Math.max(res, h * (i-j));
            }
        }

        return res;
    }

}
