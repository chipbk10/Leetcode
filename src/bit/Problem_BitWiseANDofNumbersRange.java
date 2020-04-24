package bit;

public class Problem_BitWiseANDofNumbersRange {

    public static void run() {
        Problem_BitWiseANDofNumbersRange solution = new Problem_BitWiseANDofNumbersRange();
        int a = 5, b = 7;
        int res = solution.rangeBitwiseAnd(a, b);
        System.out.println(res);
    }

    public int rangeBitwiseAnd(int m, int n) {
        int i = 0; // i means we have how many bits are 0 on the right
        while(m != n){
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }
}
