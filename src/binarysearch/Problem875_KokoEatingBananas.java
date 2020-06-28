package binarysearch;

public class Problem875_KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int H) {
        // min(K) : eat all piles & times <= H
        // [3,6,7,11]
        // K = 11 -> 4 hours
        // K = 8 -> 5 hours
        // K = 6 -> 1 + 1 + 2 + 2 = 6 hours
        // K = 5 -> 1 + 2 + 2 + 3 = 8 hours
        // K = 4 -> 1 + 2 + 2 + 3 = 8 hours
        // K = 3 -> 1 + 2 + 3 + 4 > 8 hours

        // lo = 1, hi = max;
        // function(mid) <= H --> hi = mid-1
        // function(mid) > H --> lo = mid+1
        // return lo

        int lo = 1, hi = 0;
        for (int pile: piles) hi = Math.max(hi, pile);

        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (timeToEatAll(piles, mid) <= H) hi = mid-1;
            else lo = mid+1;
        }
        return lo;
    }

    private int timeToEatAll(int[] piles, int speed) {
        int res = 0;
        for (int pile : piles) {
            if (speed >= pile) res += 1;
            else {
                res += pile/speed;
                if (pile%speed != 0) res++;
            }
        }
        return res;
    }
}
