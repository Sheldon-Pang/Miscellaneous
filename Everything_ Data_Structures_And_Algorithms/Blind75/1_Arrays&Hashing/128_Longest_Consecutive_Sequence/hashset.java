/*

Input: nums = [100,4,200,1,3,2]
Output: 4

nums = [100,4,200,1,3,2]
                  ^
HashSet[100,4,200,1,3,2]
maxStreak = 4
curNum = 1 + 1 + 1 + 1
tempStreak = 1 + 1 + 1 + 1


Time: O(n)
Space: O(n)

*/
class Solution {
    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int maxStreak = 0;

        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int curNum = num;
                int tempStreak = 1;
                while (set.contains(curNum + 1)) {
                    curNum++;
                    tempStreak++;
                }
                maxStreak = Math.max(tempStreak, maxStreak);
            }
        }

        return maxStreak;
    }
}