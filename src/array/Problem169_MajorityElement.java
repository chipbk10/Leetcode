package array;

public class Problem169_MajorityElement {
    public int majorityElement(int[] A) {
        int res = 0, count = 0;
        for (int a : A) {
            if (count == 0) res = a;
            if (a == res) count++;
            else count--;
        }
        return res;
    }
}
