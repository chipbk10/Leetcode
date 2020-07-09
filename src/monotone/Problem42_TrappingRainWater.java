package monotone;

import java.util.Stack;

public class Problem42_TrappingRainWater {

    public int trap(int[] A) {
        int res = 0, n = A.length, i = 0;
        Stack<Integer> stack = new Stack<>();

        while (i < n) {
            if (stack.isEmpty() || A[stack.peek()] >= A[i]) {
                stack.push(i++);
            }
            else {
                int h = A[stack.pop()];
                if (!stack.isEmpty()) {
                    int j = stack.peek();
                    int min = Math.min(A[j], A[i]);
                    res += (min - h) * (i-j-1);
                }
            }
        }

        return res;
    }
}
