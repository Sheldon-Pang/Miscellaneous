/* Time: O(nlogn), Space: O(n) */
class Solution {
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length())
            return false;

        char[] strS = s.toCharArray();
        char[] strT = t.toCharArray();

        Arrays.sort(strS);
        Arrays.sort(strT);

        return Arrays.equals(strS, strT);
    }
}