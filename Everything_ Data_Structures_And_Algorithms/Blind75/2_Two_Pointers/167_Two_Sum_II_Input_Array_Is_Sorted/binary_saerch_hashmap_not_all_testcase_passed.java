/*


numbers = [1,2,5,7,11,15,16,17], target = 9
           L M   R


left = 0, right = 3, mid = 1, complement = 9 - 2 = 7
map[7,]

if Mid point > target
    set Right to Mid point

     [2,3,4], target = 6, output = [1,3]
      L R
      M
left = 0, right = 1, mid = 0, complement = 6 - 2 = 4
map[2,4,3]


[-3,3,4,90], 0, output = [1,2]
 L  M    R

left = 0, right = 3, mid = 1, complement = 0 - 3 = -3
map[-3,90,]

[3,24,50,79,88,150,345], 200

*/

class Solution {
    public int[] twoSum(int[] numbers, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0, right = numbers.length - 1;
        int mid = (left + right) / 2;

        while (left < right) {

            if (!map.containsKey(numbers[left])) {
                map.put(numbers[left], left);
            }
            if (!map.containsKey(numbers[right])) {
                map.put(numbers[right], right);
            }

            int complement = target - numbers[mid];

            if (map.containsKey(complement)) {
                if (mid + 1 > map.get(complement) + 1) {
                    return new int[] {map.get(complement) + 1, mid + 1};
                } else {
                    return new int[] {mid + 1, map.get(complement) + 1};
                }
            }

            map.put(numbers[mid], mid);

            if (complement <= numbers[mid]) {
                right = mid;
                mid = (left + right) / 2;
            } else {
                if (left == mid) {
                    left++;
                    mid = (left + right) / 2;
                } else {
                    left = mid;
                    mid = (left + right) / 2;
                }
            }

        }

        return new int[]{-1, -1};
    }
}