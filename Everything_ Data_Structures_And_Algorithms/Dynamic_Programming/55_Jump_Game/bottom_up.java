/*

Bottom up apporach
Time: O(n), Space: O(1)

*/

class Solution {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int curIndex = nums.length - 1; curIndex >= 0; curIndex--) {
            if (curIndex + nums[curIndex] >= lastPos) {
                lastPos = curIndex;
            }
        }
        return lastPos == 0;
    }
}