
class Solution {

    public char[][] letterGrid;
    public int rows;
    public int cols;

    public boolean exist(char[][] board, String word) {

        letterGrid = board;
        rows = board.length;
        cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (backtrack(row, col, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean backtrack(int row, int col, String word, int index) {

        /* check the bottom case */
        if (index >= word.length())
            return true;

        /* Check the boundaries */
        if (row < 0 || row == rows || col < 0 || col == cols || letterGrid[row][col] != word.charAt(index))
            return false;

        /* explore the neighbors in DFS */
        boolean result = false;
        letterGrid[row][col] = '#'; // mark the path before the next exploration

        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        for (int d = 0; d < 4; ++d) {
            result = backtrack(row + rowOffsets[d], col + colOffsets[d], word, index + 1);
            if (result) {
                break;
            }
        }

        /* clean up and return the result */
        letterGrid[row][col] = word.charAt(index);
        return result;
    }
}
