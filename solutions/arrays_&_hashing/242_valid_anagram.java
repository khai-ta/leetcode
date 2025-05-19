import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [242] - Valid Anagram
 * 
 * Description:
 * Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
 * An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
 * 
 * Example 1:
 * Input: s = "racecar", t = "carrace"
 * Output: true
 * 
 * Example 2:
 * Input: s = "jar", t = "jam"
 * Output: false
 * 
 * Constraints:
 * - s and t consist of lowercase English letters.
 * 
 * ====== APPROACHES ======
 * 1. [Sorting]
 *    - Time: O(n log n) - sorting both strings
 *    - Space: O(n) - creating new char arrays
 *    - Steps:
 *      1. convert both strings to char arrays
 *      2. sort both arrays
 *      3. compare the sorted arrays
 *      4. if they are equal, return true; otherwise false
 * 
 * 2. [Hash Map]
 *    - Time: O(n) - one pass through both strings
 *    - Space: O(1) - fixed size map for lowercase letters
 *    - Steps:
 *      1. create a frequency map for characters
 *      2. increment count for characters in s
 *      3. decrement count for characters in t
 *      4. check if all counts are zero
 * 
 * 3. [Array Counter]
 *    - Time: O(n) - one pass through both strings
 *    - Space: O(1) - fixed size array for lowercase letters
 *    - Steps:
 *      1. create a fixed-size array of 26 elements (for lowercase letters)
 *      2. increment count for characters in s
 *      3. decrement count for characters in t
 *      4. check if all counts are zero
 * 
 * ====== NOTES ======
 * Common Follow-ups
 * - Q: What if the strings contain Unicode characters?
 * - A: Use HashMap instead of fixed-size array - O(n) time, O(k) space where k is unique characters
 * 
 * - Q: What if we need to handle case sensitivity?
 * - A: Convert strings to lowercase/uppercase before processing
 * 
 * Test Cases
 * - Edge: "", ""
 * - Same string: "hello", "hello"
 * - Different lengths: "a", "ab"
 * - Unicode: "café", "éfac"
 * - Case sensitive: "Hello", "hello"
 */

class Solution {
    // Sorting Solution
    public boolean isAnagramSorting(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }

    // Hash Map Solution
    public boolean isAnagramHashMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> charCount = new HashMap<>();
        
        // Count characters in s
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Decrement counts for characters in t
        for (char c : t.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) - 1);
            if (charCount.get(c) < 0) {
                return false;
            }
        }
        
        return true;
    }

    // Optimal Solution using Array Counter
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] charCount = new int[26];
        
        // Count characters in both strings
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
            charCount[t.charAt(i) - 'a']--;
        }
        
        // Check if all counts are zero
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
} 