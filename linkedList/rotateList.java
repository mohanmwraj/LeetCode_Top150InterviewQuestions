package linkedList;
/*
    https://leetcode.com/problems/rotate-list/description/

    rotate the list to the right by k places.

 */
public class rotateList {

    /*
        * Approach:
        *
        * The nodes in the list are already linked, and hence the rotation basically means
        *   > To close the linked list into the ring.
        *   > To break the ring after the new tail and just in front of the new head.
        *
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;
        if(head.next == null) return head;

        ListNode oldTail = head;
        int n;

        for(n = 1; oldTail.next != null; ++n){
            oldTail = oldTail.next;
        }

        oldTail.next = head;
        ListNode newTail = head;

        for(int i = 0; i < n - (k % n) - 1; ++i){
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
