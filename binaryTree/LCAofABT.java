package binaryTree;
/*
    https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 */
public class LCAofABT {
    /*
        * Approach: Recursive Approach
        *
        * Perform a recursive search in the left subtree to find the nodes x and y.
        * This involves calling the LCA function on the left child of the current root.
        * Similarly, perform a recursive search in the right subtree.
        * This entails calling the LCA function on the right child of the current root.
        * After completing the recursive searches, analyze the results of both subtree searches.
        *
        * If both recursive calls return non-null values, it implies that one target node was found in each subtree.
        * Consequently, the current root node must be the LCA, as it is the common ancestor of both nodes.
        *
        * If only one of the subtree searches returns a non-null result, it indicates that both target nodes are located within the same subtree.
        * In this case, return the non-null result, which represents the LCA found in that subtree.
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null){
            return right;
        } else if(right == null){
            return left;
        } else {
            return root;
        }
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
