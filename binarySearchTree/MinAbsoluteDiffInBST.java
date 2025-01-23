package binarySearchTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class MinAbsoluteDiffInBST {
    /*
     * Approach: Depth First Search
     *
     * To solve this problem, we don't need to check every pair of integers.
     * Instead, checking the difference between every two consecutive integers would work.
     *
     */
    List<Integer> nodeValues = new ArrayList<>();

    void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        nodeValues.add(node.val);
        dfs(node.left);
        dfs(node.right);
    }

    int getMinimumDifference_1(TreeNode root) {
        dfs(root);

        Collections.sort(nodeValues);
        int minDifference = Integer.MAX_VALUE;
        // Find the diff between every two consecutive values in the list.
        for (int i = 1; i < nodeValues.size(); i++) {
            minDifference = Math.min(minDifference, nodeValues.get(i) - nodeValues.get(i - 1));
        }

        return minDifference;
    }
    /*
        Time complexity: O(n⋅logn)
        Space complexity: O(n)
     */

    /*
        * Approach: In-Order Traversal Using List
        *
     */
    List<Integer> inorderNodes = new ArrayList<>();

    void inorderTraversal_2(TreeNode node) {
        if (node == null) {
            return;
        }

        inorderTraversal_2(node.left);
        // Store the nodes in the list.
        inorderNodes.add(node.val);
        inorderTraversal_2(node.right);
    }

    int getMinimumDifference_2(TreeNode root) {
        inorderTraversal_2(root);

        int minDifference = Integer.MAX_VALUE;
        for (int i = 1; i < inorderNodes.size(); i++) {
            minDifference = Math.min(minDifference, inorderNodes.get(i) - inorderNodes.get(i-1));
        }

        return minDifference;
    }
    /*
        Time complexity: O(n)
        Space complexity: O(n)
     */
    /*
        * Approach: Inorder Traversal without List
        *
        * we can avoid storing elements in a list
        * if we can find the difference between consecutive nodes on the fly during in-order traversal.
        * For each node in the tree, we need the previous node we have handled,
        * and then we can find the difference.
     */
    int minDifference = Integer.MAX_VALUE;
    TreeNode prevNode;

    private void inorderTraversal_3(TreeNode node){
        if(node == null) return;

        inorderTraversal_3(node.left);
        if(prevNode != null){
            minDifference = Math.min(minDifference, node.val - prevNode.val);
        }
        prevNode = node;
        inorderTraversal_3(node.right);
    }

    public int getMinimumDifference_3(TreeNode root) {
        inorderTraversal_3(root);
        return minDifference;
    }
    /*
        Time complexity: O(n)
        Space complexity: O(n)
     */
}
