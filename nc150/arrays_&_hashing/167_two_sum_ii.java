import java.util.Map;
import java.util.HashMap;

/**
 * [167] - Two Sum II - Input Array Is Sorted
 * 
 * Description:
 * Given an array of integers numbers that is sorted in non-decreasing order.
 * Return the indices (1-indexed) of two numbers, [index1, index2], such that they add up to a given target number target
 * and index1 < index2. Note that index1 and index2 cannot be equal, therefore you may not use the same element twice.
 * There will always be exactly one valid solution.
 * 
 * Example 1:
 * Input: numbers = [1,2,3,4], target = 3
 * Output: [1,2]
 * Explanation: The sum of 1 and 2 is 3. Since we are assuming a 1-indexed array, index1 = 1, index2 = 2.
 * 
 * Constraints:
 * - 2 <= numbers.length <= 1000
 * - -1000 <= numbers[i] <= 1000
 * - -1000 <= target <= 1000
 * 
 * ====== APPROACHES ======
 * 1. [Brute Force]
 *    - Time: O(n²)
 *    - Space: O(1)
 *    - Check all possible pairs
 *    - Not efficient but simple
 * 
 * 2. [Binary Search]
 *    - Time: O(n log n)
 *    - Space: O(1)
 *    - For each number, binary search for complement
 *    - Better than brute force but not optimal
 * 
 * 3. [Hash Map]
 *    - Time: O(n)
 *    - Space: O(n)
 *    - Store numbers and their indices
 *    - Fast but uses extra space
 * 
 * 4. [Two Pointers (Optimal)]
 *    - Time: O(n)
 *    - Space: O(1)
 *    - Use two pointers from both ends
 *    - Most efficient solution for sorted array
 * 
 * ====== NOTES ======
 * - Two Pointers is the optimal solution for sorted array
 * - Hash Map is best for unsorted array
 * - Binary Search is a good compromise
 * - Brute Force is simple but inefficient
 */

class Solution {
    // Approach 1: Brute Force
    public int[] twoSumBruteForce(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[] { i + 1, j + 1 };
                }
            }
        }
        return new int[0];
    }
    
    // Approach 2: Binary Search
    public int[] twoSumBinarySearch(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int l = i + 1, r = numbers.length - 1;
            int tmp = target - numbers[i];
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (numbers[mid] == tmp) {
                    return new int[] { i + 1, mid + 1 };
                } else if (numbers[mid] < tmp) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return new int[0];
    }
    
    // Approach 3: Hash Map
    public int[] twoSumHashMap(int[] numbers, int target) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int tmp = target - numbers[i];
            if (mp.containsKey(tmp)) {
                return new int[] { mp.get(tmp), i + 1 };
            }
            mp.put(numbers[i], i + 1);
        }
        return new int[0];
    }
    
    // Approach 4: Two Pointers (Optimal)
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;

        while (l < r) {
            int curSum = numbers[l] + numbers[r];

            if (curSum > target) {
                r--;
            } else if (curSum < target) {
                l++;
            } else {
                return new int[] { l + 1, r + 1 };
            }
        }
        return new int[0];
    }
} 