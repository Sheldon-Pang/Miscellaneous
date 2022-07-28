/* iterate and find min, Time: O(n), Space: O(1) */
class Solution {
    public int maximumDifference(int[] nums) {

        int maxDiff = 0;
        int min = nums[0];

        for (int num : nums) {
            maxDiff = Math.max((num - min), maxDiff);
            if (num < min)
                min = num;
        }

        if (maxDiff == 0)
            return -1;

        return maxDiff;
    }
}