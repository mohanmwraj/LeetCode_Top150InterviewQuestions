package binaryTree;


import javax.swing.tree.TreeCellRenderer;
import java.util.ArrayDeque;

/*
    https://leetcode.com/problems/same-tree/description/
 */
public class sameTree {
    /*
        * Approach: Recursion
        *
        * Check if p and q nodes are not None, and their values are equal.
        * If all checks are OK, do the same for the child nodes recursively.
        *
     */

    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;

        return isSameTree(p.right, q.right) &&
                isSameTree(p.left, q.left);
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Iteration
        *
        * use two deque for two trees.
        * Start from the root and then at each iteration pop the current node out of the deques.
        * do the checks.
        *
     */

    public boolean check(TreeNode tree1, TreeNode tree2){
        if(tree1 == null && tree2 == null) return true;
        if(tree1 == null || tree2 == null) return false;
        if(tree1.val != tree2.val) return false;

        return true;
    }

    public boolean isSameTree_2(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        if(!check(p, q)) return false;

        ArrayDeque<TreeNode> deqP = new ArrayDeque<>();
        ArrayDeque<TreeNode> deqQ = new ArrayDeque<>();
        deqQ.addLast(q);
        deqP.addLast(p);

        while(!deqP.isEmpty()){
            p = deqP.removeFirst();
            q = deqQ.removeFirst();

            if(!check(p, q)) return false;
            if(p != null){
                if(!check(p.left, q.left)) return false;
                if(p.left != null){
                    deqP.addLast(p.left);
                    deqQ.addLast(q.left);
                }

                if(!check(p.right, q.right)) return false;
                if(q.right != null){
                    deqP.addLast(p.left);
                    deqQ.addLast(q.right);
                }
            }
        }
        return true;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
