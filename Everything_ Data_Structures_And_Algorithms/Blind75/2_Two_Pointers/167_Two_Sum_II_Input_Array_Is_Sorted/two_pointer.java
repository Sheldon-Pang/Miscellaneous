class Solution {
    public int[] twoSum(int[] numbers, int target) {

        int left = 0, right = numbers.length - 1;

        while (left < right) {

            int curSum = numbers[left] + numbers[right];

            if (curSum == target) {
                return new int[] {left + 1, right + 1};
            } else if (curSum > target) {
                right--;
            } else {
                left++;
            }

        }

        return new int[] {-1, -1};
    }
}