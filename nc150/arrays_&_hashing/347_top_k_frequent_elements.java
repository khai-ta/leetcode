import java.util.*;

/**
 * [347] - Top K Frequent Elements
 * 
 * Description:
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * The answer is guaranteed to be unique.
 * 
 * Example 1:
 * Input: nums = [1,2,2,3,3,3], k = 2
 * Output: [2,3]
 * 
 * Example 2:
 * Input: nums = [7,7], k = 1
 * Output: [7]
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^4
 * - -1000 <= nums[i] <= 1000
 * - 1 <= k <= number of distinct elements in nums
 * 
 * ====== APPROACHES ======
 * 1. [Min-Heap]
 *    - Time: O(n log k) - where n is array length
 *    - Space: O(n + k) - for hashmap and heap
 *    - Steps:
 *      1. Count frequencies using HashMap
 *      2. Use min heap to keep top k elements
 *      3. Convert heap to result array
 * 
 * 2. [Bucket Sort]
 *    - Time: O(n) - where n is array length
 *    - Space: O(n) - for frequency map and buckets
 *    - Steps:
 *      1. Count frequencies using HashMap
 *      2. Create buckets for each frequency
 *      3. Collect elements from highest frequency buckets
 * 
 * ====== NOTES ======
 * Common Follow-ups
 * - Q: What if we need to handle negative numbers?
 * - A: Both approaches work with negative numbers as is
 * 
 * - Q: What if we need to handle very large numbers?
 * - A: Both approaches work with any integer range
 * 
 * - Q: What if we need to handle ties?
 * - A: Current problem guarantees unique answer, but we could modify to handle ties
 * 
 * Test Cases
 * - Single element: [1], k=1
 * - All same elements: [1,1,1], k=1
 * - All different elements: [1,2,3], k=2
 * - Negative numbers: [-1,-1,2,2,3], k=2
 * - Edge case: [1,1,1,2,2,3], k=3
 */

class Solution {
    // Approach 1: Min-Heap
    public int[] topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            heap.offer(new int[]{entry.getValue(), entry.getKey()});
            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll()[1];
        }
        return res;
    }
    
    // Approach 2: Bucket Sort (Optimal)
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        List<Integer>[] freq = new List[nums.length + 1];

        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            freq[entry.getValue()].add(entry.getKey());
        }

        int[] res = new int[k];
        int index = 0;
        for (int i = freq.length - 1; i > 0 && index < k; i--) {
            for (int n : freq[i]) {
                res[index++] = n;
                if (index == k) {
                    return res;
                }
            }
        }
        return res;
    }
} 