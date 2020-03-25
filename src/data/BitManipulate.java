package data;

public class BitManipulate {

    public static boolean is2Dividend(int n) {
        return (n & 1) == 0;
    }

    public static int countBitOne(int n) {
        int res = 0;
        while (n != 0) {
            res += (n & 1);
            n >>= 1;
        }
        return res;
    }

    public static boolean areDuplicated(int m, int n) {
        return ((m & n) > 0);
    }

    public static int getMask(String s) {
        char[] cs = s.toCharArray();
        return getMask(cs);
    }

    public static int getMask(char[] cs) {
        int res = 0;
        for (char c : cs) {
            int num = (1 << (c-'a'));
            if ((res & num) > 0) return 0;
            res |= num;
        }
        return res;
    }

    public static boolean isPowerOfTwo(int n) {
        return n != 0 && ((n & (n-1)) == 0);
    }
}
