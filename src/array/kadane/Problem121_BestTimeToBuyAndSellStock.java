package array.kadane;

public class Problem121_BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        int min = prices[0], maxSoFar = 0;
        for (int i = 1; i < n; i++) {
            min = Math.min(min, prices[i]);
            maxSoFar = Math.max(maxSoFar, prices[i] - min);
        }

        return maxSoFar;
    }
}
