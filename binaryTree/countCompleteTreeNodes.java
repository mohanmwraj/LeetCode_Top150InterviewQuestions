package binaryTree;

import com.sun.source.tree.Tree;

/*
    https://leetcode.com/problems/count-complete-tree-nodes/description/
 */
public class countCompleteTreeNodes {
    /*
        * Approach: Linear Time
        *
        * counts nodes recursively one by one.
        *
     */
    public int countNodes_1(TreeNode root) {
        return root != null ? 1 +
                    countNodes_1(root.right) +
                        countNodes_1(root.left) : 0;
    }

    /*
        * Approach: Binary Search
        *
        * In a complete binary tree every level,
        * except possibly the last, is completely filled,
        * and all nodes in the last level are as far left as possible.
        *
        * complete tree has 2k nodes in the kth level if the kth level is not the last one.
        * The last level may be not filled completely,
        * and hence in the last level the number of nodes
        * could vary from 1 to 2d, where d is a tree depth.
        *
        * last level: 1 <= nodes <= 2^d
     */

    public int countNodes_2(TreeNode root){
        if(root == null) return 0;
        int d = computeDepth(root);
        if(d == 0) return 1;

        int left = 1;
        int right = (int)Math.pow(2, d) - 1;
        int pivot;
        while(left <= right){
            pivot = left + (right - left) / 2;
            if(exists(pivot, d, root)){
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }

        return (int)Math.pow(2, d) - 1 + left;
    }

    private int computeDepth(TreeNode root){
        int d = 0;
        while(root.left != null){
            ++d;
            root = root.left;
        }
        return d;
    }

    private boolean exists(int idx, int d, TreeNode root){
        int left = 0;
        int right = (int)Math.pow(2, d) - 1;
        int pivot;

        for(int i = 0; i < d; ++i){
            pivot = left + (right - left) / 2;
            if(idx <= pivot){
                root = root.left;
                right = pivot;
            } else {
                root = root.right;
                left = pivot + 1;
            }
        }

        return root != null;
    }
    /*
        Time complexity : O(d^2)=O(log2N), where d is a tree depth.
        Space Complexity: O(1)
     */
}
