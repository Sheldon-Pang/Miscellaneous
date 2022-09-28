/* 70. Climbing Stairs */
/* tabulation with array, Time: O(n), Space: O(n) */
class Solution {
    public int climbStairs(int n) {

        /* special case */
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];

        /* base case */
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}


/*

Input: n = 3
Output: 3

n = 1, 1
n = 2, 2
n = 3, 3
n = 4, 5

tabulation apporach
n = (n - 1) + (n - 2)

Time: O(N)
Space: O(N)

*/

class Solution {
    public int climbStairs(int n) {

        int[] tabulation = new int[n + 1];

        tabulation[0] = 1;
        tabulation[1] = 2;

        for (int i = 2; i < n; i++) {
            tabulation[i] = tabulation[i - 1] + tabulation[i - 2];
        }

        return tabulation[n - 1];
    }
}