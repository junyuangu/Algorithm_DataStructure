/*
 * 814. Shortest Path in Undirected Graph
Description
Give an undirected graph, in which each edge's length is 1, and give two nodes from the graph. 
We need to find the length of the shortest path between the given two nodes.

Example
Given graph = {1,2,4#2,1,4#3,5#4,1,2#5,3}, and nodeA = 3, nodeB = 5.

1------2  3
 \     |  | 
  \    |  |
   \   |  |
    \  |  |
      4   5
return 1.
*/

/**
 * Definition for graph node.
 * class GraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { 
 *         label = x; neighbors = new ArrayList<UndirectedGraphNode>(); 
 *     }
 * };
 */
public class Solution {
    /**
     * @param graph: a list of Undirected graph node
     * @param A: nodeA
     * @param B: nodeB
     * @return:  the length of the shortest path
     */
    // 1. 双向BFS
    public int shortestPath(List<UndirectedGraphNode> graph, UndirectedGraphNode A, UndirectedGraphNode B) {
        // Write your code here
        Queue<UndirectedGraphNode> queueA = new LinkedList<>();
        Set<UndirectedGraphNode> visitedA = new HashSet<>();
        queueA.offer(A);
        visitedA.add(A);
        
        Queue<UndirectedGraphNode> queueB = new LinkedList<>();
        Set<UndirectedGraphNode> visitedB = new HashSet<>();
        queueB.offer(B);
        visitedB.add(B);
        
        int ans = 0;
        while (!queueA.isEmpty() || !queueB.isEmpty()) {
            int aSize = queueA.size();
            int bSize = queueB.size();
            ans++;
            for (int i = 0; i < aSize; i++) {
                UndirectedGraphNode curt = queueA.poll();
                for (UndirectedGraphNode nb : curt.neighbors) {
                    if (visitedA.contains(nb)) {
                        continue;
                    }
                    if (visitedB.contains(nb)) {
                        return ans;
                    }
                    queueA.offer(nb);
                    visitedA.add(nb);
                }
            }
            ans++;
            for (int i = 0; i < bSize; i++) {
                UndirectedGraphNode curt = queueB.poll();
                for (UndirectedGraphNode nb : curt.neighbors) {
                    if (visitedB.contains(nb)) {
                        continue;
                    }
                    if (visitedA.contains(nb)) {
                        return ans;
                    }
                    queueB.offer(nb);
                    visitedB.add(nb);
                }
            }
        }
        
        return -1;
    }
}