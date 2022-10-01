/*

Input: nums = [1,1,1,2,2,3], k = 2
                         ^
Output: [1,2]

Store in hashmap[[1,3],[2,2],[3,1]]


*/
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                int temp = map.get(num);
                temp++;
                map.put(num, temp);
            } else {
                map.put(num, 1);
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            int maxKey = maxKeyBaseOnVal(map);
            result.add(maxKey);
            //update maxKey's val to 0
            map.put(maxKey, 0);
        }

        int[] arr = result.stream().mapToInt(i -> i).toArray();

        return arr;
    }

    public static int maxKeyBaseOnVal(HashMap<Integer, Integer> map) {
        int maxValueInMap = (Collections.max(map.values()));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                return entry.getKey();
            }
        }

        return -1;
    }
}