package graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Problem1306_JumpGameIII {

    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) return true;

        Set<Integer> seen = new HashSet<>();
        seen.add(start);

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        int n = arr.length;

        while (!stack.isEmpty()) {
            int i = stack.pop();
            int d = arr[i];

            if (i+d >= 0 && i+d < n && !seen.contains(i+d)) {
                if (arr[i+d] == 0) return true;
                seen.add(i+d);
                stack.push(i+d);
            }

            if (i-d >= 0 && i-d < n && !seen.contains(i-d)) {
                if (arr[i-d] == 0) return true;
                seen.add(i-d);
                stack.push(i-d);
            }
        }

        return false;
    }
}
