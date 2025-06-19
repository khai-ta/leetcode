import java.util.*;

/**
 * [15] - 3Sum
 * 
 * Description:
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that nums[i] + nums[j] + nums[k] == 0 and i, j, k are distinct.
 * No duplicate triplets should be returned. Triplets can be in any order.
 * 
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * 
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 * 
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * 
 * Constraints:
 * - 3 <= nums.length <= 1000
 * - -10^5 <= nums[i] <= 10^5
 * 
 * ====== APPROACHES ======
 * 1. [Brute Force]
 *    - Time: O(n^3)
 *    - Space: O(m) - m = number of valid triplets
 *    - Steps:
 *      1. Use 3 nested loops to try all i < j < k combinations
 *      2. Check if nums[i] + nums[j] + nums[k] == 0
 *      3. Use Set to avoid duplicate triplets
 * 
 * 2. [Hash Map]
 *    - Time: O(n^2)
 *    - Space: O(n)
 *    - Steps:
 *      1. Sort the array
 *      2. Iterate with two nested loops
 *      3. Use a hash map to track complements
 * 
 * 3. [Two Pointers - Optimal]
 *    - Time: O(n^2)
 *    - Space: O(m) - m = number of valid triplets
 *    - Steps:
 *      1. Sort the array
 *      2. Fix one number and use two pointers to find remaining two
 *      3. Skip duplicates
 * 
 * ====== NOTES ======
 * - Triplets must be unique
 * - Array must be sorted for two-pointer method
 * 
 * Test Cases:
 * - [-1,0,1,2,-1,-4] → [[-1,-1,2],[-1,0,1]]
 * - [0,0,0] → [[0,0,0]]
 * - [1,2,-2,-1] → []
 */

public class Solution {

    // Brute Force Solution
    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    // Hash Map Solution
    public List<List<Integer>> threeSumHashMap(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        List<List<Integer>> result = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.get(nums[i]) - 1);
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < nums.length; j++) {
                count.put(nums[j], count.get(nums[j]) - 1);
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int third = -(nums[i] + nums[j]);
                if (count.getOrDefault(third, 0) > 0) {
                    List<Integer> triplet = Arrays.asList(nums[i], nums[j], third);
                    triplet.sort(Integer::compare);
                    String key = triplet.toString();
                    if (!seen.contains(key)) {
                        result.add(triplet);
                        seen.add(key);
                    }
                }
            }

            for (int j = i + 1; j < nums.length; j++) {
                count.put(nums[j], count.get(nums[j]) + 1);
            }
        }
        return result;
    }

    // Two Pointers - Optimal Solution
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break; // Since array is sorted, no point in continuing
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1]) left++; // Skip duplicates
                    while (left < right && nums[right] == nums[right + 1]) right--; // Skip duplicates
                }
            }
        }

        return result;
    }
}