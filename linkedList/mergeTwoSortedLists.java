package linkedList;
/*
    https://leetcode.com/problems/merge-two-sorted-lists/
 */

public class mergeTwoSortedLists {
    /*
        * Approach: Recursion
        *
        * list1[0] + merge(list1[1:],list2) list1[0]<list2[0]
        * list2[0]+merge(list1,list2[1:])   otherwise
     */
    public ListNode mergeTwoLists_1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists_1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_1(l1, l2.next);
            return l2;
        }
    }

    /*
        Time Complexity: O(n + m)
        Space Complexity: O(n + m)
     */

    /*
        * Approach: Iteration
        *
        * We can achieve the same idea via iteration by assuming that l1
        * is entirely less than l2 and processing the elements one-by-one,
        * inserting elements of l2 in the necessary places in l1.
        *
     */
    public ListNode mergeTwoLists_2(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
    /*
        Time Complexity: O(n + m)
        Space Complexity: O(1)
     */
}
