package linkedList;
/*
    https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 */
public class removeNthNodeFromEnd {
    /*
        * Approach: Two Pass Algorithm
        *
        * Find the length of the linked list.
        * Subtract n - 1 form the length;
        * Point the length - n - 1 node to node.next.next;
     */
    public ListNode removeNthFromEnd_1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }
    /*
        Time Complexity: O(L)
        Space Complexity: O(1)
     */

    /*
        * Approach: One Pass
        *
        * Instead of one pointer, we could use two pointers.
        * The first pointer advances the list by n+1 steps from the beginning,
        * while the second pointer starts from the beginning of the list.
        * Now, both pointers are exactly separated by n nodes apart.
        * We maintain this constant gap by advancing both pointers together until the first pointer arrives past the last node.
        * The second pointer will be pointing at the nth node counting from the last.
        * We relink the next pointer of the node referenced by the second pointer to point to the node's next next node.
        *
     */
    public ListNode removeNthFromEnd_2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        for(int i = 1; i <= n + 1; ++i){
            first = first.next;
        }

        while(first != null){
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        return dummy.next;
    }
    /*
        Time Complexity: O(L)
        Space Complexity: O(1)
     */
}
