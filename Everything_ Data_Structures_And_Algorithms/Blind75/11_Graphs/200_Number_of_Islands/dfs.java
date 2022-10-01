/*
Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

Time:   O(m*n)
Space:  O(m*n)

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'



        Input: grid =
  ["-1","-1","-1","-1","0"],
  ["-1","-1","0","-1","0"],
  ["-1","-1","0","0","0"],
  ["0","0","0","0","0"]

  Output: 1

        each element four direction to go
          [-1][]
          [+1][]
          [][-1]
          [][+1]

      recursive helper(grid, i , j):
        base case: row < 0, row > grid.length, col < 0, col > grid[0].length , [row][col] = 0
        recursive call:
            1. dfs(go up)
            2. dfs(go down)
            3. dfs(go right)
            4. dfs(go left)

      main:
        for loop row
            for loop col
                if ([row][col] != -1 && [row][col] == 1)
                    call recursive helper
                    number of island ++

        return number of island 3

      ["-1","-1","0","0","0"],
      ["-1","-1","0","0","0"],
      ["0","0","-1","0","0"],
      ["0","0","0","-1","-1"]

base case: row < 0, row > grid.length, col < 0, col > grid[0].length , [row][col] = 0
        recursive call:
            1. dfs(go up)
            2. dfs(go down)
            3. dfs(go right)
            4. dfs(go left)
*/
/* Time: O(m*n), Space: O(m*n) worst case */
class Solution {
    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numOfIsland = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    numOfIsland++;
                    dfs(grid, i ,j);
                }
            }
        }

        return numOfIsland;
    }

    private static void dfs(char[][] grid, int i, int j) {

        if (i < 0 || i >= grid.length ||
                j < 0 || j >= grid[0].length ||
                grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}