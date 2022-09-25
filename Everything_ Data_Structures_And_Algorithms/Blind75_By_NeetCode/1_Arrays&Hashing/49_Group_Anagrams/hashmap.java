class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        if (strs.length == 0) {
            return new ArrayList();
        }

        HashMap<String, List> result = new HashMap<>();

        for (String str : strs) {
            char[] tempChar = str.toCharArray();
            Arrays.sort(tempChar);
            String key = String.valueOf(tempChar);
            if (!result.containsKey(key)) {
                result.put(key, new ArrayList());
            }
            result.get(key).add(str);
        }

        return new ArrayList(result.values());
    }
}