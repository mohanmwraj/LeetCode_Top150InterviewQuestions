package binaryTree;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/*
    https://leetcode.com/problems/symmetric-tree/
 */
public class symmetricTree {
    /*
        * Approach: Recursion
        *
        * Two trees are a mirror reflection of each other if:
        *   Their two roots have the same value.
        *   The right subtree of each tree is a mirror reflection of the left subtree of the other tree.
        *
     */

    public boolean isSummetric_1(TreeNode root){
        if(root.left == null && root.right == null) return true;
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2){
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        if(t1.val != t2.val) return false;

        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Iteration
        *
        * Each time, two nodes are extracted and their values are compared.
        * the right and left children of the two nodes are inserted in the queue in opposite order.
     */

    public boolean isSymmetric_2(TreeNode root){
        /*
            * We cannot use Array Deque, as it is not allowed to insert null value.
            * Queue with linked list implementation will allow insertion of null into it.
         */
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            if(t1 == null && t2 == null) continue;
            if(t1 == null || t2 == null) return false;
            if(t1.val != t2.val) return false;

            queue.add(t1.left);
            queue.add(t2.right);

            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
