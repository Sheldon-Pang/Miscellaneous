class Solution {
    public String decodeString(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == ']') {
                List<Character> decodedString = new ArrayList<>();

                /* Get encoded string from stack */
                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }
                stack.pop(); /* Pop '[' off the stack */

                int base = 1;
                int k = 0;
                /* Get number k */
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }

                /* Push decodedString k times into stack */
                while (k != 0) {
                    for (int j = decodedString.size() - 1; j >= 0; j--) {
                        stack.push(decodedString.get(j));
                    }
                    k--;
                }
            } else {
                /* Push current character to stack */
                stack.push(s.charAt(i));
            }
        }

        /* Get result from stack */
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return new String(result);
    }
}