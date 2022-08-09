/* Using two pointer method, Time: O(n^2), Space: O(n) */
/* Assume we can sort the array */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        /* Sort the array */
        Arrays.sort(nums);

        /* Used to store our result */
        List<List<Integer>> result = new ArrayList<>();

        /* Stop the loop when current index greater than zero
        Because remaining values cannot sum to zero*/
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            /* Skip when the value is same as the one before */
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumTwoPointer(nums, i, result);
            }
        }

        return result;
    }


    void twoSumTwoPointer(int[] nums, int i, List<List<Integer>> result) {
        int low = i + 1, high = nums.length - 1;
        while (low < high) {
            int sum = nums[i] + nums[low] + nums[high];
            if (sum < 0) {
                low++;
            } else if (sum > 0) {
                high--;
            } else {
                result.add(Arrays.asList(nums[i], nums[low++], nums[high--]));
                while (low < high && nums[low] == nums[low - 1]) {
                    low++;
                }
            }
        }
    }
}