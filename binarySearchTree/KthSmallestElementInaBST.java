package binarySearchTree;

import java.util.ArrayList;
import java.util.LinkedList;

/*
    https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 */
public class KthSmallestElementInaBST {
    /*
        * Approach: Recursive Inorder Traversal
        *
        * The idea is to build an inorder traversal of BST which is an array sorted in the ascending order.
        * Now the answer is the k - 1th element of this array.
        *
     */
    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }

    public int kthSmallest_1(TreeNode root, int k) {
        ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
        return nums.get(k - 1);
    }
    /*
        Time complexity: O(n)
        Space complexity: O(n)
     */
    /*
        * Approach: Iterative Inorder Traversal
        *
     */
    public int kthSmallest_2(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }

    /*
        Time complexity: O(H+k)
        Space complexity: O(H)
     */
}
