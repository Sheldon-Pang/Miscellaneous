class Solution {
    public boolean isValid(String s) {

        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (map.containsKey(curChar)) { //if contain closing bracket
                char topElement;
                if (stack.isEmpty()) {
                    topElement = '#';
                } else {
                    topElement = stack.pollLast();
                }
                if (topElement != map.get(curChar)) {
                    return false;
                }
            } else { // push open bracket to stack
                stack.add(curChar);
            }
        }

        return stack.isEmpty();
    }
}