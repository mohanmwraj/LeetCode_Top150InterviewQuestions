package divideAndConquer;

import java.util.Random;

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

public class convertSortedArrayintiBST {
    /*
     * Approach: Preorder Traversal: Always Choose Left Middle Node as a Root
     *
     *
     */
    int[] nums;

    public TreeNode helper(int left, int right) {
        if (left > right) return null;

        // always choose left middle node as a root
        int p = (left + right) / 2;

        // preorder traversal: node -> left -> right
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper(left, p - 1);
        root.right = helper(p + 1, right);
        return root;
    }

    public TreeNode sortedArrayToBST_1(int[] nums) {
        this.nums = nums;
        return helper(0, nums.length - 1);
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(log N)
     */
    /*
     * Approach: Preorder Traversal - Always Choose Right Middle Node as a Root
     *
     */
    public TreeNode helper_2(int left, int right) {
        if (left > right) return null;

        // always choose right middle node as a root
        int p = (left + right) / 2;
        if ((left + right) % 2 == 1) ++p;

        // preorder traversal: node -> left -> right
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper_2(left, p - 1);
        root.right = helper_2(p + 1, right);
        return root;
    }

    public TreeNode sortedArrayToBST_2(int[] nums) {
        this.nums = nums;
        return helper_2(0, nums.length - 1);
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(log N)
     */
    /*
     * Approach: Preorder Traversal - Choose a Random Middle Node as a Root
     *
     */
    Random rand = new Random();
    public TreeNode helper_3(int left, int right) {
        if (left > right) return null;

        // choose random middle node as a root
        int p = (left + right) / 2;
        if ((left + right) % 2 == 1) p += rand.nextInt(2);

        // preorder traversal: node -> left -> right
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper_3(left, p - 1);
        root.right = helper_3(p + 1, right);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return helper_3(0, nums.length - 1);
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(log N)
     */
}
