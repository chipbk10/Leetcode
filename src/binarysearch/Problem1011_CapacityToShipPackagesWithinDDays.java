package binarysearch;

public class Problem1011_CapacityToShipPackagesWithinDDays {

    public int shipWithinDays(int[] weights, int D) {
        int lo = 0, hi = 0;
        for (int w: weights) {
            lo = Math.max(lo, w);
            hi += w;
        }
        while (lo <= hi) {
            int mid = lo + (hi-lo) / 2, need = 1, cur = 0;
            for (int w: weights) {
                if (cur + w > mid) {
                    need += 1;
                    cur = 0;
                }
                cur += w;
            }
            if (need <= D) hi = mid - 1;
            else lo = mid+1;
        }
        return lo;
    }
}
