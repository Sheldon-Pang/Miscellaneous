/* 62. Unique Paths */

/*

        DP, Memorization

        0 1 1 1  1  1  1
        1 2 3 4  5  6  7
        1 3 6 10 15 21 28

    1. init 2D array, dp[m][n] = number of paths
    2. iterate over all inner cells, dp[m][n] = d[m - 1][n] + dp[m][n - 1]
    3. return dp[m][n]


*/

class Solution {
    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];

        for (int[] arr : dp) {
            Arrays.fill(arr, 1);
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                dp[row][col] = dp[row - 1][col] + dp[row][col -1];
            }
        }

        return dp[m - 1][n - 1];
    }
}