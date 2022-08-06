class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        if (strs.length == 0)
            return new ArrayList();

        HashMap<String, List> answer = new HashMap<>();

        for (String str: strs) {
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);

            if (!answer.containsKey(key)) {
                answer.put(key, new ArrayList());
            }

            answer.get(key).add(str);
        }

        return new ArrayList(answer.values());
    }
}