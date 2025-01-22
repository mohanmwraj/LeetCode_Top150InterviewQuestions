package linkedList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
    https://leetcode.com/problems/lru-cache/description/

 */
public class LRUCache {
    /*
        * Approach: Doubly Linked List
        *
        * We need a way to store data in an ordered manner such that elements can be removed from any position in constant time.
        * A linked list is a great candidate for this task.
        * Removing from arbitrary positions is one of the few things that a linked list does better than an array.
        *
     */
    static class ListNode {
        int key;
        int val;
        ListNode next;
        ListNode prev;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static class LRUCache_1 {
        int capacity;
        Map<Integer, ListNode> dic;
        ListNode head;
        ListNode tail;

        public LRUCache_1(int capacity) {
            this.capacity = capacity;
            dic = new HashMap<>();
            head = new ListNode(-1, -1);
            tail = new ListNode(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!dic.containsKey(key)) {
                return -1;
            }

            ListNode node = dic.get(key);
            remove(node);
            add(node);
            return node.val;
        }

        public void put(int key, int value) {
            if (dic.containsKey(key)) {
                ListNode oldNode = dic.get(key);
                remove(oldNode);
            }

            ListNode node = new ListNode(key, value);
            dic.put(key, node);
            add(node);

            if (dic.size() > capacity) {
                ListNode nodeToDelete = head.next;
                remove(nodeToDelete);
                dic.remove(nodeToDelete.key);
            }
        }

        public void add(ListNode node) {
            ListNode previousEnd = tail.prev;
            previousEnd.next = node;
            node.prev = previousEnd;
            node.next = tail;
            tail.prev = node;
        }

        public void remove(ListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(capacity)
     */

    /*
        * Approach: Built-In
        *
        * In Java, we will be using LinkedHashMap, which is a hash map that maintains insertion order.
        * It essentially implements the linked list for us in the same data structure as the hash map,
        * with the add and remove methods built into the hash map operations.
     */
    static class LRUCache_2 {
        int capacity;
        LinkedHashMap<Integer, Integer> dic;

        public LRUCache_2(int capacity) {
            this.capacity = capacity;
            dic = new LinkedHashMap<>(5, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(
                        Map.Entry<Integer, Integer> eldest
                ) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) {
            return dic.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            dic.put(key, value);
        }
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(capacity)
     */

}
