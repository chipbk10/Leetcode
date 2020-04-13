package stack;

public class Problem946_ValidateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int i = -1, j = 0;
        for (int x : pushed) {
            pushed[++i] = x;
            while (i >= 0 && pushed[i] == popped[j]) {
                j++;
                i--;
            }
        }
        return j == popped.length;
    }
}
