/* 70. Climbing Stairs */
/* Brute force recursion, Time: O(2^n), Space: O(n) */
class Solution {
    public int climbStairs(int n) {
        return helper(0, n);
    }

    private int helper(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return helper(i + 1, n) + helper(i + 2, n);
    }
}