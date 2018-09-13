/*
 * 615. Course Schedule
Description
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example
Given n = 2, prerequisites = [[1,0]]
Return true

Given n = 2, prerequisites = [[1,0],[0,1]]
Return false
 */

public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    // BFS 
    public boolean canFinish(int numCourses, int[][] preq) {
        // corner cases
        if (preq == null || preq.length == 0 || preq[0].length == 0 || numCourses == 0) {
            return true;
        }
        
        int n = preq.length;
        // model courses to directed graph.
        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        for (int i = 0; i < numCourses; ++i) {
            neighbors.put(i, new ArrayList<Integer>());
        }
        
        Map<Integer, Integer> inDegrees = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            if (neighbors.containsKey(preq[i][1])) {
                List<Integer> list = neighbors.get(preq[i][1]);
                list.add(preq[i][0]);
                neighbors.put(preq[i][1], list);
            } else {
                List<Integer> list = new ArrayList();
                list.add(preq[i][0]);
                neighbors.put(preq[i][1], list);
            }
            if (!inDegrees.containsKey(preq[i][0])) {
                inDegrees.put(preq[i][0], 1);
            } else {
                inDegrees.put(preq[i][0], inDegrees.get(preq[i][0]) + 1);
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        // find those inDegree == 0 and add them to queue and set;
        for (int i = 0; i < numCourses; ++i) {
            if (!inDegrees.containsKey(i)) {
                queue.offer(i);
                visited.add(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int curt = queue.poll();
            if (neighbors.get(curt) == null) {
                continue;
            }
            for (Integer next : neighbors.get(curt)) {
                inDegrees.put(next, inDegrees.get(next) - 1);
                if (inDegrees.get(next) == 0) {
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
        return visited.size() == numCourses;
    }
}