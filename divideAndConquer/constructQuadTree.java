package divideAndConquer;
/*
    https://leetcode.com/problems/construct-quad-tree/

    A Quad-Tree is a tree data structure in which each internal node has exactly four children.

    We can construct a Quad-Tree from a two-dimensional area using the following steps:

    If the current grid has the same value (i.e. all 1's or all 0's) set isLeaf True and
    set val to the value of the grid and set the four children to Null and stop.
    If the current grid has different values, set isLeaf to False and set val to any value and
    divide the current grid into four sub-grids
    Recurse for each of the children with the proper sub-grid.
 */
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf,
                Node topLeft, Node topRight,
                Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
public class constructQuadTree {
    /*
        * Approach: Recursion
        *
        * We have a square matrix of size N * N of 0's and 1's.
        * We need to convert this matrix to a quad tree with nodes having two attributes, val and isLeaf:
        *
        * If the whole matrix has the same value (0 or 1), then isLeaf would be true and val would be the same as the matrix value, and we can return.
        * Otherwise, it's not a leaf node, so isLeaf would be false, and val will not matter. This node will have four child nodes.
        * Divide the current matrix into four equally sized square matrices and recurse the same process to each.
        *
        *
        * Iterate over all the values in the current matrix, i.e., with the top-left coordinate at (x1, y1) and the length of the side as length.
        * If all values are the same, then create and return a leaf node with the same value.
        *
        * If all values are not the same, create a new node root, and then make recursive calls to the four sub-matrices:
        *   Top-Left matrix with top-left coordinate as (x1, y1).
        *   Top-Right matrix with top-left coordinate as (x1, y1 + length / 2).
        *   Bottom-Left matrix with top-left coordinate as (x1 + length / 2, y1).
        *   Bottom-Right matrix with top-left coordinate as (x1 + length / 2, y1 + length / 2).
        *
        * Assign the nodes returned by these recursive calls as the respective child nodes of root.
        *
     */
    public Node construct_1(int[][] grid){
        return solve_1(0, 0, grid.length, grid);
    }

    private Node solve_1(int x1, int y1, int length, int[][] grid){
        if(isSameValue(x1, y1, length, grid)){
            return new Node(grid[x1][y1] == 1, true);
        } else {
            Node root = new Node(false, false);

            root.topLeft = solve_1(x1, y1, length / 2, grid);
            root.topRight = solve_1(x1, y1 + length / 2, length/2, grid);
            root.bottomLeft = solve_1(x1 + length / 2, y1, length / 2, grid);
            root.bottomRight = solve_1(x1 + length / 2, y1 + length / 2, length / 2, grid);

            return root;
        }
    }

    private boolean isSameValue(int x1, int y1, int length, int[][] grid){
        for(int i = x1; i < x1 + length; ++i){
            for(int j = y1; j < y1 + length; ++j){
                if(grid[i][j] != grid[x1][y1]){
                    return false;
                }
            }
        }

        return true;
    }

    /*
        Time Complexity: O(N^2 log N)
        Space Complexity: O(log N)
     */

    /*
        * Approach: Optimized Recursion
        *
        * In the previous approach, we first iterate over all the cells in the matrix and then decide if this should be a leaf or not and have four child nodes.
        * In case we decide to have four child nodes, we recursively move to the four sub-matrices and follow the same process.
        * The redundant part in this approach is when we will iterate over the cells in the sub-matrices that would have already been iterated for the root node.
        *
        * These redundant operations can be avoided if we simply make a recursive call to the four sub-matrices instead of first checking all the values.
        * Once all four recursive calls are returned, we will decide whether to let these as child nodes of the root node or should be combined them into one as the root node.
        * This decision will again depend on the values, but we won't have to check all the cells,
        * instead, we can just check if the four nodes are leaf nodes and all have the same value (value attribute).
        *
        * If it is, we can just return a root leaf node with a value same as the four nodes,
        * otherwise, we will return a node with any value and having these nodes as the respective child nodes.
        *
     *
     */

    public Node construct_2(int[][] grid){
        return solve_2(0, 0, grid.length, grid);
    }

    private Node solve_2(int x1, int y1, int length, int[][] grid){
        if(length == 1){
            return new Node(grid[x1][y1] == 1, true);
        }

        Node topLeft = solve_2(x1, y1, length / 2, grid);
        Node topRight = solve_2(x1, y1  + length / 2, length / 2, grid);
        Node bottomLeft = solve_2(x1  + length / 2, y1, length / 2, grid);
        Node bottomRight = solve_2(x1  + length / 2, y1  + length / 2, length / 2, grid);

        if(topLeft.isLeaf
                && topRight.isLeaf
                && bottomLeft.isLeaf
                && bottomRight.isLeaf
                && topLeft.val == topRight.val
                && topRight.val == bottomLeft.val
                && bottomLeft.val == bottomRight.val
                ) {
            return new Node(topLeft.val, true);
        }

        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    /*
        Time Complexity: O(N^2)
        Space Complexity: O(log N)
     */
}
