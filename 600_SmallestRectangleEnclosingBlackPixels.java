/* 
 * 600. Smallest Rectangle Enclosing Black Pixels
Description
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

Example
For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.

 */
// 2. Binary Search - O(nlogm + mlogn) time, where n = rows, m = cols.
public class Solution {
    /**
     * @param image: a binary matrix with '0' and '1'
     * @param x: the location of one of the black pixels
     * @param y: the location of one of the black pixels
     * @return: an integer
     * 2. Binary search the upper bound, lower bound, left bound and right bound;
     * Then compute the area = (right - left + 1) * (upper - lower + 1).
     */
    // private final static boolean horizon = true;
    // private final static boolean vertical = false;
    // private final static boolean positive = true;
    // private final static boolean negative = false;
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image.length == 0) {
            return 0;
        }
        int row = image.length;
        int col = image[0].length;
        
        int top = findTop(image, 0, x);
        int btm = findBottom(image, x, row - 1);
        int left = findLeft(image, 0, y);
        int right = findRight(image, y, col - 1);
        
        return (right - left + 1) * (btm - top + 1);
    }
    
    private boolean isRowEmpty(char[][] image, int rowIndex) {
        for (int i = 0; i < image[0].length; ++i) {
            if (image[rowIndex][i] == '1') {
                return false;
            }
        }        
        return true;
    } 
    
    private int findTop(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isRowEmpty(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (!isRowEmpty(image, start)) {
            return start;
        }
        return end;
    }
    
    private int findBottom(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isRowEmpty(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (!isRowEmpty(image, end)) {
            return end;
        }
        return start;
    }
    
    private boolean isColumnEmpty(char[][] image, int colIndex) {
        for (int i = 0; i < image.length; ++i) {
            if (image[i][colIndex] == '1') {
                return false;
            }
        }        
        return true;
    } 
    
    private int findLeft(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isColumnEmpty(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (!isColumnEmpty(image, start)) {
            return start;
        }
        return end;
    }
    
    private int findRight(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isColumnEmpty(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (!isColumnEmpty(image, end)) {
            return end;
        }
        return start;
    }
    
}



// 1. BFS. 
class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    /**
     * @param image: a binary matrix with '0' and '1'
     * @param x: the location of one of the black pixels
     * @param y: the location of one of the black pixels
     * @return: an integer
     */
    private static int[] deltaX = {0, -1, 1, 0};
    private static int[] deltaY = {-1, 0, 0, 1};
    public int minArea(char[][] image, int x, int y) {
        // write your code here
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        
        int xMin = Integer.MAX_VALUE, yMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE, yMax = Integer.MIN_VALUE;
        int row = image.length;
        int col = image[0].length;
        boolean[][] visited = new boolean[row][col];
        
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        visited[x][y] = true;
        while(!queue.isEmpty()) {
            Point curt = queue.poll();
            xMin = Math.min(xMin, curt.x);
            xMax = Math.max(xMax, curt.x);
            yMin = Math.min(yMin, curt.y);
            yMax = Math.max(yMax, curt.y);
            for (int i = 0; i < 4; ++i) {
                int pos_x = curt.x + deltaX[i];
                if (pos_x < 0 || pos_x >= row) {
                    continue;
                }
                for (int j = 0; j < 4; ++j) {
                    int pos_y = curt.y + deltaY[j];
                    if (pos_y < 0 || pos_y >= col || visited[pos_x][pos_y] == true
                            ||image[pos_x][pos_y] == '0') {
                        continue;
                    }
                    visited[pos_x][pos_y] = true;
                    queue.offer(new Point(pos_x, pos_y));
                }
            }
        }
        long ans = (xMax - xMin + 1) * (yMax - yMin + 1);
        return (int)ans;
    }
}