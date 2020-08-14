package binarysearch;

public class ReadMe {

    /*
        |--x-----x------[v]vvvvvvvvvvvvvvvv|
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if function(mid)
                hi = mid-1;
            else
                lo = mid+1;
        }
        return lo; ---> lowest element that satisfies function(x)

        |vvvvvvvvvvvvv[v]-----x------x-------|
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if function(mid)
                lo = mid+1;
            else
                hi = mid-1;
        }
        return hi; ---> highest element that satisfies function(x)

        |----x----[v]----------x--------------|
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if function(mid) == target
                return mid;
            if function(mid) < target
                lo = mid+1;
            else
                hi = mid-1;
        }
        return -1;

        Note: function(x) is non-decreasing function
     */

     /*
     *
     * Collections.binarySearch
     * Array.binarySearch
     * for binarySearch, the result can be negative. If key is not present, then it returns "(-(insertion point) - 1)".
     * The insertion point is defined as the point at which the key would be inserted into the list.
     *
     * To get the position where the value is the same or closet from the left to the key, we use:
     *
     * int i = Collections.binarySearch(A, key);
     * if (i < 0) i = (-i - 1) - 1;
     *
     *
     */
}
