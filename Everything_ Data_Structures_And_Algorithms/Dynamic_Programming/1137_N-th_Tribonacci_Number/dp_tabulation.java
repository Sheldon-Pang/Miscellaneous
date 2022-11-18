/* 1137. N-th Tribonacci Number */

/*

        n = 6
        T(6 + 3) = T(6) + T(6 + 1) + T(6 + 2)

        0 1 2 3 4 5 6  7  8
        0 1 1 2 4 7 13 24 44

        Constrains: 0 <= n <= 37

        Time: O(n)
        Space: O(n)

*/

class Solution {
    public int tribonacci(int n) {

        if (n == 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        }

        int[] memo = new int[n + 1];

        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 1;

        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 3] + memo[i - 2] + memo[i - 1];
        }

        return memo[n];
    }
}