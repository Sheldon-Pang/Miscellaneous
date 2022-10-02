/*

weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15

Min poosible weight = largest item of array (10)
Max poosible weight = sum of the array (55)

pesudo Code:
    calculate sum = endW
    find min = startW

    startW = 10
    endW = 55

    while (stratW < endW)
        midW = average of startW and endW


    weights = [1,2,3,4,5,6,7,8,9,10], days = 5

    perform binary search[10  12 13 14 15 15    21      32            55]
                                        L  R

    Condition is (curWeight + weight > mid)
        requiredDays++ = 6
        curWeight = 0
    curWeight = curWeight + weight = 10

    -set left and right pointer base on requiredDays calculated from previous step

*/

/* Time: O(n*log(k)), Space: O(1)
*  where n is number of items in int[] weights
*  k is sum of weights - largest item of weights
* */
class Solution {
    public int shipWithinDays(int[] weights, int days) {

        int left = 0, right = 0;

        /* Find left(Largest item in list) and right(sum of the list) */
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }

        while (left < right) {
            int mid = (left + right) / 2;
            int requiredDays = 1, curWeight = 0;

            /* Going through list to calculate required days to ship */
            for (int weight: weights) {
                if (curWeight + weight > mid) {
                    requiredDays++;
                    curWeight = 0;
                }
                curWeight += weight;
            }

            /* Binary search logic */
            if (requiredDays > days) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}