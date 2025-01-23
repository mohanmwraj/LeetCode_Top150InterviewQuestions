package binaryTree;
/*
    https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */

import com.sun.source.tree.Tree;

import java.util.LinkedList;

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

public class maximumDepthofBinaryTree {
    /*
     * Approach: Recursion
     *
     * the maximum depth of a binary tree is the maximum number of steps
     * to reach a leaf node from the root node.
     *
     */

    public int maxDepth_1(TreeNode root){
        if(root == null) return 0;

        return 1 + Math.max(maxDepth_1(root.left),
                                maxDepth_1(root.right));
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Iteration
        *
        * use two stacks, one for store the nodes, another one to store the height.
        *
     */
    public int maxDepth_2(TreeNode root){
        if(root == null) return 0;

        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> depth = new LinkedList<>();
        stack.add(root);
        depth.add(1);

        int height = 0;
        while(!stack.isEmpty()){
            TreeNode node = stack.poll();
            int currentDepth = depth.poll();

            if(node != null){
                height = Math.max(height, currentDepth);
                stack.push(node.left);
                stack.push(node.right);

                depth.push(currentDepth + 1);
                depth.push(currentDepth + 1);
            }
        }

        return height;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
