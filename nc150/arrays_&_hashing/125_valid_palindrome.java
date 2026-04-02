/**
 * [125] - Valid Palindrome
 * 
 * Description:
 * Given a string s, return true if it is a palindrome, otherwise return false.
 * A palindrome is a string that reads the same forward and backward.
 * It is also case-insensitive and ignores all non-alphanumeric characters.
 * 
 * Example 1:
 * Input: s = "Was it a car or a cat I saw?"
 * Output: true
 * Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.
 * 
 * Example 2:
 * Input: s = "tab a cat"
 * Output: false
 * Explanation: "tabacat" is not a palindrome.
 * 
 * Constraints:
 * - 1 <= s.length <= 1000
 * - s is made up of only printable ASCII characters
 * 
 * ====== APPROACHES ======
 * 1. [Reverse String]
 *    - Time: O(n)
 *    - Space: O(n)
 *    - Create new string with only alphanumeric characters
 *    - Compare with its reverse
 * 
 * 2. [Two Pointers (Optimal)]
 *    - Time: O(n)
 *    - Space: O(1)
 *    - Use two pointers from both ends
 *    - Skip non-alphanumeric characters
 *    - Compare characters case-insensitively
 * 
 * ====== NOTES ======
 * - Two Pointers approach is more space efficient
 * - Both approaches handle case-insensitivity and non-alphanumeric characters
 * - Two Pointers is the optimal solution for space complexity
 */

class Solution {
    // Approach 1: Reverse String
    public boolean isPalindromeReverse(String s) {
        StringBuilder newStr = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                newStr.append(Character.toLowerCase(c));
            }
        }
        return newStr.toString().equals(newStr.reverse().toString());
    }
    
    // Approach 2: Two Pointers (Optimal)
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l < r) {
            while (l < r && !alphaNum(s.charAt(l))) {
                l++;
            }
            while (r > l && !alphaNum(s.charAt(r))) {
                r--;
            }
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
            l++; r--;
        }
        return true;
    }

    private boolean alphaNum(char c) {
        return (c >= 'A' && c <= 'Z' || 
                c >= 'a' && c <= 'z' || 
                c >= '0' && c <= '9');
    }
} 