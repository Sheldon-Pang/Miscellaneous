/* Brute force, Time Limit Exceeded, Time: O(n^2), Space: O(1) */
class Solution {
    public int maxArea(int[] height) {

        int maxVal = 0;

        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                /* Update container height */
                int containerHeight = height[i];
                if (height[i] > height[j]) {
                    containerHeight = height[j];
                }
                /* Update max volume */
                int tempVolume = containerHeight * (j - i);
                maxVal = Math.max(maxVal, tempVolume);
            }
        }

        return maxVal;
    }
}