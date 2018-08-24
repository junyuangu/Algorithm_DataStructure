/* 
 * 890. Find and Replace Pattern
You have a list of words and a pattern, and you want to know which words in words matches the pattern.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

(Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)

Return a list of the words in words that match the given pattern. 

You may return the answer in any order.

Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.
 

Note:

1 <= words.length <= 50
1 <= pattern.length = words[i].length <= 20

 */

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            if (!isMatch(words[i], pattern)) {
                continue;
            }
            result.add(words[i]);
        }
        return result;
    }
    
    private boolean isMatch(String word, String pattern) {
        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> rmap = new HashMap<>();
        Set<Character> set = new HashSet<>();
        
        for (int i = 0; i < pattern.length(); ++i) {
            char pc = pattern.charAt(i);
            char wc = word.charAt(i);
            if (!set.contains(pc)) {
                set.add(pc);
                map.put(pc, wc);
                if (rmap.containsKey(wc)) {
                    return false;
                }
                rmap.put(wc, pc);
            }
            //if set contains pc
            if (wc != map.get(pc)) {
                return false;
            }
        }
        return true;
    }
}