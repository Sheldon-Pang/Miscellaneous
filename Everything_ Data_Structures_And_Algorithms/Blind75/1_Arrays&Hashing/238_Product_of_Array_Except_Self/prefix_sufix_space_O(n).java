/*


Input: nums = [5,7,2,4]
Output: [56,40,140,70]

  input[5,7,2,4]
 prefix[1,5,35,70]
 suffix[56,8,4,1]

pesudo code:
    array pre[input length]
    pre[0] = 1
    -loop from index 1 to end
        -pre[i] = prev[i - 1] * input[i - 1]

    array suf[input length]
    suf[length - 1] = 1
    -loop from index (end - 2) to 0
        -suf[i] = suf[i + 1] * input[i + 1]

    array result[input length]
    -loop from index 0 to end
        -result[i] = pre[i] * suf[i]

    return result

Time complexity : O(N)
Space complexity : O(N)
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {

        int[] prefix = new int[nums.length];
        prefix[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] * nums[i-1];
        }

        int[] sufix = new int[nums.length];
        sufix[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            sufix[i] = sufix[i + 1] * nums[i + 1];
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix[i] * sufix[i];
        }

        return result;
    }
}