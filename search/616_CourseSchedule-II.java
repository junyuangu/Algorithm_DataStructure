/*
 * 616. Course Schedule II
Description
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example
Given n = 2, prerequisites = [[1,0]]
Return [0,1]

Given n = 4, prerequisites = [1,0],[2,0],[3,1],[3,2]]
Return [0,1,2,3] or [0,2,1,3]
 */


public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] preq) {
        // corner case
        if (numCourses < 0) {
            return new int[0];
        }
        int[] result = new int[numCourses];
        if (preq == null || preq.length == 0) {
            
            for (int i = 0; i < numCourses; ++i) {
                result[i] = i;
            }
            return result;
        }
        
        //construct graph
        Map<Integer, Integer> inDegrees = new HashMap<>();
        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        for (int i = 0; i < numCourses; ++i) {
            inDegrees.put(i, 0);
            neighbors.put(i, new ArrayList<Integer>());
        }
        
        for (int i = 0; i < preq.length; ++i) {
            inDegrees.put(preq[i][0], inDegrees.get(preq[i][0]) + 1);
            List<Integer> list = neighbors.get(preq[i][1]);
            list.add(preq[i][0]);
            neighbors.put(preq[i][1], list);
        }
        
        // 
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < numCourses; ++i) {
            if (inDegrees.get(i) != 0) {
                continue;
            }
            queue.offer(i);
            result[count] = i;
            count++;
        }
        
        while (!queue.isEmpty()) {
            int curt = queue.poll();
            List<Integer> list = neighbors.get(curt);
            if (list == null) {
                continue;
            }
            for (int i = 0; i < list.size(); ++i) {
                int next = list.get(i);
                inDegrees.put(next, inDegrees.get(next) - 1);
                if (inDegrees.get(next) > 0) {
                    continue;
                }
                queue.offer(next);
                result[count] = next;
                count++;
            }
        }
        if (count == numCourses) {
            return result;
        }
        
        return new int[0];
    }

    // 2. use array
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        
        for (int i = 0;i < numCourses; i++)
            edges[i] = new ArrayList<Integer>();
            
        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]] ++ ;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        Queue queue = new LinkedList();
        for(int i = 0; i < degree.length; i++){
            if (degree[i] == 0) {
                queue.add(i);
            }
        }
        
        int count = 0;
        int[] order = new int[numCourses];
        while(!queue.isEmpty()){
            int course = (int)queue.poll();
            order[count] = course;
            count ++;
            int n = edges[course].size();
            for(int i = n - 1; i >= 0 ; i--){
                int pointer = (int)edges[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    queue.add(pointer);
                }
            }
        }
        
        if (count == numCourses)
            return order;

        return new int[0];
    }
}