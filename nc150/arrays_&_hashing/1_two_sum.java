import java.util.HashMap;
import java.util.Map;

/**
 * [1] - Two Sum
 * 
 * Description:
 * Given an array of integers nums and an integer target, return the indices i and j such that nums[i] + nums[j] == target and i != j.
 * You may assume that every input has exactly one pair of indices i and j that satisfy the condition.
 * Return the answer with the smaller index first.
 * 
 * Example 1:
 * Input: nums = [3,4,5,6], target = 7
 * Output: [0,1]
 * Explanation: nums[0] + nums[1] == 7, so we return [0, 1].
 * 
 * Example 2:
 * Input: nums = [4,5,6], target = 10
 * Output: [0,2]
 * 
 * Example 3:
 * Input: nums = [5,5], target = 10
 * Output: [0,1]
 * 
 * Constraints:
 * - 2 <= nums.length <= 1000
 * - -10,000,000 <= nums[i] <= 10,000,000
 * - -10,000,000 <= target <= 10,000,000
 * 
 * ====== APPROACHES ======
 * 1. [Brute Force]
 *    - Time: O(n²) - nested loops
 *    - Space: O(1) - constant extra space
 *    - Steps:
 *      1. Use two nested loops to check every pair
 *      2. If sum equals target, return indices
 * 
 * 2. [Hash Map - One Pass]
 *    - Time: O(n) - single pass through array
 *    - Space: O(n) - hash map storage
 *    - Steps:
 *      1. Create a hash map to store number -> index
 *      2. For each number, check if complement exists
 *      3. If found, return both indices
 *      4. Otherwise, add current number to map
 * 
 * 3. [Hash Map - Two Pass]
 *    - Time: O(n) - two passes through array
 *    - Space: O(n) - hash map storage
 *    - Steps:
 *      1. First pass: store all numbers in hash map
 *      2. Second pass: look for complement
 * 
 * ====== NOTES ======
 * Common Follow-ups
 * - Q: What if there are multiple solutions?
 * - A: Return all pairs that sum to target
 * 
 * - Q: What if we need to handle duplicates?
 * - A: Store indices in a list for each number
 * 
 * - Q: What if the array is sorted?
 * - A: Use two pointers approach - O(n) time, O(1) space
 * 
 * Test Cases
 * - Edge: [1,1], target = 2
 * - Negative numbers: [-1,-2,-3,-4], target = -7
 * - Large numbers: [1000000,2000000], target = 3000000
 * - No solution: [1,2,3], target = 7
 */

class Solution {
    // Brute Force Solution
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {};
    }

    // Hash Map - One Pass Solution (Optimal)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[] {numMap.get(complement), i};
            }
            numMap.put(nums[i], i);
        }
        
        return new int[] {};
    }

    // Hash Map - Two Pass Solution
    public int[] twoSumTwoPass(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        
        // First pass: store all numbers
        for (int i = 0; i < nums.length; i++) {
            numMap.put(nums[i], i);
        }
        
        // Second pass: look for complement
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement) && numMap.get(complement) != i) {
                return new int[] {i, numMap.get(complement)};
            }
        }
        
        return new int[] {};
    }
} 