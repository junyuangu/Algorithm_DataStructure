/*
 * 433. Number of Islands
Description
Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Find the number of islands.

Example
Given graph:

[
  [1, 1, 0, 0, 0],
  [0, 1, 0, 0, 1],
  [0, 0, 0, 1, 1],
  [0, 0, 0, 0, 0],
  [0, 0, 0, 0, 1]
]
return 3.

 */

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    private int n, m;
    private int[] deltaX = new int[] {0, 1, -1, 0};
    private int[] deltaY = new int[] {-1, 0, 0, 1};
    public int numIslands(boolean[][] grid) {
        // write your code here
        int ans = 0;
        if (grid == null || grid.length == 0) {
            return ans;
        }
        n = grid.length;
        m = grid[0].length;
        
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j]) {
                    ans++;
                    bfs(grid, i, j);
                }
            }
        }
        return ans;
    }
    
    private void bfs(boolean[][] grid, int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        
        while(!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int new_x = p.x + deltaX[i];
                int new_y = p.y + deltaY[i];
                if (!isBound(new_x, new_y)) {
                    continue;
                }
                if (!grid[new_x][new_y]) {
                    continue;
                }
                grid[new_x][new_y] = false;
                queue.offer(new Point(new_x, new_y));
            }
        }
    }
    
    private boolean isBound(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }
        return true;
    }
}