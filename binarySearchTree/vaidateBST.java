package binarySearchTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/*
    https://leetcode.com/problems/validate-binary-search-tree/description/
 */
public class vaidateBST {
    /*
        * Approach: Recursive Inorder with valid Range
        *
        * compares the node value with its upper and lower limits if they are available.
        *
     */
    public boolean validate(TreeNode root, Integer low, Integer high) {
        if (root == null) {
            return true;
        }

        if ((low != null && root.val <= low) ||
                        (high != null && root.val >= high)) {
            return false;
        }

        return (validate(root.right, root.val, high) &&
                        validate(root.left, low, root.val));
    }

    public boolean isValidBST_1(TreeNode root) {
        return validate(root, null, null);
    }
    /*
        Time complexity: O(n)
        Space complexity: O(n)
     */

    /*
        * Approach: Iterative Traversal with Valid Ranges
        *
     */
    private Deque<TreeNode> stack = new LinkedList();
    private Deque<Integer> upperLimits = new LinkedList();
    private Deque<Integer> lowerLimits = new LinkedList();

    public void update(TreeNode root, Integer low, Integer high) {
        stack.add(root);
        lowerLimits.add(low);
        upperLimits.add(high);
    }

    public boolean isValidBST_2(TreeNode root) {
        Integer low = null, high = null, val;
        update(root, low, high);

        while (!stack.isEmpty()) {
            root = stack.poll();
            low = lowerLimits.poll();
            high = upperLimits.poll();

            if (root == null) continue;
            val = root.val;
            if (low != null && val <= low) {
                return false;
            }
            if (high != null && val >= high) {
                return false;
            }
            update(root.right, val, high);
            update(root.left, low, val);
        }
        return true;
    }
    /*
        Time complexity: O(n)
        Space complexity: O(n)
     */

    /*
        * Approach: Recursive Inorder Traversal
        *
     */
    private Integer prev;

    public boolean isValidBST_3(TreeNode root) {
        prev = null;
        return inorder(root);
    }

    private boolean inorder(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!inorder(root.left)) {
            return false;
        }
        if (prev != null && root.val <= prev) {
            return false;
        }
        prev = root.val;
        return inorder(root.right);
    }
    /*
        Time complexity: O(n)
        Space complexity: O(n)
     */

    /*
        * Approach: Iterative Inorder Traversal
        *
     */
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer prev = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (prev != null && root.val <= prev) {
                return false;
            }
            prev = root.val;
            root = root.right;
        }
        return true;
    }
    /*
        Time complexity: O(n)
        Space complexity: O(n)
     */
}
