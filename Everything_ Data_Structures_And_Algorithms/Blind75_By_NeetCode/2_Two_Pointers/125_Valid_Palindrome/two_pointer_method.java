/* Two pointer method, Time: O(n), Space: O(1) */
class Solution {
    public boolean isPalindrome(String s) {

        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {

            // Skip current index when the index is not a letter and number
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            // Check for palindrome
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }

        }

        return true;
    }
}