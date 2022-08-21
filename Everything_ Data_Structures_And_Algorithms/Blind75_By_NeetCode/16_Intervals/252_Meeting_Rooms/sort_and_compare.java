/* 252. Meeting Rooms */
/* Sort and compare, Time: O(log(n)), Space: O(1) */
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {

        /* Sort the array by starting time */
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }

        return true;
    }
}