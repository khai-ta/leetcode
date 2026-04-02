import java.util.*;

/**
 * [49] - Group Anagrams
 * 
 * Description:
 * Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.
 * An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
 * 
 * Example 1:
 * Input: strs = ["act","pots","tops","cat","stop","hat"]
 * Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
 * 
 * Example 2:
 * Input: strs = ["x"]
 * Output: [["x"]]
 * 
 * Example 3:
 * Input: strs = [""]
 * Output: [[""]]
 * 
 * Constraints:
 * - 1 <= strs.length <= 1000
 * - 0 <= strs[i].length <= 100
 * - strs[i] is made up of lowercase English letters
 * 
 * ====== APPROACHES ======
 * 1. [Sorting]
 *    - Time: O(m * n log n) - where m is number of strings, n is length of longest string
 *    - Space: O(m * n) - to store all strings
 *    - Steps:
 *      1. Sort each string to get its canonical form
 *      2. Use sorted string as key in hash map
 *      3. Group original strings by their sorted forms
 * 
 * 2. [Hash Table]
 *    - Time: O(m * n) - where m is number of strings, n is length of longest string
 *    - Space: O(m) extra space + O(m * n) for output
 *    - Steps:
 *      1. Count characters in each string using fixed-size array
 *      2. Convert count array to string key
 *      3. Group strings by their character count keys
 * 
 * ====== NOTES ======
 * Common Follow-ups
 * - Q: What if strings contain Unicode characters?
 * - A: Use HashMap for character counting instead of fixed array
 * 
 * - Q: What if we need to handle case sensitivity?
 * - A: Convert strings to lowercase/uppercase before processing
 * 
 * - Q: What if memory is a constraint?
 * - A: Use character counting approach as it's more space efficient
 * 
 * Test Cases
 * - Empty array: []
 * - Single empty string: [""]
 * - Single character: ["a"]
 * - All anagrams: ["eat","tea","ate"]
 * - No anagrams: ["abc","def","ghi"]
 * - Mixed case: ["Eat","tea","Ate"]
 */

class Solution {
    // Sorting Solution
    public List<List<String>> groupAnagramsSorting(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedS = new String(charArray);
            res.putIfAbsent(sortedS, new ArrayList<>());
            res.get(sortedS).add(s);
        }
        return new ArrayList<>(res.values());
    }

    // Hash Table Solution (Optimal)
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            String key = Arrays.toString(count);
            res.putIfAbsent(key, new ArrayList<>());
            res.get(key).add(s);
        }
        return new ArrayList<>(res.values());
    }
} 