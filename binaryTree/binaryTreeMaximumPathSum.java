package binaryTree;
/*
    https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 */
public class binaryTreeMaximumPathSum {
    /*
        * Approach: Post Order DFS
        *
        * There could be four possibilities.
        *   1. The path starts at the root and goes down through the root's left child.
        *      We don't know how long the path is, but it could extend to the bottom of the left subtree.
        *   2. The path starts at the root and goes down through the root's right child.
        *      Very similar to the previous case, but the direction is toward the right.
        *   3. The path involves both the left and the right child.
        *   4. The path doesn't involve any child. The root itself is the only element of the path with maximum sum.
        *
                left_gain = max(gain_from_left_subtree, 0)
                right_gain = max(gain_from_right_subtree, 0)
                gain_from_subtree = max(left_gain, right_gain) + root.val
        * We use max(gain_from_left_subtree, 0) because we want to consider the gain only if it is positive.
        * If it is negative, we ignore it or consider it as zero.
        *
      */
    private int maxSum;
    public int maxPathSum(TreeNode root){
        maxSum = Integer.MIN_VALUE;
        gainFromSubTree(root);
        return maxSum;
    }

    private int gainFromSubTree(TreeNode root){
        if(root == null) return 0;

        int gainFromLeft = Math.max(gainFromSubTree(root.left), 0);
        int gainFromRight = Math.max(gainFromSubTree(root.right), 0);

        maxSum = Math.max(maxSum, gainFromLeft + gainFromRight + root.val);

        return Math.max(gainFromLeft + root.val, gainFromLeft + root.val);
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

}
