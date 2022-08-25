/* 70. Climbing Stairs */
/* recursion with memorization using HashMap, Time: O(n), Space: O(n) */
class Solution {

    private HashMap<Integer, Integer> memo = new HashMap<>();

    private int dp(int i) {

        /* Base case */
        if (i <= 2) {
            return i;
        }

        /* Memorization */
        if (!memo.containsKey(i)) {
            memo.put(i, dp(i - 1) + dp(i - 2));
        }

        return memo.get(i);
    }

    public int climbStairs(int n) {
        return dp(n);
    }
}