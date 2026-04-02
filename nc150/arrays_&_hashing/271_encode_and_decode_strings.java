import java.util.*;

/**
 * [271] - Encode and Decode Strings
 * 
 * Description:
 * Design an algorithm to encode a list of strings to a single string.
 * The encoded string is then decoded back to the original list of strings.
 * 
 * Example 1:
 * Input: ["neet","code","love","you"]
 * Output: ["neet","code","love","you"]
 * 
 * Example 2:
 * Input: ["we","say",":","yes"]
 * Output: ["we","say",":","yes"]
 * 
 * Constraints:
 * - 0 <= strs.length < 100
 * - 0 <= strs[i].length < 200
 * - strs[i] contains only UTF-8 characters
 * 
 * ====== APPROACHES ======
 * 1. [Two-Part Encoding]
 *    - Time: O(m) for both encode and decode
 *    - Space: O(m + n) for both encode and decode
 *    - Steps:
 *      1. First part: Store all lengths separated by commas
 *      2. Second part: Store all strings concatenated
 *      3. Use '#' as separator between parts
 * 
 * 2. [Length + Delimiter (Optimal)]
 *    - Time: O(m) for both encode and decode
 *    - Space: O(m + n) for both encode and decode
 *    - Steps:
 *      1. For each string, store its length + '#' + string
 *      2. Parse length and extract string during decoding
 * 
 * ====== NOTES ======
 * Common Follow-ups
 * - Q: What if strings contain the delimiter?
 * - A: Both approaches handle delimiter conflicts correctly
 * 
 * - Q: What if strings are very long?
 * - A: Both approaches work for any string length
 * 
 * - Q: What if we need to handle empty strings?
 * - A: Both approaches handle empty strings correctly
 * 
 * Test Cases
 * - Empty list: []
 * - List with empty strings: ["", ""]
 * - List with special characters: ["we","say",":","yes"]
 * - List with long strings: ["longstring", "anotherlongstring"]
 */

class Solution {
    // Approach 1: Two-Part Encoding
    public String encodeTwoPart(List<String> strs) {
        if (strs.isEmpty()) return "";
        StringBuilder res = new StringBuilder();
        List<Integer> sizes = new ArrayList<>();
        
        // First part: collect all lengths
        for (String str : strs) {
            sizes.add(str.length());
        }
        
        // Add lengths separated by commas
        for (int size : sizes) {
            res.append(size).append(',');
        }
        
        // Add separator
        res.append('#');
        
        // Second part: add all strings
        for (String str : strs) {
            res.append(str);
        }
        
        return res.toString();
    }

    public List<String> decodeTwoPart(String str) {
        if (str.length() == 0) {
            return new ArrayList<>();
        }
        
        List<String> res = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();
        int i = 0;
        
        // Parse lengths
        while (str.charAt(i) != '#') {
            StringBuilder cur = new StringBuilder();
            while (str.charAt(i) != ',') {
                cur.append(str.charAt(i));
                i++;
            }
            sizes.add(Integer.parseInt(cur.toString()));
            i++;
        }
        
        // Skip the '#'
        i++;
        
        // Extract strings using the lengths
        for (int sz : sizes) {
            res.add(str.substring(i, i + sz));
            i += sz;
        }
        
        return res;
    }

    // Approach 2: Length + Delimiter (Optimal)
    public String encode(List<String> strs) {
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s.length()).append('#').append(s);
        }
        return res.toString();
    }

    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int i = 0;
        
        while (i < str.length()) {
            int j = i;
            // Find the delimiter
            while (str.charAt(j) != '#') {
                j++;
            }
            // Get the length
            int length = Integer.parseInt(str.substring(i, j));
            // Move to the start of the string
            i = j + 1;
            // Extract the string
            j = i + length;
            res.add(str.substring(i, j));
            i = j;
        }
        
        return res;
    }
}
