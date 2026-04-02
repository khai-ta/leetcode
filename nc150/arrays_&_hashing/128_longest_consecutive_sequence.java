import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

/**
 * [128] - Longest Consecutive Sequence
 * 
 * Description:
 * Given an array of integers nums, return the length of the longest consecutive sequence of elements that can be formed.
 * A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous element.
 * The elements do not have to be consecutive in the original array.
 * 
 * Example 1:
 * Input: nums = [2,20,4,10,3,4,5]
 * Output: 4
 * Explanation: The longest consecutive sequence is [2, 3, 4, 5].
 * 
 * Example 2:
 * Input: nums = [0,3,2,5,4,6,1,1]
 * Output: 7
 * 
 * Constraints:
 * - 0 <= nums.length <= 1000
 * - -10^9 <= nums[i] <= 10^9
 * 
 * ====== APPROACHES ======
 * 1. [Brute Force]
 *    - Time: O(n²)
 *    - Space: O(n)
 *    - Use HashSet to store numbers
 *    - For each number, check consecutive numbers
 * 
 * 2. [Sorting]
 *    - Time: O(n log n)
 *    - Space: O(1) or O(n) depending on sorting algorithm
 *    - Sort array and count consecutive sequences
 *    - Handle duplicates
 * 
 * 3. [Hash Set (Optimal)]
 *    - Time: O(n)
 *    - Space: O(n)
 *    - Use HashSet to store numbers
 *    - Only start counting from smallest number in sequence
 *    - Most efficient solution
 * 
 * 4. [Hash Map]
 *    - Time: O(n)
 *    - Space: O(n)
 *    - Use HashMap to store sequence lengths
 *    - Update boundaries of sequences
 * 
 * ====== NOTES ======
 * - The Hash Set solution is the most efficient and readable
 * - The Brute Force solution is simple but inefficient
 * - The Sorting solution is straightforward but not optimal
 */

class Solution {
    // Approach 1: Brute Force
    public int longestConsecutiveBruteForce(int[] nums) {
        int res = 0;
        Set<Integer> store = new HashSet<>();
        for (int num : nums) {
            store.add(num);
        }

        for (int num : nums) {
            int streak = 0, curr = num;
            while (store.contains(curr)) {
                streak++;
                curr++;
            }
            res = Math.max(res, streak);
        }
        return res;
    }
    
    // Approach 2: Sorting
    public int longestConsecutiveSorting(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0, curr = nums[0], streak = 0, i = 0;

        while (i < nums.length) {
            if (curr != nums[i]) {
                curr = nums[i];
                streak = 0;
            }
            while (i < nums.length && nums[i] == curr) {
                i++;
            }
            streak++;
            curr++;
            res = Math.max(res, streak);
        }
        return res;
    }
    
    // Approach 3: Hash Set (Optimal)
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int longest = 0;

        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int length = 1;
                while (numSet.contains(num + length)) {
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }
    
    // Approach 4: Hash Map
    public int longestConsecutiveHashMap(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        int res = 0;

        for (int num : nums) {
            if (!mp.containsKey(num)) {
                mp.put(num, mp.getOrDefault(num - 1, 0) + mp.getOrDefault(num + 1, 0) + 1);
                mp.put(num - mp.getOrDefault(num - 1, 0), mp.get(num));
                mp.put(num + mp.getOrDefault(num + 1, 0), mp.get(num));
                res = Math.max(res, mp.get(num));
            }
        }
        return res;
    }
} 