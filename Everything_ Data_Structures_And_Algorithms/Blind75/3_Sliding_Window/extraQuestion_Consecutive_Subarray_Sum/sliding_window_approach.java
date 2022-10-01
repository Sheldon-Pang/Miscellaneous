/*
Consecutive Subarray Sum
Given an array of positive integers and a target value, return true if there is a subarray of consecutive elements that sum up to this target value.

Input: Array of integers, target value
Output: Boolean

Example
Input: [6,12,1,7,5,2,3], 14     =>	Output: true (7+5+2)
Input: [8,3,7,9,10,1,13], 50		=>	Output: false

Time: O(N)
Space: O(1)

                 6,12,1,7,5,2,3
                        L
                             R
                 tempSum = 7+5+2, target = 14

      tempSum = 0, target, left = 0, right = 0

      while left < input length
        add right pointer index value to tempsum
        if tempSum = target
            return true
        else if tempSum greater than target
            tempsum subtract left pointer index value
            increment left pointer
        else if tempSum less than target
            incremnet right pointer
            tempSum add right pointer index value

      return false
*/

class Main {

    private static boolean consecutiveSubarraySum(int[] input, int target) {
        int tempSum = 0, left = 0, right = 0;

        while (left < input.length) {
            if (tempSum == target) {
                return true;
            } else if (tempSum < target && right < input.length - 1) {
                right++;
                tempSum += input[right];
            } else {
                tempSum -= input[left];
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        //Input: [6,12,1,7,5,2,3], 14     =>	Output: true (7+5+2)
        int[] input = {6,12,1,7,5,2,3};
        int[] input2 = {8,3,7,9,10,1,13};
        System.out.println(consecutiveSubarraySum(input, 14));

    }
}