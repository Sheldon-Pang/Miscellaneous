class Solution {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();

        if (s.length() != t.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                int tempCount = map.get(s.charAt(i));
                tempCount++;
                map.put(s.charAt(i), tempCount);
            } else {
                map.put(s.charAt(i), 1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))) {
                return false;
            }
            if (map.get(t.charAt(i)) > 0) {
                int tempCount = map.get(t.charAt(i));
                tempCount--;
                map.put(t.charAt(i), tempCount);
            } else {
                return false;
            }
        }

        return true;
    }
}