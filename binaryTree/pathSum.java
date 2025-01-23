package binaryTree;

import java.util.LinkedList;

/*
    https://leetcode.com/problems/path-sum/description/
 */
public class pathSum {
    /*
        * Approach: Recursion
        *
        * If node is not a leaf,
        * one calls recursively hasPathSum method for its children with a sum decreased by the current node value.
        *
        * If node is a leaf,
        * one check if the current sum is zero, i.e. if the initial sum was discovered.
     */
    public boolean hasPathSum_1(TreeNode root, int sum) {
        if (root == null) return false;

        sum -= root.val;
        if ((root.left == null) && (root.right == null)) return (sum == 0);
        return hasPathSum_1(root.left, sum) || hasPathSum_1(root.right, sum);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Iterations
        *
        * The idea is to visit each node with the DFS strategy while updating the remaining sum to cumulate at each visit.
        * sum - root.val
        *
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;

        LinkedList<TreeNode> nodeStack = new LinkedList<>();
        LinkedList<Integer> sumStack = new LinkedList<>();
        nodeStack.add(root);
        sumStack.add(targetSum - root.val);

        TreeNode node;
        int currSum;

        while(!nodeStack.isEmpty()){
            node = nodeStack.pollLast();
            currSum = sumStack.pollLast();

            if(node.left == null && node.right == null && currSum == 0){
                return true;
            }

            if(node.left != null){
                nodeStack.add(node.left);
                sumStack.add(currSum - node.left.val);
            }

            if(node.right != null){
                nodeStack.add(node.right);
                sumStack.add(currSum - node.right.val);
            }
        }

        return false;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
