package data;

public class Overflow {

    public static int MOD = (int)1e9 + 7;

    public static int mod(int n) {

        // under overflow
        if (n < 0) n += MOD;

        // above overflow
        return n % MOD;
    }
}