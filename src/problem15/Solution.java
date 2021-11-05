/* 15. 3Sum (https://leetcode.com/problems/3sum/)

Runtime: 12 ms, faster than 99.88%!!! of Java online submissions for 3Sum.

Details: Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
        Notice that the solution set must not contain duplicate triplets. */

package problem15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private int [] valToIdx = null; // First occurrence index in the sorted array (1-based index, not zero-based!).

    /**
     * Builds valToIdx array mapping values valToIdx[value - nums[0]] to 1st occurrence indices in nums[]
     */
    private void buildValToIdx(int [] nums){
        // Would be much easier with valToIdx being a HashMap, but +16ms are +16ms ;)
        valToIdx = new int [nums[nums.length - 1] - nums[0] + 1];
        int prevVal = nums[0] - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != prevVal) {
                valToIdx[nums[i] - nums[0]] = i + 1;
                prevVal = nums[i];
            }
        }
    }

    /**
     * Checks if the iMinIdx to iMaxIdx range of nums[] still can have build zero sum of 3 elements
     */
    private static boolean potentialSums(int[] nums, int iMinIdx, int iMaxIdx) {
        return  iMinIdx >= 0 && iMaxIdx < nums.length && // Check array bounds
                iMaxIdx - iMinIdx > 1 && nums[iMinIdx] <= 0 && nums[iMaxIdx] >= 0;
    }

    private void collectTriplets(List<List<Integer>> ret, int[] nums) {

        int iMinIdx = 0;                // Starting index, minimum (negative) value
        int iMaxIdx = nums.length - 1;  // End index, maximum (positive) value

        // Reduce iMaxIdx skipping obviously unacceptable part
        while (potentialSums(nums, iMinIdx, iMaxIdx) && -nums[iMaxIdx] < nums[iMinIdx] + nums[iMinIdx + 1])
            --iMaxIdx;

        int prevMaxIdx = iMaxIdx;       // We'd return to it once we increment iMinIdx

        while (potentialSums(nums, iMinIdx, prevMaxIdx)) {

            // No way to level negative value
            if (!potentialSums(nums, iMinIdx, iMaxIdx) ||
                    nums[iMaxIdx] + nums[iMaxIdx - 1] < -nums[iMinIdx]) {

                // Increment iMinIdx, skipping its duplicates
                do {
                    ++iMinIdx;
                } while (potentialSums(nums, iMinIdx, prevMaxIdx) && nums[iMinIdx] == nums[iMinIdx - 1]);

                // Start over from previous Max Index, but cut away obviously unacceptable part
                while (potentialSums(nums, iMinIdx, prevMaxIdx) && -nums[prevMaxIdx] < nums[iMinIdx] + nums[iMinIdx + 1]) {
                    --prevMaxIdx;
                }

                iMaxIdx = prevMaxIdx;
                continue;
            }

            int idx = valToIdx[-nums[iMinIdx] - nums[iMaxIdx] - nums[0]];
            if (idx > 0) {
                // Check if we are not trying to reuse the same element twice
                if (idx - 1 == iMinIdx && nums[iMinIdx] != nums[iMinIdx + 1]) {
                    idx = 0;
                } else if (idx - 1 == iMaxIdx && (iMaxIdx + 1 >= nums.length || nums[iMaxIdx] != nums[iMaxIdx + 1])) {
                    idx = 0;
                }

                if (idx > 0) {
                    List<Integer> foundTriplet = new ArrayList<>(3);
                    foundTriplet.add(nums[iMinIdx]);
                    foundTriplet.add(-nums[iMinIdx] - nums[iMaxIdx]);
                    foundTriplet.add(nums[iMaxIdx]);
                    ret.add(foundTriplet);

                    while(potentialSums(nums, iMinIdx + 1, iMaxIdx) && nums[iMinIdx] == nums[iMinIdx + 1])
                        ++iMinIdx;
                }
            }

            // Move iMaxIdx closer to Zero skipping duplicates
            do {
                --iMaxIdx;
            } while (potentialSums(nums, iMinIdx, iMaxIdx) && nums[iMaxIdx] == nums[iMaxIdx + 1]);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length < 3)
            return ret;

        Arrays.sort(nums);

        if (!potentialSums(nums, 0, nums.length - 1)) {
            return ret;
        }

        buildValToIdx(nums);

        collectTriplets(ret, nums);

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().threeSum(new int [] {-1,0,1,2,-1,-4}));
        System.out.println("[[-1, -1, 2], [-1, 0, 1]] - EXPECTED");

        System.out.println(new Solution().threeSum(new int [] {1,-1,-1,0}));
        System.out.println("[[-1, 0, 1]] - EXPECTED");

        System.out.println(new Solution().threeSum(new int [] {-4, -3, -2, -2, -2, -2, -1, -1, -1, 4, 6}));
        System.out.println("[[-4, -2, 6], [-3, -1, 4], [-2, -2, 4]] - EXPECTED");

        //System.out.println(new Solution().threeSum(new int [] {3,0,-2,-1,1,2}));
        //System.out.println(new Solution().threeSum(new int [] {34,55,79,28,46,33,2,48,31,-3,84,71,52,-3,93,15,21,-43,57,-6,86,56,94,74,83,-14,28,-66,46,-49,62,-11,43,65,77,12,47,61,26,1,13,29,55,-82,76,26,15,-29,36,-29,10,-70,69,17,49}));
        //System.out.println(new Solution().threeSum(new int [] {-1, 0, 1, 2, -1, -4}));
        //System.out.println(new Solution().threeSum(new int [] {-1,0,1,2,-1,-4,-2,-3,3,0,4}));
        //System.out.println(new Solution().threeSum(new int [] {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));
    }
}
