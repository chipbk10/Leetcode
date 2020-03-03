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

}
