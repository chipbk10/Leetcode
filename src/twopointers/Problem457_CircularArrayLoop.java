package twopointers;

public class Problem457_CircularArrayLoop {

    public boolean circularArrayLoop(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) continue;
            int a = A[i];

            int slow = i, fast = next(A,i);
            while (same(a, A[fast]) && same(a, A[next(A, fast)])) {
                if (slow == fast) {
                    if (slow == next(A, slow)) break;
                    return true;
                }
                slow = next(A, slow);
                fast = next(A, next(A, fast));
            }

            slow = i;
            while (same(a, A[slow])) {
                int tmp = next(A, slow);
                A[slow] = 0;
                slow = tmp;
            }
        }
        return false;
    }

    private int next(int[] A, int i) {
        int n = A.length, j = (i + A[i]) % n;
        return (j >= 0) ? j : j+n;
    }

    private boolean same(int a, int b) {
        if (a > 0 && b > 0) return true;
        if (a < 0 && b < 0) return true;
        return false;
    }
}
