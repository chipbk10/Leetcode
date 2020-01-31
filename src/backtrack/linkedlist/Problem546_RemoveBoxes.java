package backtrack.linkedlist;

public class Problem546_RemoveBoxes {

    public static void run() {
        Problem546_RemoveBoxes solution = new Problem546_RemoveBoxes();
        int[] A = new int[] {1,3,2,2,2,3,4,3,1};
        int x = solution.removeBoxes(A);
        System.out.println(x);
    }

    public int removeBoxes(int[] A) {
        ListNode dummy = new ListNode(0,0), cur = dummy;
        for (int a : A) {
            if (cur.val == a) cur.count++;
            else {
                cur.next = new ListNode(a, 1);
                cur = cur.next;
            }
        }
        backtrack(dummy, 0);
        return res;
    }

    int res = 0;

    /**
     *  TLE
     */
    private void backtrack(ListNode root, int sum) {
        ListNode prev = root, cur = prev.next;

        if (cur == null) {
            res = Math.max(res, sum);
            return;
        }

        while (cur != null) {
            ListNode next = cur.next;

            // remove cur
            sum += cur.count * cur.count;

            // merge prev & next
            if (next != null && prev.val == next.val) {
                prev.count += next.count;
                prev.next = next.next;
            }
            else {
                prev.next = next;
            }

            backtrack(root, sum);

            // split prev & next
            if (next != null && prev.val == next.val) {
                prev.count -= next.count;
            }

            // recover cur & sum
            prev.next = cur;
            cur.next = next;
            sum -= cur.count * cur.count;

            // next iterative
            prev = cur;
            cur = next;
        }
    }

    private class ListNode {
        ListNode next;
        int val;
        int count;
        ListNode(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }

}
