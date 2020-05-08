package twopointers.slowfast;

import java.util.HashMap;
import java.util.Map;

public class Problem166_FractionToRecurringDecimal {

    public String fractionToDecimal1(int numerator, int denominator) {
        if (denominator == 0) return "";
        long m = (long)numerator, n = (long)denominator;
        StringBuilder sb = new StringBuilder();

        // handle sign
        if (m > 0 && n < 0) {
            sb.append('-');
            n = -n;
        }
        if (m < 0 && n > 0) {
            sb.append('-');
            m = -m;
        }

        // handle integral part
        sb.append(m/n);
        m %= n;
        if (m == 0) return sb.toString();

        // handle fractional part
        Map<Long, Integer> map = new HashMap<>();
        sb.append('.');
        map.put(m, sb.length());
        while (m != 0) {
            m *= 10;
            sb.append(m/n);
            m %= n;
            if (map.containsKey(m)) {
                int index = map.get(m);
                sb.insert(index, '(');
                sb.append(')');
                break;
            }
            else {
                map.put(m, sb.length());
            }
        }

        return sb.toString();
    }


    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        if ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0)) {
            sb.append("-");
        }
        long nLong = Math.abs((long) numerator);
        long dLong = Math.abs((long) denominator);
        sb.append(nLong / dLong);
        nLong %= dLong;
        if (nLong == 0) {
            return sb.toString();
        }
        sb.append('.');
        nLong *= 10;

        // cycle detection
        long slow = nLong;
        long fast = nLong;
        while (fast != 0) {
            slow = next(slow, dLong);
            fast = next(next(fast, dLong), dLong);
            if (slow == fast) break;
        }
        // 2 cases: slow == fast or fast == 0
        slow = nLong;
        if (fast == 0) {
            while (slow != 0) {
                sb.append(slow / dLong);
                slow = next(slow, dLong);
            }
            return sb.toString();
        }
        // has cycle
        while (slow != fast) {
            sb.append(slow / dLong);
            slow = next(slow, dLong);
            fast = next(fast, dLong);
        }
        long cycleStart = slow;
        sb.append("(");
        do {
            sb.append(slow / dLong);
            slow = next(slow, dLong);
        } while (slow != cycleStart);
        sb.append(")");
        return sb.toString();
    }

    private long next(long numerator, long denominator) {
        numerator %= denominator;
        return numerator * 10;
    }
}
