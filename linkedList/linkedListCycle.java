package linkedList;

import java.util.HashSet;
import java.util.Set;

/*
    https://leetcode.com/problems/linked-list-cycle/description/
 */
public class linkedListCycle {
    /*
        * Approach: Hash Table
        *
     */
    public boolean hasCycle_1(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (nodesSeen.contains(current)) {
                return true;
            }
            nodesSeen.add(current);
            current = current.next;
        }
        return false;
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Floyd's Cycle Finding Algorithm
        *
        * Slow and Fast Runner
        *
     */
    public boolean hasCycle_2(ListNode head) {
        if(head == null){
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
