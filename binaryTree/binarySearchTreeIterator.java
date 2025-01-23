package binaryTree;

import java.util.ArrayList;
import java.util.Stack;

/*
    https://leetcode.com/problems/binary-search-tree-iterator/description/
 */
public class binarySearchTreeIterator {
    /*
        * Approach: Flatten the BST
        *
        * Inorder traversal on the BST will give us the sorted array.
        *
     */
    static class BSTIterator_1 {
        ArrayList<Integer> nodesSorted;
        int index;

        public BSTIterator_1(TreeNode root) {
            this.nodesSorted = new ArrayList<Integer>();
            this.index = -1;
            this._inorder(root);
        }

        private void _inorder(TreeNode root) {
            if (root == null) {
                return;
            }

            this._inorder(root.left);
            this.nodesSorted.add(root.val);
            this._inorder(root.right);
        }

        public int next() {
            return this.nodesSorted.get(++this.index);
        }

        public boolean hasNext() {
            return this.index + 1 < this.nodesSorted.size();
        }
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Controller Recursion
        *
        * if we could simulate a controlled recursion for an inorder traversal,
        * we wouldn't really need to use any additional space other than the space used by the stack for our recursion simulation.
        *
     */
    class BSTIterator_2 {
        Stack<TreeNode> stack;
        public BSTIterator_2(TreeNode root) {
            this.stack = new Stack<TreeNode>();
            this._leftMostInorder(root);
        }

        private void _leftMostInorder(TreeNode root){
            while(root != null){
                this.stack.push(root);
                root = root.left;
            }
        }

        public int next() {
            TreeNode topMostNode = this.stack.pop();
            if(topMostNode.right != null){
                this._leftMostInorder(topMostNode.right);
            }

            return topMostNode.val;
        }

        public boolean hasNext() {
            return this.stack.size() > 0;
        }
    }
    /*
        Time Complexity: hasNext() - O(1), next() - O(1)
        Space Complexity: O(n)
     */
}
