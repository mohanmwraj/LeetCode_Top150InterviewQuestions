package linkedList;
/*
    https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 */
public class reverseNodesinKGroup {
    /*
     * Approach: Recursion
     *
     * In every recursive call, we first count the number of nodes in the linked list.
     * As soon as the count reaches k, we break.
     * If there are less than k nodes left in the list, we return the head of the list.
     * However, if there are at least k nodes in the list, then we reverse these nodes by calling our reverse() function.
     * So, in every recursive call, we first reverse k nodes, then recurse on the rest of the linked list.
     * When recursion returns, we establish the proper connections.
     */
    public ListNode reverseLinkedList(ListNode head, int k) {
        // Reverse k nodes of the given linked list.
        // This function assumes that the list contains
        // atleast k nodes.
        ListNode new_head = null;
        ListNode ptr = head;

        while (k > 0) {
            // Keep track of the next node to process in the
            // original list
            ListNode next_node = ptr.next;

            // Insert the node pointed to by "ptr"
            // at the beginning of the reversed list
            ptr.next = new_head;
            new_head = ptr;

            // Move on to the next node
            ptr = next_node;

            // Decrement the count of nodes to be reversed by 1
            k--;
        }

        // Return the head of the reversed list
        return new_head;
    }

    public ListNode reverseKGroup_1(ListNode head, int k) {
        int count = 0;
        ListNode ptr = head;

        // First, see if there are atleast k nodes
        // left in the linked list.
        while (count < k && ptr != null) {
            ptr = ptr.next;
            count++;
        }

        // If we have k nodes, then we reverse them
        if (count == k) {
            // Reverse the first k nodes of the list and
            // get the reversed list's head.
            ListNode reversedHead = this.reverseLinkedList(head, k);

            // Now recurse on the remaining linked list. Since
            // our recursion returns the head of the overall processed
            // list, we use that and the "original" head of the "k" nodes
            // to re-wire the connections.
            head.next = this.reverseKGroup_1(ptr, k);
            return reversedHead;
        }

        return head;
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(N/k)
     */

    /*
     * Approach: Iterative O(1) Space
     *
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode ptr = head;
        ListNode ktail = null;

        // Head of the final, moified linked list
        ListNode new_head = null;

        // Keep going until there are nodes in the list
        while (ptr != null) {
            int count = 0;

            // Start counting nodes from the head
            ptr = head;

            // Find the head of the next k nodes
            while (count < k && ptr != null) {
                ptr = ptr.next;
                count += 1;
            }

            // If we counted k nodes, reverse them
            if (count == k) {
                // Reverse k nodes and get the new head
                ListNode revHead = this.reverseLinkedList(head, k);

                // new_head is the head of the final linked list
                if (new_head == null) new_head = revHead;

                // ktail is the tail of the previous block of
                // reversed k nodes
                if (ktail != null) ktail.next = revHead;

                ktail = head;
                head = ptr;
            }
        }
        // attach the final, possibly un-reversed portion
        if (ktail != null) ktail.next = head;

        return new_head == null ? head : new_head;
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */
}