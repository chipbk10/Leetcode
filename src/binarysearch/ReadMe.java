package binarysearch;

public class ReadMe {

    /**
     * summary of 2 most frequently used binary search templates.
     * one is return index during the search:
     *
     * while lo <= hi:
     *   mid = (lo+hi)/2
     *   if nums[mid] == target:
     *     return mid
     *   if nums[mid] > target:
     *     hi = mid-1
     *   else:
     *     lo = mid+1
     * return -1
     *
     * Another more frequently used binary search template is for searching lowest element
     * satisfy function(i) == True (the array should satisfy function(x) == False for 0 to i-1,
     * and function(x) == True for i to n-1, and it is up to the question to define the function,
     * like in the find peak element problem, function(x) can be nums[x] < nums[x+1] ),
     * there are 2 ways to write it:
     *
     * while lo <= hi:
     *   mid = (lo+hi)/2
     *   if function(mid):
     *     hi = mid-1
     *   else:
     *     lo = mid+1
     * return lo
     *
     * or
     *
     * while lo <  hi:
     *   mid = (lo+hi)/2
     *   if function(mid):
     *     hi = mid
     *   else:
     *     lo = mid+1
     *
     * return lo ---> lowest element that satifies function(x)
     * return hi ---> highest element that not satifies function(x)
     *
     * No matter which one you use, just be careful about updating the hi and lo,
     * which could easily lead to infinite loop.
     * Some binary question is searching a floating number
     * and normally the question will give you a precision,
     * in which case you don't need to worry too much about the infinite loop
     * but your while condition will become something like "while lo+1e-7<hi"
     *
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
