/*

Given a rectangular matrix containing only the values “0” and “1”, where the values of “1” always appear in the form of a rectangular island and the islands are always separated row-wise and column-wise by at least one line of “0”s, count the number of islands in the given matrix. Note that the islands can diagonally adjacent.

Input: Matrix of elements with values either 0 or 1
Output: An integer which is the count of all rectangular islands
Example

Input: [[1, 1, 0, 1],
        [1, 1, 0, 1],
        [0, 0, 1, 0],
        [0, 0, 1, 0]]

Output: 3

Input: [[1, 0, 0, 0],
        [0, 0, 0, 1],
        [0, 1, 0, 1],
        [0, 0, 0, 1]]

Output: 3

Time Complexity: O(MN)
Auxiliary Space Complexity: O(1)

        [1, 1, 0, 1],
        [1, 1, 0, 1],
        [0, 0, 1, 0],
        [0, 0, 1, 0]

    // for(loop through row)
    //   for(loop through col)
    //       if (cell[row][col] == 1)
    //           call dfs
    //           number of island++;


      [0, 0, 0, 0],
      [0, 0, 0, 0],
      [0, 0, 0, 0],
      [0, 0, 0, 0]

    dfs(matrix, row, col)
      base case:
        -outside of bound, return
        -if cell[][] == 0, return
      cell[][] = 0
      recursive case:
      up, down, left, right

    findIsland(matrix)
      -loop row
        -loop col
          -check if cell is island
            -call dfs
            -increment island count = 3
      -return island count


*/
class Main {
    /* Time: O(m*n), Space: O(m*n)  */
    private static void dfs(int[][] matrix, int row, int col) {
        if (row < 0 || row >= matrix.length ||
                col < 0 || col >= matrix[0].length) {
            return;
        }
        if (matrix[row][col] == 0) {
            return;
        }
        matrix[row][col] = 0;
        dfs(matrix, row - 1, col);
        dfs(matrix, row + 1, col);
        dfs(matrix, row, col - 1);
        dfs(matrix, row, col + 1);
    }

    private static int findIsland(int[][] matrix) {
        int islandCount = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    dfs(matrix, i, j);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

// Input: [[1, 1, 0, 1],
//         [1, 1, 0, 1],
//         [0, 0, 1, 0],
//         [0, 0, 1, 0]]

// Output: 3

// Input: [[1, 0, 0, 0],
//         [0, 0, 0, 1],
//         [0, 1, 0, 1],
//         [0, 0, 0, 1]]

// Output: 3


    public static void main(String[] args) {

        int[][] matrix1 = {{1, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 0, 1, 0},
                {0, 0, 1, 0}};

        int[][] matrix2 = {{1, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 1, 0, 1},
                {0, 0, 0, 1}};

        int[][] matrix3 = {{}};

        System.out.println(findIsland(matrix1));
    }
}