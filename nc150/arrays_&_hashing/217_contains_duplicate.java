import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

/**
 * [217] - Contains Duplicate
 * 
 * Description:
 * Given an integer array 'nums', return 'true' if any value appears at least twice in the array, and return 'false' if every element is distinct.
 * 
 * Examples:
 * Input: nums = [1,2,3,1]
 * Output: true
 * Explanation: The element 1 occurs at the indices 0 and 3.
 * 
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: All elements are distinct.
 * 
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 * 
 * ====== APPROACHES ======
 * 1. [Brute Force]
 *    - Time: O(n^2) - nested loop to check each element against all others
 *    - Space: O(1) - no extra space used
 *    - Steps:
 *      1. iterate through each element in the array
 *      2. for each element, iterate through the rest of the array
 *      3. if a duplicate is found, return true
 *      4. if no duplicates are found after checking all elements, return false
 * 
 * 2. [Sorting]
 *    - Time: O(n log n) - sorting the array
 *    - Space: O(1) - in place sorting
 *    - Steps:
 *      1. sort the array in place
 *      2. iterate through the array
 *      3. check if current element equals previous element
 *      4. if found, return true
 *      5. if no duplicates found after checking all elements, return false
 * 
 * 3. [HashSet]
 *    - Time: O(n) - one pass through array while hashset operations are O(1)
 *    - Space: O(n) - store up to n elements in the HashSet
 *    - Steps:
 *      1. initialize an empty HashSet
 *      2. iterate through each element in the array
 *      3. check if the element is already in the HashSet
 *      4. if it is, return true
 *      5. if not, add the element to the HashSet
 *      6. if no duplicates are found after checking all elements, return false
 * 
 * ====== NOTES ======
 * Common Follow-ups
 * - Q: How to find all duplicates instead of just checking existence?
 * - A: Use HashMap to track frequencies - O(n) time, O(n) space
 * 
 * - Q: What if array is sorted?
 * - A: Check adjacent elements - O(n) time, O(1) space
 * 
 * Test Cases
 * - Edge: [], [1]
 * - All same: [1,1,1,1]
 * - All unique: [1,2,3,4]
 * - Large range: [1000000000, -1000000000]
 * - Mixed signs: [-1,1,-1,1] 
 */

class Solution {
    // Brute Force Solution
    public boolean containsDuplicateBruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    // Sorting Solution
    public boolean containsDuplicateSorting(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return true;
            }
        }
        return false;
    }
    // Optimal Solution using HashSet
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
} 