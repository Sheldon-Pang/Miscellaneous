/*

Using java default PriorityQueue as min Heap
keep the size of heap less and equal to k
return head of the heap

time: O(N*log(k)), k-th elements
space: O(k)
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.poll();
    }
}