/*
 * 607. Two Sum III - Data structure design
Description
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example
add(1); add(3); add(5);
find(4) // return true
find(7) // return false

 */

public class TwoSum {
    /*
     * @param number: An integer
     * @return: nothing
     */
    private List<Integer> list = new ArrayList<>();
    public void add(int number) {
        // write your code here
        list.add(number);
    }

    /*
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
        Collections.sort(list);
        int start = 0, end = list.size() - 1;
        while (start + 1 < end) {
            int lo = list.get(start);
            int hi = list.get(end);
            if (lo + hi == value) {
                return true;
            } else if (lo + hi < value) {
                start++;
            } else {
                end--;
            }
        }
        
        return list.get(start) + list.get(end) == value;
    }
}