/*

Time: O(n*log(n) + m * log(k))
note: n is length of nums, m is number of add() call, k is heap size
Space: worst case O(n)

*/
class KthLargest {

    private static PriorityQueue<Integer> heap;
    private static int k;

    public KthLargest(int k, int[] nums) {
        heap = new PriorityQueue<>();
        this.k = k;

        for (int num : nums) {
            heap.add(num);
        }

        while (heap.size() > k) {
            heap.poll();
        }
    }

    public int add(int val) {
        heap.add(val);
        if (heap.size() > k) {
            heap.poll();
        }

        return heap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */