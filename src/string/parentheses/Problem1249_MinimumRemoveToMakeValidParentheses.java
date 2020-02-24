package string.parentheses;

public class Problem1249_MinimumRemoveToMakeValidParentheses {

    public String minRemoveToMakeValid(String s) {
        char[] cs = s.toCharArray();
        int n = s.length(), count = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '(') {
                count++;
            }
            else if (cs[i] == ')') {
                if (count == 0) cs[i] = '#'; // To be removed
                else count--;
            }
        }

        // number of '(' can be removed
        if (count > 0) {
            for (int i = n-1; i >= 0; i--) {
                if (count == 0) break;
                if (cs[i] == '(') {
                    cs[i] = '#'; // To be removed
                    count--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (cs[i] != '#') sb.append(cs[i]); // remove
        }

        return sb.toString();
    }

    public String minRemoveToMakeValid_stack(String s) {
        int n = s.length(), start = 0, top = 0;
        int stack[] = new int[n+1];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // push
                stack[++top] = i;
            }
            else if (c == ')') {

                // if stack.isEmpty()
                if (top == start) {
                    stack[++start] = i;
                    ++top;
                }
                // pop
                else {
                    top--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int j = 1;
        for (int i = 0; i < n; i++) {
            if (j > top) {
                sb.append(s.charAt(i));
            }
            else {
                if (i == stack[j]) {
                    j++;
                }
                else {
                    sb.append(s.charAt(i));
                }
            }
        }

        return sb.toString();
    }

}
