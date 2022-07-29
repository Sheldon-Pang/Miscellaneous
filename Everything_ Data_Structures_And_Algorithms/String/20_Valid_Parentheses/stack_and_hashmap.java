/* using stack, closing bracket will match opening bracket on top of stack, if not then not valid */
/* Time: O(n), Space: O(n) */
class Solution {
    public boolean isValid(String s) {

        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) { /* if c is closing bracket */
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != map.get(c))
                    return false;
            } else {
                stack.push(c);
            }
        }

        /* only valid when stack is empty */
        return stack.isEmpty();
    }
}