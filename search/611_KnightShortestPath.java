/*
 * 611. Knight Shortest Path
Description
Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, find the shortest path to a destination position, return the length of the route.
Return -1 if knight can not reached.

Info:
source and destination must be empty.
Knight can not enter the barrier.

Clarification
If the knight is at (x, y), he can get to the following positions in one step:

(x + 1, y + 2)
(x + 1, y - 2)
(x - 1, y + 2)
(x - 1, y - 2)
(x + 2, y + 1)
(x + 2, y - 1)
(x - 2, y + 1)
(x - 2, y - 1)
Example
[[0,0,0],
 [0,0,0],
 [0,0,0]]
source = [2, 0] destination = [2, 2] return 2

[[0,1,0],
 [0,0,0],
 [0,0,0]]
source = [2, 0] destination = [2, 2] return 6

[[0,1,0],
 [0,0,1],
 [0,0,0]]
source = [2, 0] destination = [2, 2] return -1


 */

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    // 1. Bidirectional BFS
    // O(n*m) space; use Set<Point> will TLE(time limit exceeds)
    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path 
     */
    private static int[] deltaX = {1, 1, -1, -1, 2, 2, -2, -2};
    private static int[] deltaY = {2, -2, 2, -2, 1, -1, 1, -1};
    private int n, m;
    private static int startVisited = 2;
    private static int endVisited = 1;
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        
        if (source.equals(destination)) {
            return 0;
        }
        
        n = grid.length;
        m = grid[0].length;
        int[][] visited = new int[n][m];
        
        Queue<Point> startQ = new LinkedList<>();
        startQ.offer(source);
        visited[source.x][source.y] = startVisited;
        
        Queue<Point> endQ = new LinkedList<>();
        endQ.offer(destination);
        visited[destination.x][destination.y] = endVisited;
        
        int step = 0;
        while (!startQ.isEmpty() || !endQ.isEmpty()) {
            int startS = startQ.size();
            int endS = endQ.size();
            step++;
            for (int i = 0; i < startS; ++i) {
                Point curt = startQ.poll();
                for (int j = 0; j < 8; ++j) {
                    int new_x = curt.x + deltaX[j];
                    int new_y = curt.y + deltaY[j];
                    if (!isBound(grid, new_x, new_y) || visited[new_x][new_y] == startVisited) {
                        continue;
                    }
                    Point newP = new Point(new_x, new_y);
                    if (visited[new_x][new_y] == endVisited) {
                        return step;
                    }
                    startQ.offer(newP);
                    visited[new_x][new_y] = startVisited;
                }
            }
            
            step++;
            for (int i = 0; i < endS; ++i) {
                Point curt = endQ.poll();
                for (int j = 0; j < 8; ++j) {
                    int new_x = curt.x + deltaX[j];
                    int new_y = curt.y + deltaY[j];
                    if (!isBound(grid, new_x, new_y) || visited[new_x][new_y] == endVisited) {
                        continue;
                    }
                    Point newP = new Point(new_x, new_y);
                    if (visited[new_x][new_y] == startVisited) {
                        return step;
                    }
                    endQ.offer(newP);
                    visited[new_x][new_y] = endVisited;
                }
            }
        }
        return -1;
    }
    
    private boolean isBound(boolean[][] grid, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }
        return !grid[x][y];
    }
}