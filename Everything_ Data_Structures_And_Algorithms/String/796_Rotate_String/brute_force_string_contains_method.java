/* Brute Force, String.contains() */
/* Time: O(n^2), Space: O(n) */
class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length())
            return false;

        return (s + s).contains(goal);
    }
}