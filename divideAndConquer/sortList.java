package divideAndConquer;

/*
    https://leetcode.com/problems/sort-list/description/
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class sortList {
    /*
     * Approach: Top Down Merge Sort Algorithm
     *
     * Divide phase: Divide the problem into subproblems.
     * Conquer phase: Repeatedly solve each subproblem independently
     * and combine the result to form the original problem.
     *
     * The Top-Down approach for merge sort recursively splits the original list into sublists of equal sizes,
     * sorts each sublist independently, and eventually merge the sorted lists.
     */
    public ListNode sortList_1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = getMid_1(head);
        ListNode left = sortList_1(head);
        ListNode right = sortList_1(mid);
        return merge_1(left, right);
    }

    ListNode merge_1(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
        }
        tail.next = (list1 != null) ? list1 : list2;
        return dummyHead.next;
    }

    ListNode getMid_1(ListNode head) {
        ListNode midPrev = null;
        while (head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(log n)
     */
    /*
        * Approach: Bottom Up Merge Sort
        *
     */
    ListNode tail = new ListNode();
    ListNode nextSubList = new ListNode();

    public ListNode sortList_2(ListNode head) {
        if (head == null || head.next == null) return head;
        int n = getCount_2(head);
        ListNode start = head;
        ListNode dummyHead = new ListNode();
        for (int size = 1; size < n; size = size * 2) {
            tail = dummyHead;
            while (start != null) {
                if (start.next == null) {
                    tail.next = start;
                    break;
                }
                ListNode mid = split_2(start, size);
                merge_2(start, mid);
                start = nextSubList;
            }
            start = dummyHead.next;
        }
        return dummyHead.next;
    }

    ListNode split_2(ListNode start, int size) {
        ListNode midPrev = start;
        ListNode end = start.next;
        //use fast and slow approach to find middle and end of second linked list
        for (
                int index = 1;
                index < size && (midPrev.next != null || end.next != null);
                index++
        ) {
            if (end.next != null) {
                end = (end.next.next != null) ? end.next.next : end.next;
            }
            if (midPrev.next != null) {
                midPrev = midPrev.next;
            }
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        nextSubList = end.next;
        end.next = null;
        // return the start of second linked list
        return mid;
    }

    void merge_2(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode newTail = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                newTail.next = list1;
                list1 = list1.next;
                newTail = newTail.next;
            } else {
                newTail.next = list2;
                list2 = list2.next;
                newTail = newTail.next;
            }
        }
        newTail.next = (list1 != null) ? list1 : list2;
        // traverse till the end of merged list to get the newTail
        while (newTail.next != null) {
            newTail = newTail.next;
        }
        // link the old tail with the head of merged list
        tail.next = dummyHead.next;
        // update the old tail to the new tail of merged list
        tail = newTail;
    }

    int getCount_2(ListNode head) {
        int cnt = 0;
        ListNode ptr = head;
        while (ptr != null) {
            ptr = ptr.next;
            cnt++;
        }
        return cnt;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(1)
     */
}
