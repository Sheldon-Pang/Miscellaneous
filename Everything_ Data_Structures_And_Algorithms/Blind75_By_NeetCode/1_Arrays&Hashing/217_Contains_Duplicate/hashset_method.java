/* Use HashSet to store number and check for duplicate */
/* Time: O(n), Space: O(n) */
class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> map = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.contains(nums[i]))
                return true;
            map.add(nums[i]);
        }

        return false;
    }
}