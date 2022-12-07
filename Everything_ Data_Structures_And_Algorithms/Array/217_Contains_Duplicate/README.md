# Intuition
Use a HashSet to check for duplicate number

# Approach
1. Create a HashSet to store Integer value
2. Iterated through the input array
   1. Check if the current number is already in the map, return true if duplicate is found 
   2. Add current number to the map
3. Return false if loop ended without returning true

# Complexity
- Time complexity:
  Iteration through array takes O(n) times, n is size of the input array

- Space complexity:
  The worst case is O(n), as we store every number inside the map we created

# Code
```
class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> map = new HashSet<>();
        for (int num : nums) {
            if (map.contains(num)) {
                return true;
            }
            map.add(num);
        }
        return false;
    }
}
```