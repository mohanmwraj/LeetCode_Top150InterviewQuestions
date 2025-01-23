package binaryTree;

import java.util.HashMap;
import java.util.Map;

/*
    https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 */
public class constructBTFromInAndPostorder {
    /*
        * Approach: Recursion
        *
     */
    private int postOrderIndex;
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postOrderIndex = postorder.length - 1;
        inorderIndexMap = new HashMap<>();

        for(int i = 0; i < inorder.length; ++i){
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(postorder, 0, inorder.length - 1);
    }

    private TreeNode arrayToTree(int[] postorder, int left, int right){
        if(left > right) return null;

        int rootValue = postorder[postOrderIndex--];
        TreeNode root = new TreeNode(rootValue);
        // postOrderIndex--;
        root.right = arrayToTree(postorder, inorderIndexMap.get(rootValue) + 1, right);
        root.left = arrayToTree(postorder, left, inorderIndexMap.get(rootValue) - 1);

        return root;

    }
}
