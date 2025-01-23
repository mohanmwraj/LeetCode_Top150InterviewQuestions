package binaryTree;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;

/*
    https://leetcode.com/problems/invert-binary-tree/
 */
public class invertBinaryTree {
    /*
        * Approach: Recursion
        *
        * The inverse of a tree with root r, and subtrees right and left, is a tree with root r,
        * whose right subtree is the inverse of left, and whose left subtree is the inverse of right.
        *
     */

    public TreeNode invertTree_1(TreeNode root){
        if(root == null) return null;

        TreeNode right = invertTree_1(root.right);
        TreeNode left = invertTree_1(root.left);
        root.left = right;
        root.right = left;
        return root;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Iterative
        *
        * we create a queue to store nodes whose left and right child have not been swapped yet.
        * As long as the queue is not empty, remove the next node from the queue,
        * swap its children, and add the children to the queue.
        *
     */

    public TreeNode invertTree_2(TreeNode root){
        if(root == null) return null;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.removeFirst();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if(node.left != null) queue.addLast(node.left);
            if(node.right != null) queue.addLast(node.right);
        }

        return root;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
