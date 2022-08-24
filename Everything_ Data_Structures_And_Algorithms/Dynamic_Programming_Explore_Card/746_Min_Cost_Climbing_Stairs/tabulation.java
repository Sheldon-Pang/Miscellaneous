/* 746. Min Cost Climbing Stairs */
/* Tabulation, Time: O(n), Space: O(n) */
class Solution {
    public int minCostClimbingStairs(int[] cost) {

        int minimumCost[] = new int[cost.length + 1];

        for (int i = 2; i < minimumCost.length; i++) {
            int takeOneStep = minimumCost[i - 1] + cost[i - 1];
            int takeTwoSteps = minimumCost[i - 2] + cost[i - 2];
            minimumCost[i] = Math.min(takeOneStep, takeTwoSteps);
        }

        return minimumCost[minimumCost.length - 1];
    }
}