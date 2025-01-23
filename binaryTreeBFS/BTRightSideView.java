package binaryTreeBFS;

import java.util.*;

/*
    https://leetcode.com/problems/binary-tree-right-side-view/description/
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

public class BTRightSideView {
    /*
     * Approach: BFS Two Queues
     *
     * use two queues: one for the current level, and one for the next.
     * The idea is to pop the nodes one by one from the current level and push their children into the next level queue.
     * Each time the current queue is empty, we have the right side element in our hands.
     */
    public List<Integer> rightSideView_1(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();

        ArrayDeque<TreeNode> nextLevel = new ArrayDeque() {
            {
                offer(root);
            }
        };
        ArrayDeque<TreeNode> currLevel = new ArrayDeque();
        List<Integer> rightside = new ArrayList();

        TreeNode node = null;
        while (!nextLevel.isEmpty()) {
            // prepare for the next level
            currLevel = nextLevel;
            nextLevel = new ArrayDeque<>();

            while (!currLevel.isEmpty()) {
                node = currLevel.poll();

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) nextLevel.offer(node.left);
                if (node.right != null) nextLevel.offer(node.right);
            }

            // The current level is finished.
            // Its last element is the rightmost one.
            if (currLevel.isEmpty()) rightside.add(node.val);
        }
        return rightside;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(D) to keep the queues, where D is a tree diameter.
     */
    /*
        * Approach: BFS - One Queue + Sentinel
        *
        * Another approach is to push all the nodes in one queue
        * use a sentinel node to separate the levels.
        * Typically, one could use null as a sentinel.
        *
     */
    public List<Integer> rightSideView_2(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();

        Queue<TreeNode> queue = new LinkedList() {
            {
                offer(root);
                offer(null);
            }
        };
        TreeNode prev, curr = root;
        List<Integer> rightside = new ArrayList();

        while (!queue.isEmpty()) {
            prev = curr;
            curr = queue.poll();

            while (curr != null) {
                // add child nodes in the queue
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }

                prev = curr;
                curr = queue.poll();
            }

            // the current level is finished
            // and prev is its rightmost element
            rightside.add(prev.val);

            // add a sentinel to mark the end
            // of the next level
            if (!queue.isEmpty()) queue.offer(null);
        }
        return rightside;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(D) to keep the queues, where D is a tree diameter.
     */

    /*
        * Approach: BFS - One Queue + Level Size Measurements
        *
     */
    public List<Integer> rightSideView_3(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();

        ArrayDeque<TreeNode> queue = new ArrayDeque() {
            {
                offer(root);
            }
        };
        List<Integer> rightside = new ArrayList();

        while (!queue.isEmpty()) {
            int levelLength = queue.size();

            for (int i = 0; i < levelLength; ++i) {
                TreeNode node = queue.poll();

                // if it's the rightmost element
                if (i == levelLength - 1) {
                    rightside.add(node.val);
                }

                // add child nodes in the queue
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return rightside;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(D) to keep the queues, where D is a tree diameter.
     */
    /*
        * Approach: Recursive DFS
        *
        * The idea is simple: to traverse the tree level by level,
        * starting each time from the rightmost child.
        *
     */
    List<Integer> rightside = new ArrayList();

    public void helper(TreeNode node, int level) {
        if (level == rightside.size()) rightside.add(node.val);

        if (node.right != null) helper(node.right, level + 1);
        if (node.left != null) helper(node.left, level + 1);
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return rightside;

        helper(root, 0);
        return rightside;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(H), where H is a tree height.
     */
}
