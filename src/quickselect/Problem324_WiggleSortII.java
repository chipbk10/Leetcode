package quickselect;

import lib.Array;
import lib.Printer;

import java.util.Arrays;

public class Problem324_WiggleSortII {

    public void wiggleSort(int[] A) {
        int n = A.length, m = (n+1) >> 1;
        int[] copy = Arrays.copyOf(A, n);
        int mid = Array.findKthLargest(copy, m);
        Array.dutchNationalFlag(copy, mid);
        for (int i = m - 1, j = 0; i >= 0; i--, j += 2) A[j] = copy[i];
        for (int i = n - 1, j = 1; i >= m; i--, j += 2) A[j] = copy[i];
    }

    public static void run() {
        Problem324_WiggleSortII solution = new Problem324_WiggleSortII();
        int[] A = {2,3,3,2,2,2,1,1};
        solution.wiggleSort(A);
        Printer.print(A);
    }
}
