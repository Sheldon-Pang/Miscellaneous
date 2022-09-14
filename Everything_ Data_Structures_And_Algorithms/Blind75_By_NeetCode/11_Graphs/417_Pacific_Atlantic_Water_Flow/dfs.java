/*

                     Pacific

                    [1,2,2,3,5]
                    [3,2,3,4,4]
           Pacific  [2,4,5,3,1]  Atlantic
                    [6,7,1,4,5]
                    [5,1,1,2,4]

                      Atlantic

    Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]

    intuitive idea: use recursive BFS to travesal from every single cell.
    if a cell can travel to both top/left and bottom/right, save to result

    optimized:
    what if we start from cell border the ocean,
    any overlaping cell would be our result

    recursive DFS pesudo code:
    -dfs(matrix[][], int row, int col, int prevCell, result[][])
        -base case:
         1. if out off boundary, return
         2. if current height < prevCell height, return
         3. if result[][] already visited, return
        -make corresponding cell reuslt[row][col] true
        -recursively call
        1. up
        2. down
        3. left
        4. right

    PacificAtlantic resudo code:
    -public List<List<Integer>> pacificAtlantic(int[][] heights)
        two boolean[][] to store result for Pacific and Atlantic
        -for loop for Pacific
            call dfs
        -for loop for Atlantic
            call dfs
        -for loop to check result in both Pacific and Atlantic
        return result

*/

class Solution {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int row = heights.length;
        int col = heights[0].length;

        List<List<Integer>> result = new ArrayList<>();

        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            // left side facing pacific
            dfs(heights, i, 0, -1, pacific);
            // right side facing atlantic
            dfs(heights, i , col - 1, -1, atlantic);
        }

        for (int i = 0; i < col; i++) {
            // top side facing pacific
            dfs(heights, 0, i, -1, pacific);
            // bottom side facing atlantic
            dfs(heights, row - 1, i, -1, atlantic);
        }

        // compare results
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(
            int[][] heights,
            int row,
            int col,
            int prevCell,
            boolean[][] result) {
        /*
        -base case:
         1. if out off boundary, return
         2. if current height < prevCell height, return
         3. if result[][] already visited, return
        */
        if (row < 0 ||
                row >= result.length ||
                col < 0 ||
                col >= result[0].length) {
            return;
        }
        if (heights[row][col] < prevCell || result[row][col]) {
            return;
        }

        result[row][col] = true;

        dfs(heights, row - 1, col, heights[row][col], result);
        dfs(heights, row + 1, col, heights[row][col], result);
        dfs(heights, row, col - 1, heights[row][col], result);
        dfs(heights, row, col + 1, heights[row][col], result);
    }
}
