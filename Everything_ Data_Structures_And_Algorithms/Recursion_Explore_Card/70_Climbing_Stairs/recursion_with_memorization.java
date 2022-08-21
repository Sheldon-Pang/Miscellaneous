/* 70. Climbing Stairs */
/* recursion with memorization, Time: O(n), Space: O(n) */
class Solution {
    public int climbStairs(int n) {

        /* Memorization */
        int memo[] = new int[n + 1];

        return helper(0, n, memo);
    }

    private int helper(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }

        memo[i] = helper(i + 1, n, memo) + helper(i + 2, n, memo);

        return memo[i];
    }
}