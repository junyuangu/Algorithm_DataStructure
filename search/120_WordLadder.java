/*
 * 120. Word Ladder
Description
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary

Info:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
 */

public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        // corner case
        if (start.equals(end)) {
            return 1;
        }
        if ( dict == null || dict.size() == 0) {
            return 0;
        }
        dict.add(start);
        dict.add(end);
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int j = 0; j < size; ++j) {
                String curt = queue.poll();
                List<String> list = getNext(curt, dict, visited);
                for (int i = 0; i < list.size(); ++i) {
                    String next = list.get(i);
                    if (next.equals(end)) {
                        count++;
                        return count;
                    }
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
        
        return 0;
    }
    
    private List<String> getNext(String curt, Set<String> dict, Set<String> visited) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < curt.length(); ++i) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == curt.charAt(i)) {
                    continue;
                }
                String cvtStr = swap(curt, i, c);
                if (!dict.contains(cvtStr) || visited.contains(cvtStr)) {
                    continue;
                }
                list.add(cvtStr);
            }
        }
        return list;
    } 
    
    private String swap(String str, int index, char c) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); ++i) {
            if (i == index) {
                sb.append(c);
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
}