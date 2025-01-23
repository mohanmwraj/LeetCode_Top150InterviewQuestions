package binaryTreeBFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/*
    https://leetcode.com/problems/binary-tree-level-order-traversal/description/
 */
public class BTLevelOrderTraversal {
    /*
        * Approach: Recursion
        *
        * Use level variable, to track the level of the tree.
        * Append the node to the result, when visiting first.
        *
     */
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level) levels.add(new ArrayList<Integer>());

        // fulfil the current level
        levels.get(level).add(node.val);

        // process child nodes for the next level
        if (node.left != null) helper(node.left, level + 1);
        if (node.right != null) helper(node.right, level + 1);
    }

    public List<List<Integer>> levelOrder_1(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Iteration
        *
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while(!queue.isEmpty()){
            levels.add(new ArrayList<Integer>());

            int levelLength = queue.size();
            for(int i = 0; i < levelLength; ++i){
                TreeNode node = queue.remove();
                levels.get(level).add(node.val);

                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            level++;
        }

        return levels;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
