package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
    https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/

 */
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
public class populatingNextRightPointersinEachNodeII {
    /*
        * Approach: Level Order Traversal
        *
        * do a BFS traversal using a queue.
        * at every level, establish a pointer from current node to next of queue.peek()
        * if(current node index < level size)
        *   current node.next = queue.peek()
        *
     */
    public Node connect_1(Node root){
        if(root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0; i < size; ++i){
                Node node = queue.poll();
                if(i < size - 1) node.next = queue.peek();

                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
        }
        return root;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Using Previously Established Pointers
        *
        * Consider nodes at every level as linked list.
        * From current level to next level
        *   - create a sentinel node points to first node in the next level
        *           dummyNode.next = head.left;
        *   - using this establish a next pointer, node at current level
        *           node.left.next = node.right
        *   - move to the next node in the current level
        *            head = head.next
     */
    public Node connect_2(Node root){
        if(root == null) return root;
        Node head = root;

        while(head != null){
            Node dummyNode = new Node(0);
            Node temp = dummyNode;
            while(head != null){
                if(head.left != null){
                    temp.next = head.left;
                    temp = temp.next;
                }
                if(head.right != null){
                    temp.next = head.right;
                    temp = temp.next;
                }

                head = head.next;
            }
            head = dummyNode.next;
        }

        return root;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
