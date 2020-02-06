package dp.distinct.way;

public class Problem673_NumberOfLongestIncreasingSubsequence {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, max = 0;

        // the longest increasing subsequence that ends at i
        int[] len = new int[n];

        // the number of the longest increasing subsequence that ends at i
        int[] cnt = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = i-1; j >= 0; j--) {

                // if combining with i makes an increasing subsequence
                if (nums[j] < nums[i]) {

                    // if combining with i makes a longer increasing subsequence
                    if (len[j]+1 > len[i]) {
                        len[i] = len[j]+1;
                        cnt[i] = cnt[j];
                    }
                    // if combining with i makes another longest increasing subsequence
                    else if (len[j]+1 == len[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }

            // if the current nums[i] is the minimum value so far
            if (len[i] == 0) {
                len[i] = 1;
                cnt[i] = 1;
            }

            // update the longest length
            max = Math.max(max, len[i]);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (len[i] == max) {
                res += cnt[i];
            }
        }
        return res;
    }

}
