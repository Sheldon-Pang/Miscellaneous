/*

Input: nums = [100,4,200,1,3,2]
Output: 4

brute force, Time Limit Exceeded

[100,4,200,1,3,2]
           ^

maxStreak = 4
tempStreak = 4

curNum = 1 + 1 + 1 + 1

Time: O(n^3)
Space: O(1)


*/
class Solution {
    public int longestConsecutive(int[] nums) {

        int maxStreak = 0;

        for (int num : nums) {
            int curNum = num;
            int tempStreak = 1;
            while (arrayContains(nums, curNum + 1)) {
                curNum++;
                tempStreak++;
            }
            maxStreak = Math.max(tempStreak, maxStreak);
        }

        return maxStreak;
    }

    private boolean arrayContains(int[] arr, int num) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }

        return false;
    }
}