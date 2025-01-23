package binaryTree;

import java.util.HashMap;
import java.util.Map;

/*
    https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 */
public class constructBTfromPreAndInorder {
    /*
        * Approach: Recursion
        *
        * Preorder traversal follows Root -> Left -> Right, therefore, given the preorder array preorder,
        * we have easy access to the root which is preorder[0].
        *
        * Inorder traversal follows Left -> Root -> Right, therefore if we know the position of Root,
        * we can recursively split the entire array into two subtrees.
        *
        * Build a hashmap to record the relation of value -> index for inorder, so that we can find the position of root in constant time.
        * Initialize an integer variable preorderIndex to keep track of the element that will be used to construct the root.
        * Implement the recursion function arrayToTree which takes a range of inorder and returns the constructed binary tree.
        * initialize the root with preorder[preorderIndex] and then increment preorderIndex
        * recursively use the left and right portions of inorder to construct the left and right subtrees.
        *
     */

    int preOrderIndex;
    Map<Integer, Integer> inOrderMap;
    public TreeNode buildTree(int[] inOrder, int[] preOrder){
        preOrderIndex = 0;
        inOrderMap = new HashMap<>();

        for(int i = 0; i < inOrder.length; ++i){
            inOrderMap.put(inOrder[i], i);
        }
        return arrayToTree(preOrder, 0, preOrder.length - 1);
    }

    private TreeNode arrayToTree(int[] preOrder, int left, int right){
        if(left > right) return null;

        int rootValue = preOrder[preOrderIndex++];
        TreeNode root = new TreeNode(rootValue);

        root.left = arrayToTree(preOrder, left, inOrderMap.get(rootValue) - 1);
        root.right = arrayToTree(preOrder, inOrderMap.get(rootValue) + 1, right);
        return root;
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

}
