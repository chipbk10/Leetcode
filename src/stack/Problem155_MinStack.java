package stack;

import java.util.Stack;

public class Problem155_MinStack {

    class MinStack {

        Stack<int[]> stack = new Stack<>();

        /** initialize your data structure here. */
        public MinStack() {
        }

        public void push(int x) {
            int min = (stack.isEmpty()) ? x : Math.min(x, getMin());
            stack.push(new int[] {x, min});
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
    }
}
