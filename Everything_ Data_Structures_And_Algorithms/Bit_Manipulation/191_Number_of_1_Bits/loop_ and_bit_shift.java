public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {

        int bits = 0;

        for (int i = 0; i < 32; i++) {
            if (n % 2 == 1 || n % 2 == -1) {
                bits++;
            }
            n = n >> 1;
        }

        return bits;
    }
}