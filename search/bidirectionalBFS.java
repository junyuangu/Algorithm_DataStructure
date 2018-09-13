/*
 * 领扣 611. KnightShortestPath
 Description
Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, find the shortest path to a destination position, return the length of the route.
Return -1 if knight can not reached.

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
    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path 
     */
    final private int[] deltaX = {1, 1,-1,-1, 2, 2,-2,-2};
    final private int[] deltaY = {2,-2, 2,-2, 1,-1, 1,-1};
    final private int endVisited = 2;
    final private int startVisited = 1;
    final private int unVisited = 0;
    
    
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0] == null) {
            return -1;
        }
        if (source.equals(destination)) {
            return 0;
        }
        int[][] visitGrid = new int[grid.length][grid[0].length];
        // for (int i = 0; i < grid.length; i++) {
        //     for (int j = 0; j < grid[0].length; j++) {
        //         visitGrid[i][j] = unVisited;
        //     }
        // }
        
        Queue<Point> srcQueue = new LinkedList();
        srcQueue.offer(source);
        visitGrid[source.x][source.y] = startVisited;
        
        Queue<Point> dstQueue = new LinkedList();
        dstQueue.offer(destination);
        visitGrid[destination.x][destination.y] = endVisited;
        
        int step = 0;
        while (!srcQueue.isEmpty() || !dstQueue.isEmpty()) {
            step++;
            int srcSize = srcQueue.size();
            int dstSize = dstQueue.size();
            for (int i = 0; i < srcSize; i++) {
                Point curt = srcQueue.poll();
                for (int j = 0; j < 8; j++) {
                    Point next = new Point( 
                        curt.x + deltaX[j],
                        curt.y + deltaY[j]
                        );
                    if (inBound(grid, next)) {
                        if (visitGrid[next.x][next.y] == startVisited) {
                            continue;
                        } else if (visitGrid[next.x][next.y] == endVisited) {
                            return step;
                        } else if (visitGrid[next.x][next.y] == unVisited) {
                            if (grid[next.x][next.y] == false) {
                                srcQueue.offer(next);
                                grid[next.x][next.y] = true;
                                visitGrid[next.x][next.y] = startVisited;
                            }
                        }
                    }
                }
            }
            step++;
            for (int i = 0; i < dstSize; i++) {
                Point curt = dstQueue.poll();
                for (int j = 0; j < 8; j++) {
                    Point next = new Point( 
                        curt.x + deltaX[j],
                        curt.y + deltaY[j]
                        );
                    if (inBound(grid, next)) {
                        if (visitGrid[next.x][next.y] == endVisited) {
                            continue;
                        } else if (visitGrid[next.x][next.y] == startVisited) {
                            return step;
                        } else if (visitGrid[next.x][next.y] == unVisited){
                            if (grid[next.x][next.y] == false) {
                                dstQueue.offer(next);
                                grid[next.x][next.y] = true;
                                visitGrid[next.x][next.y] = endVisited;
                            }
                        }
                    }
                }
            }
        }
        
        return -1;
    }
    
    private boolean inBound(boolean[][] grid, Point curt) {
        int n = grid.length, m = grid[0].length;
        int x = curt.x, y = curt.y;
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }
        
        return true;
    }
}