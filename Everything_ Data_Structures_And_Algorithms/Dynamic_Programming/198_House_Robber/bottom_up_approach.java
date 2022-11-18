/* 198. House Robber */
/* Bottom up approach, using array to memorize */
/* Time: O(n), Space: O(n) */
class Solution {
    public int rob(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] memo = new int[nums.length];

        // base case
        memo[0] = nums[0];
        memo[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + nums[i]);
        }

        return memo[nums.length - 1];
    }
}