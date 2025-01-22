package linkedList;
/*
    https://leetcode.com/problems/partition-list/description/

    Given the head of a linked list and a value x,
    partition it such that all nodes less than x come before nodes greater than or equal to x.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class partitionList {
    /*
        * Approach: Two Pointer Approach
        *
        * We can take two pointers before and after to keep track of the two linked lists.
        * These two pointers could be used two create two separate lists
        * and then these lists could be combined to form the desired reformed list.
        *
     */
    public ListNode partition(ListNode head, int x) {
        ListNode beforeHead = new ListNode(0);
        ListNode before = beforeHead;
        ListNode afterHead = new ListNode(0);
        ListNode after = afterHead;

        while(head != null){
            if(head.val < x){
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }

            head = head.next;
        }

        after.next = null;
        before.next = afterHead.next;

        return beforeHead.next;
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */
}
