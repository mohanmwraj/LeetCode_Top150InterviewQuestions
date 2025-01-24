package divideAndConquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
    https://leetcode.com/problems/merge-k-sorted-lists/description/

    You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
    Merge all the linked-lists into one sorted linked-list and return it.

 */
public class mergeKSortedLists {
    /*
        * Approach: Brute Force
        *
        * Traverse all the linked lists and collect the values of the nodes into an array.
        * Sort and iterate over this array to get the proper value of nodes.
        * Create a new sorted linked list and extend it with the new nodes.
        *
     */
    public ListNode mergeKLists_1(ListNode[] lists) {
        ArrayList<Integer> nodes = new ArrayList<>();
        ListNode head = new ListNode(0);
        ListNode point = head;
        for (ListNode l : lists) {
            while (l != null) {
                nodes.add(l.val);
                l = l.next;
            }
        }
        Collections.sort(nodes);
        for (int x : nodes) {
            point.next = new ListNode(x);
            point = point.next;
        }
        return head.next;
    }

    /*
        Time Complexity: O(N log N)
        Space Complexity: O(N)
     */

    /*
        * Approach: Compare One by one
        *
        * Compare every k nodes (head of every linked list) and get the node with the smallest value.
        * Extend the final sorted linked list with the selected nodes.
        *
        * Time Complexity: O(kN)
        * Space Complexity: O(N)
        *
     */

    /*
        * Approach: Optimize by Priority Queue
     */
    public ListNode mergeKLists_2(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode point = head;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(
                new Comparator<ListNode>() {
                    @Override
                    public int compare(ListNode o1, ListNode o2) {
                        if (o1.val > o2.val) {
                            return 1;
                        } else if (o1.val == o2.val) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                }
        );
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            point.next = queue.poll();
            point = point.next;
            if (point.next != null) {
                queue.add(point.next);
            }
        }
        return head.next;
    }
    /*
        Time Complexity: O(N log K)
        Space Complexity: O(n)
     */

    /*
        * Approach: Merge Lists one by one
        *
        * Convert merge k lists problem to merge 2 lists (k-1) times.
        *
        * Time Complexity: O(kN)
        * Space Complexity: O(1)
     */

    /*
        * Approach: Merge with Divide and Conquer
        *
        * Pair up k lists and merge each pair.
        * After the first pairing, k lists are merged into k/2 lists with average 2N/k length, then k/4, k/8 and so on.
        * Repeat this procedure until we get the final sorted linked list.
        *
        * Thus, we'll traverse almost N nodes per pairing and merging, and repeat this procedure about log2​k times.
        *
     */
    public ListNode mergeKLists_3(ListNode[] lists){
        int amount = lists.length;
        int interval = 1;

        while(interval < amount){
            for(int i = 0; i < amount - interval; i += interval * 2){
                lists[i] = merge2Lists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }

        return amount > 0 ? lists[0] : null;
    }

    private ListNode merge2Lists(ListNode list1, ListNode list2){
        ListNode head = new ListNode(0);
        ListNode point = head;

        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                point.next = list1;
                list1 = list1.next;
            } else {
                point.next = list2;
                list2 = list2.next;
            }
            point = point.next;
        }

        point.next = (list1 == null) ? list2 : list1;
        return head.next;
    }

    /*
        Time Complexity: O(N log k)
        Space Complexity: O(1)
     */
}
