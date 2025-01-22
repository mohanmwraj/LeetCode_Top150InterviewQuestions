package linkedList;
/*
    https://leetcode.com/problems/reverse-linked-list-ii/description/
 */
public class reverseLinkedListII {
    /*
        * Approach: Recursion
        *
        * Consider linked list as array.
        * Use two pointers.
        * One pointer at beginning of the list, other one at end of the list.
        * every call move the front pointer, backtrack decrement the end pointer.
        * swap the values until both pointers cross each other.
        *
     */
    private boolean stop;
    private ListNode left;

    public void recurseAndReverse_1(ListNode right, int m, int n) {
        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse_1(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }

    public ListNode reverseBetween_1(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse_1(head, m, n);
        return head;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Iterative Link reversal
        *
        * Move the two pointers m and n until they reach the proper starting point in the list.
        * Add another pointers con, tail.
        * The two pointers that will fix the final connections.
        * Iteratively reverse the nodes until n becomes 0.
        * Adjust the final connections
     */
    public ListNode reverseBetween_2(ListNode head, int left, int right) {
        if(head == null) return null;

        ListNode prev = null;
        ListNode curr = head;

        while(left > 1){
            prev = curr;
            curr = curr.next;
            left--;
            right--;
        }

        ListNode con = prev;
        ListNode tail = curr;
        ListNode next = null;

        while(right > 0){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            right--;
        }

        if(con != null) con.next = prev;
        else head = prev;

        tail.next = curr;

        return head;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
