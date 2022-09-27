/*

Input: nums = [100,4,200,1,3,2]
Output: 4

sort the array[1,2,3,4,100,200]

loop through the array to check current with prev number

Time: O(nlog(n))
Space: O(1)

*/
class Solution {
    public int longestConsecutive(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int maxStreak = 1;
        int tempStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    tempStreak++;
                } else {
                    maxStreak = Math.max(maxStreak, tempStreak);
                    tempStreak = 1;
                }
            }
        }

        return Math.max(maxStreak, tempStreak);
    }
}