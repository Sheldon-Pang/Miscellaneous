/* Time: O(n), Space: O(n) */
/* Using HashMap is not ideal when the input area is sorted
* Use Two Pointer method instead */
class Solution {
    public int[] twoSum(int[] numbers, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                return new int[] {map.get(target - numbers[i]) + 1, i + 1};
            }
            map.put(numbers[i], i);
        }

        return null;
    }
}