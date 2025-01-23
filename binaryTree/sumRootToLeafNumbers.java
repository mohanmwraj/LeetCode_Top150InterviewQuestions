package binaryTree;

import SlidingWindow.minimumWindowSubstring;

import java.util.ArrayDeque;
import java.util.Deque;

/*
    https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
 */
class Pair<K, V> {

    private final K element0;
    private final V element1;

    public <K, V> Pair<K, V> createPair(K element0, V element1) {
        return new Pair<K, V>(element0, element1);
    }

    public Pair(K element0, V element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    public K getKey() {
        return element0;
    }

    public V getValue() {
        return element1;
    }

}
public class sumRootToLeafNumbers {
    /*
        * Approach: Iterative Preorder Traversal
        *
        * Push root into the stack.
        * While the stack is not empty:
        *   Pop out a node from the stack and update the current number.
        *   If the node is a leaf, update the root-to-leaf sum.
        *   Push right and left child nodes into the stack.
        * Return root-to-leaf sum.
     */
    public int sumNumbers_1(TreeNode root) {
        int rootToLeaf = 0, currNumber = 0;
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque();
        stack.push(new Pair(root, 0));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> p = stack.pop();
            root = p.getKey();
            currNumber = p.getValue();

            if (root != null) {
                currNumber = currNumber * 10 + root.val;
                // if it's a leaf, update root-to-leaf sum
                if (root.left == null && root.right == null) {
                    rootToLeaf += currNumber;
                } else {
                    stack.push(new Pair(root.right, currNumber));
                    stack.push(new Pair(root.left, currNumber));
                }
            }
        }
        return rootToLeaf;
    }

    /*
        Time Complexity: O(N)
        Space Complexity: O(H) to keep the stack, where H is a tree height.
     */

    /*
        * Approach: Recursive Preorder Traversal
        *
     */

    int rootToLeaf = 0;

    public void preorder(TreeNode r, int currNumber) {
        if (r != null) {
            currNumber = currNumber * 10 + r.val;
            // if it's a leaf, update root-to-leaf sum
            if (r.left == null && r.right == null) {
                rootToLeaf += currNumber;
            }
            preorder(r.left, currNumber);
            preorder(r.right, currNumber);
        }
    }

    public int sumNumbers_2(TreeNode root) {
        preorder(root, 0);
        return rootToLeaf;
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(H) to keep the stack, where H is a tree height.
     */
}
