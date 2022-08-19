/* 119. Pascal's Triangle II */
/* brute force recursion, Time: O(2^k), Space: O(k) */
class Solution {
    public List<Integer> getRow(int rowIndex) {

        List<Integer> result = new LinkedList<>();

        for (int i = 0; i <= rowIndex; i++) {
            result.add(getNum(rowIndex, i));
        }

        return result;
    }

    private int getNum(int row, int col) {
        if (row == 0 || col == 0 || row == col) {
            return 1;
        }
        return getNum(row - 1, col - 1) + getNum(row - 1, col);
    }
}