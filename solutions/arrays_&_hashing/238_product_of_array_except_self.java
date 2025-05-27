/**
 * [238] - Product of Array Except Self
 * 
 * Description:
 * Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].
 * Each product is guaranteed to fit in a 32-bit integer.
 * 
 * Example 1:
 * Input: nums = [1,2,4,6]
 * Output: [48,24,12,8]
 * 
 * Example 2:
 * Input: nums = [-1,0,1,2,3]
 * Output: [0,-6,0,0,0]
 * 
 * Constraints:
 * - 2 <= nums.length <= 1000
 * - -20 <= nums[i] <= 20
 * 
 * ====== APPROACHES ======
 * 1. [Brute Force]
 *    - Time: O(n²)
 *    - Space: O(1) extra space
 *    - For each element, multiply all other elements
 * 
 * 2. [Division]
 *    - Time: O(n)
 *    - Space: O(1) extra space
 *    - Calculate total product and divide by each element
 *    - Handle zero cases separately
 * 
 * 3. [Prefix & Suffix Arrays]
 *    - Time: O(n)
 *    - Space: O(n)
 *    - Use two arrays to store prefix and suffix products
 * 
 * 4. [Prefix & Suffix (Optimal)]
 *    - Time: O(n)
 *    - Space: O(1) extra space
 *    - Use output array to store prefix products
 *    - Use a variable to track suffix products
 * 
 * ====== NOTES ======
 * - The optimal solution avoids using division
 * - Handles all edge cases including zeros
 * - Space efficient as it uses only the output array
 */

class Solution {
    // Approach 1: Brute Force
    public int[] productExceptSelfBruteForce(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int prod = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    prod *= nums[j];
                }
            }
            res[i] = prod;
        }
        return res;
    }
    
    // Approach 2: Division
    public int[] productExceptSelfDivision(int[] nums) {
        int prod = 1, zeroCount = 0;
        for (int num : nums) {
            if (num != 0) {
                prod *= num;
            } else {
                zeroCount++;
            }
        }
        
        if (zeroCount > 1) {
            return new int[nums.length]; 
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (zeroCount > 0) {
                res[i] = (nums[i] == 0) ? prod : 0;
            } else {
                res[i] = prod / nums[i];
            }
        }
        return res;
    }
    
    // Approach 3: Prefix & Suffix
    public int[] productExceptSelfPrefixSuffix(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] pref = new int[n];
        int[] suff = new int[n];

        pref[0] = 1;
        suff[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            pref[i] = nums[i - 1] * pref[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            suff[i] = nums[i + 1] * suff[i + 1];
        }
        for (int i = 0; i < n; i++) {
            res[i] = pref[i] * suff[i];
        }
        return res;
    }
    
    // Approach 4: Prefix & Suffix (Optimal)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        
        int postfix = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= postfix;
            postfix *= nums[i];
        }
        return res;
    }
} 