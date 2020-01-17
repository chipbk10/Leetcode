package dp.decision.making;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times)
 * with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 */
public class Problems309_BestTimeToBuyAndSellStockWithCooldown {


    public int maxProfit(int[] A) {
        return dfs(A, -3, -2, 0);
    }

    private int dfs(int[] A, int lastBuy, int lastSell, int i) {
        if (i == A.length) return 0;

        // choose to ignore
        int maxProfit = dfs(A, lastBuy, lastSell, i+1);

        boolean bought = (lastBuy > lastSell), sold = (lastSell > lastBuy), cooldown = (lastSell+1 == i);

        if (bought) {
            // choose to buy
            maxProfit = Math.max(maxProfit, A[i] + dfs(A, lastBuy, i, i+1));
        }
        else if (!cooldown) {
            // choose to sell
            maxProfit = Math.max(maxProfit, -A[i] + dfs(A, i, lastSell, i+1));
        }

        return maxProfit;
    }
}
