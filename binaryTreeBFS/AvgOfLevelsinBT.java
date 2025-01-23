package binaryTreeBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    https://leetcode.com/problems/average-of-levels-in-binary-tree/
 */
public class AvgOfLevelsinBT {
    /*
        * Approach: Using Depth First Search
        *
        * we use of two lists count and res.
        * count[i] refers to the total number of nodes found at the ith level(counting from root at level 0) till now,
        * res[i] refers to the sum of the nodes at the ith level encountered till now during the Depth First Search.
        *
     */
    public List< Double > averageOfLevels_1(TreeNode root) {
        List < Integer > count = new ArrayList< >();
        List < Double > res = new ArrayList < > ();
        average(root, 0, res, count);
        for (int i = 0; i < res.size(); i++)
            res.set(i, res.get(i) / count.get(i));
        return res;
    }
    public void average(TreeNode t, int i, List < Double > sum, List < Integer > count) {
        if (t == null)
            return;
        if (i < sum.size()) {
            sum.set(i, sum.get(i) + t.val);
            count.set(i, count.get(i) + 1);
        } else {
            sum.add(1.0 * t.val);
            count.add(1);
        }
        average(t.left, i + 1, sum, count);
        average(t.right, i + 1, sum, count);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(h)
     */

    /*
        * Approach: Breadth First Search
        *
     */
    public List < Double > averageOfLevels(TreeNode root) {
        List < Double > res = new ArrayList < > ();
        Queue< TreeNode > queue = new LinkedList< >();
        queue.add(root);
        while (!queue.isEmpty()) {
            long sum = 0, count = 0;
            Queue < TreeNode > temp = new LinkedList < > ();
            while (!queue.isEmpty()) {
                TreeNode n = queue.remove();
                sum += n.val;
                count++;
                if (n.left != null)
                    temp.add(n.left);
                if (n.right != null)
                    temp.add(n.right);
            }
            queue = temp;
            res.add(sum * 1.0 / count);
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(h)
     */
}
