package lib;

import java.util.Random;

public class Array {

    public static void swap(int[] A, int i, int j) {
        if (i == j) return;
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void shuffle(int[] A) {
        Random random = new Random();
        // Attention: Reverse loop
        for (int i = A.length-1; i >= 0; i--) {
            swap(A, i, random.nextInt(i+1));
        }
    }

    public static int findKthLargest(int[] A, int k) {
        int lo = 0, hi = A.length-1; k = A.length-k;
        // without suffle: O(N) best case / O(N^2) worst case running time + O(1) memory
        // with suffle: the probability O(N) is rare (1/N!), O(1) memory
        shuffle(A);
        while (true) {
            int pivot = quickselect(A, lo, hi);
            if (pivot == k) break;
            if (pivot < k) lo = pivot+1;
            else hi = pivot-1;
        }
        return A[k];
    }

    public static int quickselect(int[] A, int lo, int hi) {
        int pivot = lo; // choose the pivot at lo
        while (true) {
            while (lo <= hi && A[lo] <= A[pivot]) lo++;
            while (lo <= hi && A[hi] > A[pivot]) hi--;
            if (lo > hi) break;
            swap(A, lo, hi);
        }
        swap(A, hi, pivot);
        return hi;
    }

    public static void dutchNationalFlag(int[] A, int mid) {
        int i = 0, j = 0, k = A.length;
        while (j < k) {
            if (A[j] < mid) swap(A, i++, j++);
            else if (A[j] > mid) swap(A, j, --k);
            else j++;
        }
    }
}
