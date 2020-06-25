package quickselect;

import lib.Printer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Problem347_TopKFrequentElements {

    Map<Integer, Integer> map;

    public int[] topKFrequent(int[] A, int k) {
        map = new HashMap<>();
        for (int a : A) map.put(a, map.getOrDefault(a, 0)+1);

        int i = 0, n = map.size(), nums[] = new int[n];
        for (int a : map.keySet()) nums[i++] = a;

        findKthLargest(nums, k);

        int[] res = new int[k];
        for (i = n-k; i < n; i++) {
            res[i-n+k] = nums[i];
        }
        return res;
    }

    public void swap(int[] A, int i, int j) {
        if (i == j) return;
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void shuffle(int[] A) {
        Random random = new Random();
        // Attention: Reverse loop
        for (int i = A.length-1; i >= 0; i--) {
            swap(A, i, random.nextInt(i+1));
        }
    }

    public int findKthLargest(int[] A, int k) {
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

    public int quickselect(int[] A, int lo, int hi) {
        int pivot = lo; // choose the pivot at lo
        while (true) {
            while (lo <= hi && map.get(A[lo]) <= map.get(A[pivot])) lo++;
            while (lo <= hi && map.get(A[hi]) > map.get(A[pivot])) hi--;
            if (lo > hi) break;
            swap(A, lo, hi);
        }
        swap(A, hi, pivot);
        return hi;
    }

    public static void run() {
        Problem347_TopKFrequentElements solution = new Problem347_TopKFrequentElements();
        int k = 2, A[] = {1,1,1,2,2,3};
        int[] res = solution.topKFrequent(A, k);
        Printer.print(res);
    }
}
