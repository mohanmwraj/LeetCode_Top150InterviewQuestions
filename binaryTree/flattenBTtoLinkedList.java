package binaryTree;
/*
    https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
 */

import com.sun.source.tree.Tree;

public class flattenBTtoLinkedList {
    /*
        * Approach: Recursion
        *
        * For a given node, we will recursively flatten out the left and the right subtrees and
        * store their corresponding tail nodes in leftTail and rightTail respectively.
        *
        * we will make the following connections (only if there is a left child for the current node, else the leftTail would be null)
             leftTail.right = node.right
             node.right = node.left
             node.left = None
         * Next we have to return the tail of the final, flattened out tree rooted at node.
         * So, if the node has a right child, then we will return the rightTail, else, we'll return the leftTail.
         *
     */
    public void flatten_1(TreeNode root){
        this.flattenTree(root);
    }

    private TreeNode flattenTree(TreeNode root){
        if(root == null) return null;
        if(root.left == null && root.right == null) return root;

        TreeNode leftTail = this.flattenTree(root.left);
        TreeNode rightTail = this.flattenTree(root.right);

        if(leftTail != null){
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        return rightTail == null ? leftTail : rightTail;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Iterative Solution O(1)
        *
        * For every node we check if it has a left child or not. If it doesn't we simply move on to the right hand side i.e.
        *        node = node.right
        * If the node does have a left child, we find the first node on the rightmost branch of the left subtree
        * which doesn't have a right child i.e. the almost rightmost node.
             rightmost = node.left
             while rightmost != null:
                 rightmost = rightmost.right
         * Once we find this rightmost node, we rewire the connections as explained in the intuition section.
                 rightmost.right = node.right
                 node.right = node.left
                 node.left = null
         *
     */

    public void flatten_2(TreeNode root){
        if(root == null) return;
        TreeNode node = root;

        while(node != null){
            if(node.left != null){
                TreeNode rightMost = node.left;
                while(rightMost.right != null){
                    rightMost = rightMost.right;
                }

                rightMost.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
