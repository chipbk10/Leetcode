package linkedlist;

import data.ListNode;

public class Problem876_MiddleOfLinkedList {

    public ListNode middleNode(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

}
