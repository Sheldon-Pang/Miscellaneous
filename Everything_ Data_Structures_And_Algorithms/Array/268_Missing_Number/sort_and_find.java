class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int temp = 0;
        for (int n = 0; n < nums.length; n++) {
            if (temp != nums[n]) {
                return temp;
            }
            temp++;
        }
        return temp;
    }
}