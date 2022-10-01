/* Binary search with one pass, Time: O(log(n)), Space: O(1) */
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int pivot = left + (right - left) / 2;

            if (nums[pivot] == target) {
                return pivot;
            }

            if (nums[pivot] >= nums[left]) {

                if (target >= nums[left] && target < nums[pivot]) {
                    right = pivot - 1;
                } else {
                    left = pivot + 1;
                }

            } else {

                if (target <= nums[right] && target > nums[pivot]) {
                    left = pivot + 1;
                } else {
                    right = pivot - 1;
                }

            }
        }

        return -1;
    }
}