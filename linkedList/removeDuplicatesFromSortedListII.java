package linkedList;

/*
    https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
 */
public class removeDuplicatesFromSortedListII {
    /*
        * Approach: Single Head + Predecessor
        *
        * The standard way to handle this use case is to use the so-called Sentinel Node.
        * Sentinel nodes are widely used for trees and linked lists such as pseudo-heads, pseudo-tails, etc.
        * They are purely functional and usually don't hold any data.
        * Their primary purpose is to standardize the situation to avoid edge case handling.
        *
        * The input list is sorted, and we can determine if a node is a duplicate by comparing its value to the node after it in the list.
        *  Step by step, we could identify the current sublist of duplicates.
        *
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(0, head);
        ListNode pred = sentinel;

        while(head != null){
            if(head.next != null && head.val == head.next.val){
                while(head.next != null && head.val == head.next.val){
                    head = head.next;
                }

                pred.next = head.next;
                // To remove all the duplicates, predecessor points to previous of head.
                // If head doesn't have any duplicates, predecessor moves to head.
            } else {
                pred = pred.next;
            }
            head = head.next;
        }

        return sentinel.next;
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */
}
